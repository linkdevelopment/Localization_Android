package com.linkdev.localization

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import androidx.annotation.IdRes
import com.linkdev.localization.data.models.Locales
import com.linkdev.localization.data.shared_prefrences.LocalizationPrefsDataSource
import com.linkdev.localization.utils.LocaleContextWrapper
import com.linkdev.localization.utils.LocalizationUtils
import java.util.*


object Localization {

    /**
     *Called to handle the language and configuration changes, but leaves the application restart to the consumer app
     * and perform the following actions
     *          -updated the prefs with the new lang
     *          -update the resources configuration
     *  @param context context of current activity
     *  @param language new lang
     *  @param country new country
     */
    fun setLocale(context: Context, language: String, country: String = "") {
        setLocalAndApply(context, Locale(language, country))
    }


    /**
     * Will check on the saved language and perform the following actions
     *      1-if new and saved local are not the same
     *          -updated the prefs with the new lang
     *          -update the resources configuration
     *          -And restart app with new lang
     *
     *      2-if new and saved local are the same
     *         -it reverse lang if it is arabic will be english and vice versa
     *         -updated the prefs
     *         -update the resources configuration
     *          -And restart app with new lang
     *  @param activity context of current activity
     *  @param lacale the new locale
     *  @param activityClass if you want to more from activity to another
     **/
    fun reverseLangAndRestart(activity: Activity?, locale: Locale? = null, bundle: Bundle? = null) {
        check(activity != null) { "Localization: Activity must be not null" }
        if (locale == null) {
            if (getLanguage(activity).equals(Locales.English.language))
                setLocalAndApply(activity, Locales.Arabic)
            else
                setLocalAndApply(activity, Locales.English)
        } else {
            setLocalAndApply(activity, locale)
        }
        val intent = Intent(activity, activity.javaClass)
        if (bundle != null)
            intent.putExtras(bundle)
        activity.startActivity(intent)
        activity.finish()
    }

    /**
     * Will check on the saved language and perform the following actions
     * 1         -if new and saved locale are the same return with no action will take
     *
     * 2        -updated the prefs with the new lang
     *          -update the resources configuration
     *          -And recreate app with new lang
     *  @param activity context of current activity
     *  @param lacale the new locale
     **/
    fun setLocaleAndRecreatet(activity: Activity?, locale: Locale, bundle: Bundle? = null) {
        check(activity != null) { "Localization: Activity must be not null" }
        if (!isDifferentLocale(activity, locale.language)) return

        setLocalAndApply(activity, locale)
        activity.intent.replaceExtras(bundle)
        activity.recreate()
    }

    /**
     * Will check on the saved language and perform the following actions
     * 1          -if new and saved locale are the same return with no action will take
     *
     * 2          -updated the prefs with the new lang
     *          -update the resources configuration
     *          -navigate to the same navigation graph using a specif action ID
     *          -And restart app
     *  @param activity context of current activity
     *  @param lacale the new locale
     *  @param actionID the action ID that will be navigated to in the navigation graph
     *  @param actionIDTag tag name of [actionID]
     **/
    fun setLocaleAndRestart(
        activity: Activity?,
        locale: Locale,
        actionIDTag: String,
        @IdRes actionID: Int
    ) {
        check(activity != null) { "Localization: Activity must be not null" }
        if (!isDifferentLocale(activity, locale.language)) return

        setLocalAndApply(activity, locale)
        val intent = Intent(activity, activity.javaClass)
        intent.putExtra(actionIDTag, actionID)
        activity.startActivity(intent)
        activity.finish()
    }

    /**
     * Will check on the saved language and perform the following actions
     * 1         -if new and saved locale are the same return with no action will take
     *
     * 2         -updated the prefs with the new lang
     *          -update the resources configuration
     *          -navigate in different nav graph by deepLink
     *          -And restart app
     *  @param activity context of current activity
     *  @param lacale the new locale
     *  @param deepLink the deepLink which will move to
     *  @param deepLinkTag tag name of [deepLink]
     *  @param activityClass if you want to more from activity to another
     **/
    fun setLocaleAndRestart(
        activity: Activity?,
        locale: Locale,
        deepLink: String,
        deepLinkTag: String
    ) {
        check(activity != null) { "Localization: Activity must be not null" }
        if (!isDifferentLocale(activity, locale.language)) return

        setLocalAndApply(activity, locale)
        val intent = Intent(activity, activity.javaClass)
        intent.putExtra(deepLinkTag, deepLink)
        activity.startActivity(intent)
        activity.finish()
    }

    /**
     * Will check on the saved language and perform the following actions
     * 1         -if new and saved locale are the same return with no action will take
     *
     * 2        -updated the prefs with the new lang
     *          -update the resources configuration
     *          -navigate to new activity
     *          -And restart app
     *  @param activity context of current activity
     *  @param lacale the new locale
     *  @param activityClass new activity class will navigate to
     *  @param bundle if you want to sent args
     *  @param flags if you want to sent flags
     **/
    fun <T : Activity> setLocaleAndRestart(
        activity: Activity?,
        locale: Locale,
        activityClass: Class<T>,
        flags: Int? = null,
        bundle: Bundle? = null
    ) {
        check(activity != null) { "Localization: Activity must be not null" }
        if (!isDifferentLocale(activity, locale.language)) return
        setLocalAndApply(activity, locale)
        val intent = Intent(activity, activityClass)
        if (flags != null) {
            intent.flags = flags
        }
        if (bundle != null)
            intent.putExtras(bundle)
        activity.startActivity(intent)
        activity.finish()
    }

    fun getLocale(context: Context): Locale {
        return LocalizationPrefsDataSource(context).getLocale()
    }


    fun getLanguage(context: Context): String {
        return getLocale(context).language
    }


    fun initialize(application: Application) {
        application.registerComponentCallbacks(
            LocalizationApplicationCallbacks {
                onConfigurationChange(application, it)
            }
        )
    }

    private fun onConfigurationChange(context: Context, config: Configuration) {
        val configuration: Configuration? = config
        if (LocalizationPrefsDataSource(context).getLocale().language.isNotEmpty()) {
            val newLocale = Locale(LocalizationPrefsDataSource(context).getLocale().language)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                configuration?.apply {
                    val localeList = LocaleList(newLocale)
                    LocaleList.setDefault(localeList)
                    setLocale(newLocale)
                    setLocales(localeList)
                }
            } else {
                configuration?.setLocale(newLocale)
            }
        }
    }

    private fun setLocalAndApply(context: Context, locale: Locale) {
        LocalizationPrefsDataSource(context).setLocale(locale)
        LocalizationUtils.applyLocale(context, locale)
    }


    /**
     * change resources
     */
    private fun applyLocale(context: Context) {
        LocalizationUtils.applyLocale(context, LocalizationPrefsDataSource(context).getLocale())
    }


    private fun isDifferentLocale(context: Context, newLang: String): Boolean {
        return !newLang.equals(getLanguage(context))
    }

    fun onAttach(context: Context): ContextWrapper {
        return LocaleContextWrapper.wrap(
            context,
            LocalizationPrefsDataSource(context).getLocale().language
        )

    }

}

package com.linkdev.localization

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import com.linkdev.localization.data.models.Locales
import com.linkdev.localization.data.shared_prefrences.LocalizationPrefsDataSource
import com.linkdev.localization.utils.ErrorMessages.MSG_ACTIVITY_NULL
import com.linkdev.localization.utils.LaunchUtils
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
     *  @param locale new locale
     */
    fun setLocale(context: Context, locale: Locale) {
        setLocalAndApply(context, locale)
    }


    /**
     * Will check on the saved language and perform the following actions
     *         1-if new and saved local are not the same
     *          -updated the prefs with the new lang
     *          -update the resources configuration
     *          -And restart app with new lang
     *
     *        2-if new and saved local are the same
     *         -it reverse lang if it is arabic will be english and vice versa
     *         -updated the prefs
     *         -update the resources configuration
     *          -And restart app with new lang
     *  @param activity context of current activity
     *  @param locale the new locale
     *   @param bundle the new arguments
     **/
    fun <T : Activity> reverseLangAndRestart(
        activity: Activity?, activityClass: Class<T>,
        locale: Locale? = null, bundle: Bundle? = null
    ) {
        check(activity != null) { MSG_ACTIVITY_NULL }
        if (locale == null) {
            if (LocalizationPrefsDataSource.init(activity)
                    .getLanguage() == Locales.English.language
            )
                setLocalAndApply(activity, Locales.Arabic)
            else
                setLocalAndApply(activity, Locales.English)
        } else {
            setLocalAndApply(activity, locale)
        }
        LaunchUtils.startActivity(activity,activityClass, bundle)
    }

    /**
     * Will check on the saved language and perform the following actions
     *         1-if new and saved locale are the same return with no action will take
     *
     *         2-updated the prefs with the new lang
     *          -update the resources configuration
     *          -And recreate activity with new lang
     *  @param activity context of current activity
     *  @param locale the new locale
     *  @param bundle the new arguments
     **/
    fun setLocaleAndRecreate(activity: Activity?, locale: Locale, bundle: Bundle? = null) {
        check(activity != null) { MSG_ACTIVITY_NULL }
        if (!isDifferentLocale(activity, locale.language)) return

        setLocalAndApply(activity, locale)
        activity.intent.replaceExtras(bundle)
        activity.recreate()
    }

    /**
     * Will check on the saved language and perform the following actions
     *         1-if new and saved locale are the same return with no action will take
     *
     *         2-updated the prefs with the new lang
     *          -update the resources configuration
     *          -navigate to new activity
     *          -And restart app
     *  @param activity context of current activity
     *  @param locale the new locale
     *  @param activityClass new activity class will navigate to
     *  @param bundle the new arguments
     *  @param flags the intent flags
     **/
    fun <T : Activity> setLocaleAndRestart(
        activity: Activity?,
        locale: Locale,
        activityClass: Class<T>,
        flags: Int? = null,
        bundle: Bundle? = null
    ) {
        check(activity != null) { MSG_ACTIVITY_NULL }
        if (!isDifferentLocale(activity, locale.language)) return
        setLocalAndApply(activity, locale)
        LaunchUtils.startActivity(activity, activityClass, bundle, flags)
    }


    /*
     * Will be initialized in app application class to be invoked when configuration changed
     */

    fun initialize(application: Application) {
        application.registerComponentCallbacks(
            LocalizationApplicationCallback(application)
        )
    }

    /*
     *Will store locale in prefs and
     *Update resources with new locale
     */
    private fun setLocalAndApply(context: Context, locale: Locale) {
        if (!isDifferentLocale(context, locale.language)) return
        LocalizationPrefsDataSource.init(context).setLocale(locale)
        LocalizationUtils.applyLocale(context, locale)
    }

    /*
     *Will check saved and new locale and returns {true} if they are the same
     */
    private fun isDifferentLocale(context: Context, newLang: String): Boolean {
        return newLang != LocalizationPrefsDataSource.init(context).getLanguage()
    }

    /*
     *Will create configuration context with save locale
     * to be passed to {attachBaseContext} of consumer activity
     */
    fun onAttach(context: Context): ContextWrapper {
        return LocaleContextWrapper.wrap(
            context,
            LocalizationPrefsDataSource.init(context).getLanguage()
        )

    }

}

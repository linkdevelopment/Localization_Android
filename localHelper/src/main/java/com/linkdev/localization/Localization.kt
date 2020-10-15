package com.linkdev.localization

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import com.linkdev.localization.data.shared_prefrences.LocalizationPrefsDataSource
import com.linkdev.localization.utils.LocalizationUtils
import java.util.*


object Localization {

    /**
     *Called to handle the language and configuration changes, but leaves the application restart to the consumer app
     */
    @JvmOverloads
    fun setLocale(context: Context, language: String, country: String = "") {
        setLocalAndApply(context, Locale(language, country))
    }


    /**
     *Use this method when you want change app language with no action just recreate activity
     *  @param activity context of current activity
     *  @param lacale the new locale
     **/
    fun setLocaleAndRestart(activity: Activity?, locale: Locale, bundle: Bundle? = null) {
        check(activity != null) { "Localization: Activity must be not null" }
        setLocalAndApply(activity, locale)
        activity.intent.replaceExtras(bundle)
        activity.recreate()
    }

    /**
     *Use this method when you wantto navigate to the same navigation graph using a specif action ID
     *  @param activity context of current activity
     *  @param lacale the new locale
     *  @param actionID the action ID which will move to
     *  @param actionIDTag the action tag that will receive [actionID] through it
     **/
    fun setLocaleAndRestart(
        activity: Activity?,
        locale: Locale,
        actionIDTag: String,
        @IdRes actionID: Int
    ) {
        check(activity != null) { "Localization: Activity must be not null" }
        setLocalAndApply(activity, locale)
        val intent = Intent(activity, activity.javaClass)
        intent.putExtra(actionIDTag, actionID)
        activity.startActivity(intent)
        activity.finish()
    }

    /**
     *Use this method when you want to move in different nav graph by deepLink
     *  @param activity context of current activity
     *  @param lacale the new locale
     *  @param deepLink the deepLink which will move to
     *  @param deepLinkTag the action tag that will receive [deepLink] through it
     **/
    fun setLocaleAndRestart(
        activity: Activity?,
        locale: Locale,
        deepLink: String,
        deepLinkTag: String
    ) {
        check(activity != null) { "Localization: Activity must be not null" }
        setLocalAndApply(activity, locale)
        val intent = Intent(activity, activity.javaClass)
        intent.putExtra(deepLinkTag, deepLink)
        activity.startActivity(intent)
        activity.finish()
    }

    /**
     *Use this method when you want to move in between activities
     *  @param activity context of current activity
     *  @param lacale the new locale
     *  @param activityClass the activity class name will move to
     **/
    fun setLocaleAndRestart(
        activity: Activity?,
        locale: Locale,
        activityClass: Class<Any>
    ) {
        check(activity != null) { "Localization: Activity must be not null" }
        setLocalAndApply(activity, locale)
        val intent = Intent(activity, activityClass)
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
        application.registerActivityLifecycleCallbacks(
            LocalHelperActivityLifecycleCallbacks {
                applyForActivity(it)
            }
        )
    }

    private fun setLocalAndApply(context: Context, locale: Locale) {
        LocalizationPrefsDataSource(context).setLocale(locale)
        LocalizationUtils.applyLocale(context, locale)
    }

    /**
     * store locale in preferences
     */
    private fun setLocal(context: Context, locale: Locale) {
        LocalizationPrefsDataSource(context).setLocale(locale)
    }

    /**
     * change resources
     */
    private fun applyLocale(context: Context) {
        LocalizationUtils.applyLocale(context, LocalizationPrefsDataSource(context).getLocale())
    }

    fun init(context: Application) {
        LocalizationUtils.applyLocale(context, LocalizationPrefsDataSource(context).getLocale())
    }

    private fun applyForActivity(activity: Activity) {
        applyLocale(activity)
    }

}

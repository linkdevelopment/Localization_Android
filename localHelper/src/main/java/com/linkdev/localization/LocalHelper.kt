package com.linkdev.localization

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import com.linkdev.localization.shared_prefrences.PreferencesDataSource
import java.util.*


object LocalHelper {

    /**
     * Please note when call [setLocal] you must restart your App by yourself to be updated by new local
     * Or call [setLocaleAndRestart]
     */
    @JvmOverloads
    fun setLocale(context: Context, language: String, country: String = "") {
        setLocale(context, Locale(language, country))
    }


    private fun setLocale(context: Context, locale: Locale) {
        setLocalAndApply(context, locale)
    }

    /**
     *Use this method when you want change app language with no action just recreate activity
     *  @param activity context of current activity
     *  @param lacale the new locale
     **/
    fun setLocaleAndRestart(activity: Activity?, locale: Locale) {
        check(activity != null) { "LocalHelper: Activity must be NOT null" }
        setLocalAndApply(activity, locale)
        activity.intent.replaceExtras(Bundle())
        activity.recreate()
    }

    /**
     *Use this method when you want to move in the same nav graph by action ID
     *  @param activity context of current activity
     *  @param lacale the new locale
     *  @param actionID the action ID which will move to
     *  @param actionIDTag the action tag that will receive [actionID] through it
     **/
    fun setLocaleAndRestart(
        activity: Activity?,
        locale: Locale,
        @IdRes actionID: Int,
        actionIDTag: String
    ) {
        check(activity != null) { "LocalHelper: Activity must be NOT null" }
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
        check(activity != null) { "LocalHelper: Activity must be NOT null" }
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
        check(activity != null) { "LocalHelper: Activity must be NOT null" }
        setLocalAndApply(activity, locale)
        val intent = Intent(activity, activityClass)
        activity.startActivity(intent)
        activity.finish()
    }

    fun getLocale(context: Context): Locale {
        return PreferencesDataSource(context).getLocale()
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
        PreferencesDataSource(context).setLocale(locale)
        UpdateLocaleUtils.applyLocale(context, locale)
    }

    /**
     * store locale in preferences
     */
    private fun setLocal(context: Context, locale: Locale) {
        PreferencesDataSource(context).setLocale(locale)
    }

    /**
     * change resources
     */
    private fun applyLocale(context: Context) {
        UpdateLocaleUtils.applyLocale(context, PreferencesDataSource(context).getLocale())
    }

    fun init(context: Application) {
        UpdateLocaleUtils.applyLocale(context, PreferencesDataSource(context).getLocale())
    }

    private fun applyForActivity(activity: Activity) {
        applyLocale(activity)
    }

}

package com.linkdev.localization

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import com.linkdev.localization.data.models.ErrorMessages.MSG_ACTIVITY_NULL
import com.linkdev.localization.data.models.ErrorMessages.MSG_PREFS_NULL
import com.linkdev.localization.data.models.Locales
import com.linkdev.localization.data.shared_prefrences.LocalizationPrefsDataSource
import com.linkdev.localization.utils.LaunchUtils
import com.linkdev.localization.utils.LocaleContextWrapper
import com.linkdev.localization.utils.LocalizationUtils
import java.util.*


object Localization {

    private var localizationPrefsDataSource: LocalizationPrefsDataSource? = null

    /**
     * Will be used to update the resources when configuration changed
     */
    fun initialize(application: Application) {
        localizationPrefsDataSource = LocalizationPrefsDataSource(application.applicationContext)
        application.registerComponentCallbacks(
            LocalizationApplicationCallback(application)
        )
    }

    /**
     * Called to handle the language and configuration changes, but leaves the application restart to the consumer app
     * and perform the following actions
     * -update the prefs with the new lang
     * -update the resources configuration
     * @param context context of current activity
     * @param locale new locale
     */
    fun setLocale(context: Context, locale: Locale) {
        setLocalAndApply(context, locale)
    }


    /**
     *-Reverse the language, if the previous language was Arabic, then it will be modified to English the vice versa and perform the following actions,
     *-update the prefs
     *-update the resources configuration and restart app with new lang
     *@param currentActivity context of current activity
     *@param destinationActivityClass new activity class will navigate to
     *@param bundle pass data between [currentActivity] and [destinationActivityClass]
     *@param flags the intent flags
     **/
    fun <T : Activity> reverseLangAndRestart(
        currentActivity: Activity?, destinationActivityClass: Class<T>,
        bundle: Bundle? = null,
        flags: Int? = null
    ) {
        check(currentActivity != null) { MSG_ACTIVITY_NULL }
        if (getLanguage() == Locales.English.language
        )
            setLocalAndApply(currentActivity, Locales.Arabic)
        else
            setLocalAndApply(currentActivity, Locales.English)

        LaunchUtils.startActivity(currentActivity, destinationActivityClass, bundle, flags)
    }

    /**
     * Will check on the saved language and perform the following actions
     * 1-If the new and saved locale are the same the method will return and will not perform any action
     *
     * 2-If the new and saved local are different,
     * -Update the prefs with the new lang
     * -Update the resources configuration
     * -And recreate activity with new lang
     *  @param currentActivity context of current activity
     *  @param locale the new locale
     *  @param bundle pass data to [currentActivity]
     **/
    fun setLocaleAndRecreate(currentActivity: Activity?, locale: Locale, bundle: Bundle? = null) {
        check(currentActivity != null) { MSG_ACTIVITY_NULL }
        if (!isDifferentLocale(locale.language)) return

        setLocalAndApply(currentActivity, locale)
        LaunchUtils.recreateActivity(currentActivity, bundle)
    }

    /**
     * Will check on the saved language and perform the following actions
     * 1-If the new and saved locale are the same the method will return and will not perform any action
     *
     * 2-If the new and saved local are different,
     * -update the prefs with the new lang
     * -update the resources configuration
     * -navigate to the new activity and restart app
     * @param currentActivity context of current activity
     * @param locale the new locale
     * @param destinationActivityClass new activity class will navigate to
     * @param bundle pass data between [currentActivity] and [destinationActivityClass]
     * @param flags the intent flags
     **/
    fun <T : Activity> setLocaleAndRestart(
        currentActivity: Activity?,
        locale: Locale,
        destinationActivityClass: Class<T>,
        bundle: Bundle? = null,
        flags: Int? = null
    ) {
        check(currentActivity != null) { MSG_ACTIVITY_NULL }
        if (!isDifferentLocale(locale.language)) return
        setLocalAndApply(currentActivity, locale)
        LaunchUtils.startActivity(currentActivity, destinationActivityClass, bundle, flags)
    }


    /**
     * Will store locale in prefs and
     * Update resources with new locale
     */
    private fun setLocalAndApply(context: Context, locale: Locale) {
        check(localizationPrefsDataSource != null) { MSG_PREFS_NULL }
        localizationPrefsDataSource?.setLocale(locale)
        LocalizationUtils.applyLocale(context, locale)
    }

    /**
     * Will check saved and new locale and returns {true} if they are the same
     */
    private fun isDifferentLocale(newLang: String): Boolean {
        return newLang != getLanguage()
    }

    /**
     * Will create configuration context with save locale
     * to be passed to {attachBaseContext} of consumer activity
     */
    fun onAttach(context: Context): ContextWrapper {
        return LocaleContextWrapper.wrap(
            context,
            getLanguage()
        )
    }

    /**
     * Will return the save locale
     */
    fun getLocale(): Locale {
        check(localizationPrefsDataSource != null) { MSG_PREFS_NULL }
        return localizationPrefsDataSource!!.getLocale()
    }

    /**
     * Will return the save language
     */
    fun getLanguage(): String {
        check(localizationPrefsDataSource != null) { MSG_PREFS_NULL }
        return localizationPrefsDataSource!!.getLanguage()
    }
}

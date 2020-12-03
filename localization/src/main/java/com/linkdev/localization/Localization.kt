/**
Copyright (C) 2020 Link Development

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 **/
package com.linkdev.localization

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import com.linkdev.localization.data.models.ErrorMessages.MSG_ACTIVITY_NULL
import com.linkdev.localization.data.models.ErrorMessages.MSG_PREFS_NULL
import com.linkdev.localization.data.models.LocalizationLocale
import com.linkdev.localization.data.shared_prefrences.LocalizationPrefsDataSource
import com.linkdev.localization.utils.LaunchUtils
import com.linkdev.localization.utils.LocaleContextWrapper
import com.linkdev.localization.utils.LocalizationLogger
import com.linkdev.localization.utils.LocalizationUtils
import java.util.*


object Localization {

    private var localizationPrefsDataSource: LocalizationPrefsDataSource? = null

    /**
     * Call [initialize] method in Application class of consumer APP,
     * Will register component callbacks to update the resources when configuration changed.
     * @param application the object of Application class
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
     * Called to handle the language and configuration changes, but leaves the application restart to the consumer app
     * and perform the following actions
     * -update the prefs with the new lang
     * -update the resources configuration
     * @param context context of current activity
     * @param localizationLocale the new locale value from custom [LocalizationLocale] enum class
     */
    fun setLocale(context: Context, localizationLocale: LocalizationLocale) {
        setLocalAndApply(context, localizationLocale)
    }


    /**
     *-Reverse the language, if the previous language was Arabic, then it will be modified to English the vice versa and perform the following actions,
     *-update the prefs
     *-update the resources configuration and restart app with new lang
     *@param currentActivity the object of the current activity
     *@param destinationActivityClass new activity class will navigate to
     *@param bundle pass data between [currentActivity] and [destinationActivityClass]
     *@param flags the intent flags
     **/
    fun <T : Activity> reverseLangAndRestart(
        currentActivity: Activity?, destinationActivityClass: Class<T>,
        bundle: Bundle? = null,
        flags: Int? = null
    ) {
        check(currentActivity != null) { LocalizationLogger.error(msg = MSG_ACTIVITY_NULL) }
        if (getLanguage() == LocalizationLocale.English.locale.language)
            setLocalAndApply(currentActivity, LocalizationLocale.Arabic.locale)
        else if (getLanguage() == LocalizationLocale.Arabic.locale.language)
            setLocalAndApply(currentActivity, LocalizationLocale.English.locale)

        LaunchUtils.startActivity(currentActivity, destinationActivityClass, bundle, flags)
    }

    /**
     * Will check on the saved language and perform the following actions
     * 1-If the new and saved locale are the same the method will return and will not perform any action
     *
     * 2-If the new and saved local are different,
     * -update the prefs with the new lang
     * -update the resources configuration
     * -navigate to the new activity and restart app
     * @param currentActivity the object of the current activity
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
        check(currentActivity != null) { LocalizationLogger.error(msg = MSG_ACTIVITY_NULL) }
        if (!isDifferentLocale(locale.language)) return
        setLocalAndApply(currentActivity, locale)
        LaunchUtils.startActivity(currentActivity, destinationActivityClass, bundle, flags)
    }

    /**
     * Will check on the saved language and perform the following actions
     * 1-If the new and saved locale are the same the method will return and will not perform any action
     *
     * 2-If the new and saved local are different,
     * -update the prefs with the new lang
     * -update the resources configuration
     * -navigate to the new activity and restart app
     * @param currentActivity the object of the current activity
     * @param localizationLocale the new locale value from custom [LocalizationLocale] enum class
     * @param destinationActivityClass new activity class will navigate to
     * @param bundle pass data between [currentActivity] and [destinationActivityClass]
     * @param flags the intent flags
     **/
    fun <T : Activity> setLocaleAndRestart(
        currentActivity: Activity?,
        localizationLocale: LocalizationLocale,
        destinationActivityClass: Class<T>,
        bundle: Bundle? = null,
        flags: Int? = null
    ) {
        check(currentActivity != null) { LocalizationLogger.error(msg = MSG_ACTIVITY_NULL) }
        if (!isDifferentLocale(localizationLocale.locale.language)) return
        setLocalAndApply(currentActivity, localizationLocale)
        LaunchUtils.startActivity(currentActivity, destinationActivityClass, bundle, flags)
    }


    /**
     * Will store locale in prefs and
     * Update resources with new locale
     * @param context context of current activity
     * @param locale the new locale
     */
    private fun setLocalAndApply(context: Context, locale: Locale) {
        check(localizationPrefsDataSource != null) { LocalizationLogger.error(msg = MSG_PREFS_NULL) }
        localizationPrefsDataSource?.setLocale(locale)
        LocalizationUtils.applyLocale(context, locale)
    }

    /**
     * Will store locale in prefs and
     * Update resources with new locale
     * @param context context of current activity
     * @param localizationLocale the new locale value from custom [LocalizationLocale] enum class
     */
    private fun setLocalAndApply(context: Context, localizationLocale: LocalizationLocale) {
        check(localizationPrefsDataSource != null) { LocalizationLogger.error(msg = MSG_PREFS_NULL) }
        localizationPrefsDataSource?.setLocale(localizationLocale.locale)
        LocalizationUtils.applyLocale(context, localizationLocale.locale)
    }

    /**
     * Will check saved and new locale and returns {true} if they are the same
     * @param newLang the language will be compared with stored language
     */
    private fun isDifferentLocale(newLang: String): Boolean {
        return newLang != getLanguage()
    }

    /**
     * Will create configuration context with save locale
     * to be passed to {attachBaseContext} of consumer activity
     * And call applyOverrideConfiguration(Configuration) to apply the new configuration to resources with the new {contextWrapper}
     * @param currentActivity the object of the current activity
     * @param context context of current activity
     */
    fun onAttach(currentActivity: Activity, context: Context): ContextWrapper {
        val contextWrapper = LocaleContextWrapper.wrap(
            context,
            getLanguage()
        )
        currentActivity.applyOverrideConfiguration(contextWrapper.resources.configuration)
        return contextWrapper
    }

    /**
     * Will return the saved locale
     */
    fun getLocale(): Locale {
        check(localizationPrefsDataSource != null) { LocalizationLogger.error(msg = MSG_PREFS_NULL) }
        return localizationPrefsDataSource!!.getLocale()
    }

    /**
     * Will return the saved custom enum LocalizationLocale
     */
    fun getLocalizationLocale(): LocalizationLocale {
        check(localizationPrefsDataSource != null) { LocalizationLogger.error(msg = MSG_PREFS_NULL) }
        return LocalizationLocale.getLocale(localizationPrefsDataSource!!.getLanguage())
    }

    /**
     * Will return the saved language
     */
    fun getLanguage(): String {
        check(localizationPrefsDataSource != null) { LocalizationLogger.error(msg = MSG_PREFS_NULL) }
        return localizationPrefsDataSource!!.getLanguage()
    }
}

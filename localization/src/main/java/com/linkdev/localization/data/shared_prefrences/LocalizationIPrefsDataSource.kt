package com.linkdev.localization.data.shared_prefrences

import com.linkdev.localization.Localization
import java.util.*

/**
 *  Interface to be used by [Localization] for storing a Locale and its complementary data.
 */
internal interface LocalizationIPrefsDataSource {
    /**
     * Call to return the saved locale from prefs
     */
    fun getLocale(): Locale

    /**
     * Call to return the saved language
     */
    fun getLanguage(): String

    /**
     * Call to save locale in prefs
     * @param locale the new locale will be saved
     */
    fun setLocale(locale: Locale)

}

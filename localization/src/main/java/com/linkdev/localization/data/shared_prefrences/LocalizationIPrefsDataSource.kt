package com.linkdev.localization.data.shared_prefrences

import com.linkdev.localization.Localization
import java.util.*

/**
 *  Interface to be used by [Localization] for storing a Locale and its complementary data.
 */
internal interface LocalizationIPrefsDataSource {
    fun getLocale(): Locale
    fun getLanguage(): String
    fun setLocale(locale: Locale)

}

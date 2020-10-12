package com.linkdev.localization.shared_prefrences

import com.linkdev.localization.LocalHelper
import java.util.*

/**
 *  Interface to be used by [LocalHelper] for storing a Locale and its complementary data.
 */
interface IPreferencesDataSource {
    fun getLocale(): Locale
    fun persistLocale(locale: Locale)

}

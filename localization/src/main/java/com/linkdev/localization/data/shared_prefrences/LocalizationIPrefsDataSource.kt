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

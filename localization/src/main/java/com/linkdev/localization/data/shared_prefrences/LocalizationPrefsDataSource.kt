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

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONObject
import java.util.*

internal class LocalizationPrefsDataSource(context: Context) : LocalizationIPrefsDataSource {
    private val prefs: SharedPreferences

    init {
        prefs = context.getSharedPreferences(LOCALIZATION_APP_PREFERENCES, Context.MODE_PRIVATE)

    }

    companion object {
        private const val LOCALIZATION_LANGUAGE_KEY = "LOCALIZATION_LANGUAGE_KEY"
        private const val LOCALIZATION_APP_PREFERENCES = "LOCALIZATION_APP_PREFERENCES"
        private const val LOCALIZATION_LANGUAGE_JSON_KEY = "LOCALIZATION_LANGUAGE_JSON_KEY"
        private const val LOCALIZATION_COUNTRY_JSON_KEY = "LOCALIZATION_COUNTRY_JSON_KEY"
    }

    /**
     * Will return the saved locale from prefs
     * And if it is null or blank will return default locale
     */
    override fun getLocale(): Locale {
        return if (!prefs.getString(LOCALIZATION_LANGUAGE_KEY, null).isNullOrBlank()) {
            val json = JSONObject(prefs.getString(LOCALIZATION_LANGUAGE_KEY, null)!!)
            val language = json.getString(LOCALIZATION_LANGUAGE_JSON_KEY)
            val country = json.getString(LOCALIZATION_COUNTRY_JSON_KEY)
            Locale(language, country)
        } else {
            Locale.getDefault()
        }
    }

    /**
     * Will return the saved language
     */
    override fun getLanguage(): String {
        return getLocale().language
    }

    /**
     * Call to save language and country in prefs
     * @param locale the new locale will be saved
     */
    override fun setLocale(locale: Locale) {
        val json = JSONObject().apply {
            put(LOCALIZATION_LANGUAGE_JSON_KEY, locale.language)
            put(LOCALIZATION_COUNTRY_JSON_KEY, locale.country)
        }
        prefs.edit().putString(LOCALIZATION_LANGUAGE_KEY, json.toString()).apply()
    }


}

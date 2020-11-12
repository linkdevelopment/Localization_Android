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

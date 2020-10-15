package com.linkdev.localization.shared_prefrences

import android.content.Context
import org.json.JSONObject
import java.util.*

class PreferencesDataSource (
    context: Context,
    private val defaultLocale: Locale = Locale.getDefault()
) : IPreferencesDataSource {

    private val prefs = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

    override fun getLocale(): Locale {
        return if (!prefs.getString(LANGUAGE_KEY, null).isNullOrBlank()) {
            val json = JSONObject(prefs.getString(LANGUAGE_KEY, null)!!)
            val language = json.getString(LANGUAGE_JSON_KEY)
            val country = json.getString(COUNTRY_JSON_KEY)
            Locale(language, country)
        } else {
            defaultLocale
        }
    }

    override fun setLocale(locale: Locale) {
        val json = JSONObject().apply {
            put(LANGUAGE_JSON_KEY, locale.language)
            put(COUNTRY_JSON_KEY, locale.country)
        }
        prefs.edit().putString(LANGUAGE_KEY, json.toString()).apply()
    }



    companion object {
        private const val LANGUAGE_KEY = "language_key"
        private const val APP_PREFERENCES = "APP_PREFERENCES"
        private const val LANGUAGE_JSON_KEY = "language"
        private const val COUNTRY_JSON_KEY = "country"
    }
}

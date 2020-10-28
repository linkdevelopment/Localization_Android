package com.linkdev.localization.data.shared_prefrences

import android.content.Context
import android.content.SharedPreferences
import com.linkdev.localization.utils.ErrorMessages
import org.json.JSONObject
import java.util.*

class LocalizationPrefsDataSource : LocalizationIPrefsDataSource {
    companion object {
        private var instance: LocalizationPrefsDataSource? = null
        private lateinit var prefs: SharedPreferences
        private lateinit var mContext: Context
        private const val LOCALIZATION_LANGUAGE_KEY = "LOCALIZATION_LANGUAGE_KEY"
        private const val LOCALIZATION_APP_PREFERENCES = "LOCALIZATION_APP_PREFERENCES"
        private const val LOCALIZATION_LANGUAGE_JSON_KEY = "LOCALIZATION_LANGUAGE_JSON_KEY"
        private const val LOCALIZATION_COUNTRY_JSON_KEY = "LOCALIZATION_COUNTRY_JSON_KEY"
        fun init(context: Context): LocalizationPrefsDataSource {
            if (instance == null) {
                mContext = context
                prefs = mContext.getSharedPreferences(
                    LOCALIZATION_APP_PREFERENCES,
                    Context.MODE_PRIVATE
                )
                instance = LocalizationPrefsDataSource()

            }

            return instance!!
        }
    }

    override fun getLocale(): Locale {
        check(instance != null) { ErrorMessages.MSG_PREFS_NULL }

        return if (!prefs.getString(LOCALIZATION_LANGUAGE_KEY, null).isNullOrBlank()) {
            val json = JSONObject(prefs.getString(LOCALIZATION_LANGUAGE_KEY, null)!!)
            val language = json.getString(LOCALIZATION_LANGUAGE_JSON_KEY)
            val country = json.getString(LOCALIZATION_COUNTRY_JSON_KEY)
            Locale(language, country)
        } else {
            Locale.getDefault()
        }
    }

    override fun getLanguage(): String {
        return getLocale().language
    }

    override fun setLocale(locale: Locale) {
        check(instance != null) { ErrorMessages.MSG_PREFS_NULL }

        val json = JSONObject().apply {
            put(LOCALIZATION_LANGUAGE_JSON_KEY, locale.language)
            put(LOCALIZATION_COUNTRY_JSON_KEY, locale.country)
        }
        prefs.edit().putString(LOCALIZATION_LANGUAGE_KEY, json.toString()).apply()
    }


}

package com.linkdev.localization.utils

import android.content.Context
import android.text.TextUtils
import com.linkdev.localization.data.models.ErrorMessages.MSG_LOCALIZATION_UTILS_UPDATE_RES_ERROR
import java.util.*


internal object LocalizationUtils {

    /**
     * Will call [changeAppLanguage] method to apply new locale to resources
     * @param context current context
     * @param locale the new locale
     */
    fun applyLocale(context: Context, locale: Locale) {
        changeAppLanguage(
            context,
            locale.language
        )
    }

    /**
     * Will set locale with new language and update resources
     * @param context current context
     * @param newLang the new language
     */
    private fun changeAppLanguage(ctx: Context, newLang: String) {
        newLang.apply { this.toLowerCase(Locale.getDefault()) }
        try {
            if (!TextUtils.isEmpty(newLang)) {
                val res = ctx.applicationContext.resources
                val config = res.configuration
                val locale = Locale(newLang)
                Locale.setDefault(locale)
                config.setLocale(locale)
            }
        } catch (e: Exception) {
            LocalizationLogger.error(e, MSG_LOCALIZATION_UTILS_UPDATE_RES_ERROR)
        }
    }


}

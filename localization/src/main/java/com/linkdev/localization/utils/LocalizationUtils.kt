package com.linkdev.localization.utils

import android.content.Context
import android.text.TextUtils
import java.util.*


object LocalizationUtils {

    fun applyLocale(context: Context, locale: Locale) {
        changeAppLanguage(
            context,
            locale.language
        )
    }

    private fun changeAppLanguage(ctx: Context, languageToLoad: String) {
        languageToLoad.apply { this.toLowerCase(Locale.getDefault()) }
        try {
            if (!TextUtils.isEmpty(languageToLoad)) {
                val res = ctx.applicationContext.resources
                val config = res.configuration
                val locale = Locale(languageToLoad)
                Locale.setDefault(locale)
                config.setLocale(locale)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}

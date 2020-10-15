package com.linkdev.localization

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import java.util.*


object UpdateLocaleUtils {

    fun applyLocale(context: Context, locale: Locale) {
        updateResources(context, locale)
    }

    @Suppress("DEPRECATION")
    private fun updateResources(context: Context, locale: Locale) {
        Locale.setDefault(locale)

        val res = context.resources
        val current = res.configuration.getLocaleCompat()

        if (current == locale) return

        val config = Configuration(res.configuration)
        when {
            isAtLeastSdkVersion(Build.VERSION_CODES.N) -> setLocaleForApi24(config, locale)
            else -> config.locale = locale
        }
        res.updateConfiguration(config, res.displayMetrics)
    }

    @SuppressLint("NewApi")
    private fun setLocaleForApi24(config: Configuration, locale: Locale) {
        val localeList = LocaleList(locale)
        LocaleList.setDefault(localeList)
        config.setLocales(localeList)

    }
}

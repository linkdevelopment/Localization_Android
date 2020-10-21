package com.linkdev.localization.utils


import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.LocaleList
import java.util.*

class LocaleContextWrapper(base: Context?) : ContextWrapper(base) {
    companion object {
        @JvmStatic
        fun wrap(context: Context, language: String): ContextWrapper {
            var context = context
            val res = context.resources
            val configuration = res.configuration
            val newLocale = Locale(language)
            context = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                configuration.apply {
                    val localeList = LocaleList(newLocale)
                    LocaleList.setDefault(localeList)

                    setLocale(newLocale)
                    setLocales(localeList)
                }

                context.createConfigurationContext(configuration)
            } else {
                configuration.setLocale(newLocale)

                context.createConfigurationContext(configuration)
            }
            return LocaleContextWrapper(
                context
            )
        }
    }
}
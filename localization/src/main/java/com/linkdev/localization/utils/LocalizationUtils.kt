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

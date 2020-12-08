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
package com.linkdev.localization

import android.content.ComponentCallbacks
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import java.util.*

internal class LocalizationApplicationCallback(private val context: Context) :
    ComponentCallbacks {
    /*
     *When configuration changed in device "User changes device lang or make device landscape...etc"
     * we need to update resources in our app with stored locale
     */
    override fun onConfigurationChanged(newConfig: Configuration) {
        onConfigurationChange(context, newConfig)
    }

    override fun onLowMemory() {
    }

    /*
     *Check if saved language isNullOrBlank and apply saved locale to new configuration
     * @param context this current context
     * @param newConfig new configuration
     */
    private fun onConfigurationChange(context: Context, newConfig: Configuration) {
        val configuration: Configuration? = newConfig
        if (Localization.getLanguage().isNotEmpty()) {
            val newLocale = Locale(Localization.getLanguage())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                configuration?.apply {
                    val localeList = LocaleList(newLocale)
                    LocaleList.setDefault(localeList)
                    setLocale(newLocale)
                    setLocales(localeList)
                }
            } else {
                configuration?.setLocale(newLocale)
            }
        }
    }
}

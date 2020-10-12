package com.linkdev.localizationHelper

import android.app.Application
import com.linkdev.localization.LocalHelper
import com.linkdev.localization.Locales
import com.linkdev.localization.shared_prefrences.PreferencesDataSource

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val store = PreferencesDataSource(this, Locales.English)
        LocalHelper.init(this)
    }


}

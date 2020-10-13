package com.linkdev.localizationHelper

import android.app.Application
import com.linkdev.localization.LocalHelper
import com.linkdev.localization.Locales

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        LocalHelper.init(this, Locales.Arabic)
    }


}

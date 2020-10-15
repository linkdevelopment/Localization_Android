package com.linkdev.localizationHelper

import android.app.Application
import com.linkdev.localization.LocalHelper

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        LocalHelper.initialize(this)

//        LocalHelper.init(this, Locales.Arabic)
//        this.registerActivityLifecycleCallbacks(
//            TestActivityLifecycleCallbacks {
//                applyForActivity(it)
//            }
//        )

    }



}




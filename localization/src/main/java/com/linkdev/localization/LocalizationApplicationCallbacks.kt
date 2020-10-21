package com.linkdev.localization

import android.content.ComponentCallbacks
import android.content.res.Configuration

class LocalizationApplicationCallbacks(private val callback: (Configuration) -> Unit) :
    ComponentCallbacks {

    override fun onConfigurationChanged(newConfig: Configuration) {
        callback.invoke(newConfig)
    }

    override fun onLowMemory() {
    }
}

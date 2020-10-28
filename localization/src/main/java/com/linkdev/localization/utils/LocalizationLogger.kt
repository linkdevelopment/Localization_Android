package com.linkdev.localization.utils

import android.util.Log
import com.linkdev.localization.BuildConfig

object LocalizationLogger {
    private const val LOCALIZATION = "LOCALIZATION"
    fun error(exception: Throwable) {
        if (BuildConfig.DEBUG) {
            exception.message?.let { Log.e(LOCALIZATION, "$LOCALIZATION : $it", exception) }
        }
    }

    fun warn(exception: Throwable) {
        if (BuildConfig.DEBUG) {
            exception.message?.let { Log.w(LOCALIZATION, "$LOCALIZATION : $it") }
        }
    }

    fun info(exception: Throwable) {
        if (BuildConfig.DEBUG) {
            exception.message?.let { Log.i(LOCALIZATION, "$LOCALIZATION : $it") }
        }
    }
}
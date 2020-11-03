package com.linkdev.localization.utils

import android.util.Log

object LocalizationLogger {
    private const val LOCALIZATION = "LOCALIZATION"
    fun error(exception: Throwable) {
        exception.message?.let { Log.e(LOCALIZATION, "$LOCALIZATION : $it", exception) }
    }


    fun warn(exception: Throwable) {
        exception.message?.let { Log.w(LOCALIZATION, "$LOCALIZATION : $it") }

    }

    fun info(exception: Throwable) {
        exception.message?.let { Log.i(LOCALIZATION, "$LOCALIZATION : $it") }

    }
}
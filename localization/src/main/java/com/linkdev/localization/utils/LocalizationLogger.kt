package com.linkdev.localization.utils

import android.util.Log

/**
 * Use this logger class to print stack trace and log its message
 */
internal object LocalizationLogger {
    private const val LOCALIZATION = "LOCALIZATION"
    fun error(exception: Throwable, msg: String) {
        exception.printStackTrace()
        Log.e(LOCALIZATION, msg)
    }


    fun warn(exception: Throwable, msg: String) {
        exception.printStackTrace()
        Log.w(LOCALIZATION, msg)
    }

    fun info(exception: Throwable, msg: String) {
        exception.printStackTrace()
        Log.i(LOCALIZATION, msg)
    }
}
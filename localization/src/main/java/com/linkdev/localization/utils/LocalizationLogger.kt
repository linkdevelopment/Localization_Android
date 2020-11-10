package com.linkdev.localization.utils

import android.util.Log

/**
 * Use this logger class to print stack trace and log its message
 */
internal object LocalizationLogger {
    private const val LOCALIZATION = "LOCALIZATION"

    /**
     * Call to print stack trace and specific error message with tag "LOCALIZATION"
     * @param exception the exception object
     * @param msg the error message
     */
    fun error(exception: Throwable? = null, msg: String) {
        exception?.printStackTrace()
        Log.e(LOCALIZATION, msg)
    }

    /**
     * Call to print specific warning message with tag "LOCALIZATION"
     * @param msg the warning message
     */
    fun warn(msg: String) {
        Log.w(LOCALIZATION, msg)
    }

    /**
     * Call to print specific info message with tag "LOCALIZATION"
     * @param msg the info message
     */
    fun info(msg: String) {
        Log.i(LOCALIZATION, msg)
    }
}
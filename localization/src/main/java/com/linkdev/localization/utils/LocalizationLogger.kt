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
     * Call to print stack trace and specific warning message with tag "LOCALIZATION"
     * @param exception the exception object
     * @param msg the warning message
     */
    fun warn(exception: Throwable, msg: String) {
        exception.printStackTrace()
        Log.w(LOCALIZATION, msg)
    }

    /**
     * Call to print stack trace and specific info message with tag "LOCALIZATION"
     * @param exception the exception object
     * @param msg the info message
     */
    fun info(exception: Throwable, msg: String) {
        exception.printStackTrace()
        Log.i(LOCALIZATION, msg)
    }
}
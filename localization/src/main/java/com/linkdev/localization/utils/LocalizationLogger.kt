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
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

import android.app.Activity
import android.content.Intent
import android.os.Bundle

internal object LaunchUtils {
    /**
     *Will create intent with flags or bundle, start new activity[destinationActivityClass] and finish [currentActivity]
     *@param currentActivity the object of the current activity
     *@param destinationActivityClass new activity class will navigate to
     *@param bundle the new arguments
     *@param flags the intent flags
     */
    fun <T : Activity> startActivity(
        currentActivity: Activity,
        destinationActivityClass: Class<T>,
        bundle: Bundle? = null,
        flags: Int? = null
    ) {
        val intent = Intent(currentActivity, destinationActivityClass)
        if (flags != null) {
            intent.flags = flags
        }
        if (bundle != null)
            intent.putExtras(bundle)
        currentActivity.startActivity(intent)
        currentActivity.finish()
    }

}
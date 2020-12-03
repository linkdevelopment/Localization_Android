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
package com.linkdev.localization.data.models

internal object ErrorMessages {
    const val MSG_ACTIVITY_NULL = "Localization: Activity must not be null"
    const val MSG_PREFS_NULL =
        "Localization: LocalizationPrefsDataSource is not initialized, Please call the initialize method in the application class"
    const val MSG_LOCALIZATION_UTILS_UPDATE_RES_ERROR =
        "An undefined error occurred while updating the resources."
}
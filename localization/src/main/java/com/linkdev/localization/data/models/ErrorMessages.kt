package com.linkdev.localization.data.models

internal object ErrorMessages {
    const val MSG_ACTIVITY_NULL = "Localization: Activity must be not null"
    const val MSG_PREFS_NULL = "Localization: LocalizationPrefsDataSource not initialized, " +
            "Please initialize it first in application class "
    const val MSG_LOCALIZATION_UTILS_UPDATE_RES_ERROR="Something went wrong when update resources," +
            " Please check [changeAppLanguage] method in LocalizationUtils class "
}
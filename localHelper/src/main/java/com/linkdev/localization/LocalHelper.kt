package com.linkdev.localization

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.annotation.IdRes
import com.linkdev.localization.shared_prefrences.IPreferencesDataSource
import com.linkdev.localization.shared_prefrences.PreferencesDataSource
import java.util.*


class LocalHelper private constructor(
    private val iPreferencesDataSource: IPreferencesDataSource,
    private val updateLocaleUtils: UpdateLocaleUtils
) {

    /**
     * Please note when call [setLocal] you must restart your App by yourself to be updated by new local
     * Or call [setLocaleAndRestart]
     */
    @JvmOverloads
    fun setLocale(context: Context, language: String, country: String = "") {
        setLocale(context, Locale(language, country))
    }


    private fun setLocale(context: Context, locale: Locale) {
        setLocalAndApply(context, locale)
    }

    fun setLocaleAndRestart(activity: Activity?, locale: Locale) {
        check(activity != null) { "LocalHelper: Activity must be NOT null" }
        setLocal(locale)
        activity.recreate()
    }

    /**
     *Use this method [setLocaleAndRestart]  when you want to move in the same nav graph
     **/
    fun setLocaleAndRestart(
        activity: Activity?,
        locale: Locale,
        @IdRes actionID: Int,
        actionIDTag: String
    ) {
        check(activity != null) { "LocalHelper: Activity must be NOT null" }
        setLocal(locale)
        val intent = Intent(activity, activity.javaClass)
        intent.putExtra(actionIDTag, actionID)
        activity.startActivity(intent)
        activity.finish()
    }

    fun getLocale(): Locale {
        return iPreferencesDataSource.getLocale()
    }


    fun getLanguage(): String {
        return getLocale().language
    }


    private fun initialize(application: Application) {
        application.registerActivityLifecycleCallbacks(
            LocalHelperActivityLifecycleCallbacks {
                applyForActivity(it)
            }
        )
    }

    private fun setLocalAndApply(context: Context, locale: Locale) {
        iPreferencesDataSource.setLocale(locale)
        updateLocaleUtils.applyLocale(context, locale)
    }

    private fun setLocal(locale: Locale) {
        iPreferencesDataSource.setLocale(locale)
    }

    private fun applyLocale(context: Context) {
        updateLocaleUtils.applyLocale(context, iPreferencesDataSource.getLocale())
    }

    private fun applyForActivity(activity: Activity) {
        applyLocale(activity)
    }

    companion object {
        private lateinit var instance: LocalHelper

        @JvmStatic
        fun getInstance(): LocalHelper {
            check(::instance.isInitialized) { "Please initialize localHelper in your App class first " }
            return instance
        }

        @JvmStatic
        @JvmOverloads
        fun init(
            application: Application,
            defaultLocale: Locale = Locale.getDefault()
        ): LocalHelper {
            return init(application, PreferencesDataSource(application, defaultLocale))
        }

        private fun init(application: Application, store: IPreferencesDataSource): LocalHelper {
            check(!::instance.isInitialized) { "localHelper: already initialized" }
            val localHelper = LocalHelper(store, UpdateLocaleUtils())
            localHelper.initialize(application)
            instance = localHelper
            return localHelper
        }


    }
}

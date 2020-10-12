package com.linkdev.localization

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.linkdev.localization.shared_prefrences.IPreferencesDataSource
import com.linkdev.localization.shared_prefrences.PreferencesDataSource
import java.util.*


class LocalHelper private constructor(
    private val iPreferencesDataSource: IPreferencesDataSource,
    private val delegate: UpdateLocaleDelegate
) {

    internal var systemLocale: Locale = defaultLocale

    /**
     * Creates and sets a [Locale] using language, country and variant information.
     *
     * See the [Locale] class description for more information about valid language, country
     * and variant values.
     */
    @JvmOverloads
    fun setLocale(context: Context, language: String, country: String = "", variant: String = "") {
        setLocale(context, Locale(language, country, variant))
    }


    fun setLocale(context: Context, locale: Locale) {
        persistAndApply(context, locale)
    }

    fun setLocaleAndRestart(activity: Activity?, locale: Locale) {
        if (activity == null) {
            throw Exception("LocalHelper: Activity must be NOT null")
        } else {
            persistAndApply(activity, locale)
            activity.recreate()
        }
    }

    /**
     * Returns the active [Locale].
     */
    fun getLocale(): Locale {
        return iPreferencesDataSource.getLocale()
    }


    fun getLanguage(): String {
        return getLocale().language
    }


    internal fun initialize(application: Application) {
        application.registerActivityLifecycleCallbacks(
            LocalHelperActivityLifecycleCallbacks {
                applyForActivity(it)
            }
        )
        application.registerComponentCallbacks(
            LocalHelperApplicationCallbacks {
                processConfigurationChange(application, it)
            }
        )
        val locale = iPreferencesDataSource.getLocale()

        persistAndApply(application, locale)
    }

    private fun persistAndApply(context: Context, locale: Locale) {
        iPreferencesDataSource.persistLocale(locale)
        delegate.applyLocale(context, locale)
    }

    private fun applyLocale(context: Context) {
        delegate.applyLocale(context, iPreferencesDataSource.getLocale())
    }

    private fun processConfigurationChange(context: Context, config: Configuration) {
        systemLocale = config.getLocaleCompat()
        applyLocale(context)

    }

    private fun applyForActivity(activity: Activity) {
        applyLocale(activity)
        activity.resetTitle()
    }

    companion object {
        @SuppressLint("ConstantLocale")
        private val defaultLocale: Locale = Locale.getDefault()

        private lateinit var instance: LocalHelper

        @JvmStatic
        fun getInstance(): LocalHelper {
            check(::instance.isInitialized) { "LocalHelper should be initialized first" }
            return instance
        }


        @JvmStatic
        fun init(application: Application, defaultLanguage: String): LocalHelper {
            return init(application, Locale(defaultLanguage))
        }

        /**
         * Creates and sets up the global instance using a provided locale and the default store.
         */
        @JvmStatic
        @JvmOverloads
        fun init(
            application: Application,
            defaultLocale: Locale = Locale.getDefault()
        ): LocalHelper {
            return init(application, PreferencesDataSource(application, defaultLocale))
        }

        /**
         * Creates and sets up the global instance.
         *
         * This method must be called before any calls to [LocalHelper] and may only be called once.
         */
        @JvmStatic
        fun init(application: Application, store: IPreferencesDataSource): LocalHelper {
            check(!::instance.isInitialized) { "Already initialized" }
            val localHelper = LocalHelper(store, UpdateLocaleDelegate())
            localHelper.initialize(application)
            instance = localHelper
            return localHelper
        }

        internal fun createInstance(
            store: IPreferencesDataSource,
            delegate: UpdateLocaleDelegate
        ): LocalHelper {
            return LocalHelper(store, delegate)
        }
    }
}

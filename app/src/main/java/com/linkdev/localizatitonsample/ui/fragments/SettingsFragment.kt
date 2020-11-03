package com.linkdev.localizatitonsample.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.linkdev.localization.Localization
import com.linkdev.localization.data.models.Locales
import com.linkdev.localization.data.shared_prefrences.LocalizationPrefsDataSource
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.ui.navigation.NavigationActivity
import com.linkdev.localizatitonsample.utils.Constants
import com.linkdev.localizatitonsample.utils.UIUtils
import com.linkdev.localizatitonsample.utils.UIUtils.setToolbar
import kotlinx.android.synthetic.main.settings_fragment.*
import kotlinx.android.synthetic.main.tool_bar_layout.*
import java.util.*

class SettingsFragment : Fragment() {

    private lateinit var mContext: Context
    private lateinit var currentLocale: Locale


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.settings_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return (when (item.itemId) {
            R.id.done -> {
                changeLang(currentLocale)
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null)
            mContext = this.activity as Context

        currentLocale = LocalizationPrefsDataSource.init(mContext).getLocale()
        onSetSelectionCurrentLanguage(currentLocale.language)
        setToolbar(mContext, toolBar, getString(R.string.settings), false)
        setListeners()

    }


    private fun setListeners() {
        tvLangArabic.setOnClickListener { onLangArabicClicked() }
        tvLangEnglish.setOnClickListener { onLangEnglishClicked() }
    }

    private fun onLangEnglishClicked() {
        currentLocale = Locales.English
        onSetSelectionCurrentLanguage(Locales.English.language)
    }

    private fun onLangArabicClicked() {
        currentLocale = Locales.Arabic
        onSetSelectionCurrentLanguage(Locales.Arabic.language)
    }

    private fun onSetSelectionCurrentLanguage(lang: String) {
        when (lang) {
            Locales.Arabic.language -> {
                onChangeLangText(tvLangArabic, tvLangEnglish)

            }
            Locales.English.language -> {
                onChangeLangText(tvLangEnglish, tvLangArabic)

            }
        }
    }

    private fun onChangeLangText(activeLang: TextView, inactiveLang: TextView) {
        UIUtils.clearDrawable(inactiveLang)
        UIUtils.setDrawableEndText(activeLang, R.drawable.ic_green_done)
        activeLang.setTextColor(ContextCompat.getColor(mContext, R.color.colorGreen))
        inactiveLang.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary))
    }

    private fun changeLang(newLocale: Locale) {
        Localization.setLocaleAndRestart(
            activity,
            newLocale,
            NavigationActivity::class.java,
            bundle = getSettingsBundle()
        )
    }

    private fun getSettingsBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString(
            Constants.Extras.CHANGE_LANGUAGE_REDIRECTION,
            Constants.DeepLinks.SETTINGS_PAGE_DEEP_LINK
        )
        return bundle
    }


}
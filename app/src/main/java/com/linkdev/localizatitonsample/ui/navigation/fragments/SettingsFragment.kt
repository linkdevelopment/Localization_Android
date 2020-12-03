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
package com.linkdev.localizatitonsample.ui.navigation.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.linkdev.localization.Localization
import com.linkdev.localization.data.models.LocalizationLocale
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.ui.navigation.NavigationActivity
import com.linkdev.localizatitonsample.utils.Constants
import com.linkdev.localizatitonsample.utils.UIUtils
import com.linkdev.localizatitonsample.utils.UIUtils.setToolbar
import kotlinx.android.synthetic.main.settings_fragment.*
import kotlinx.android.synthetic.main.tool_bar_layout.*

class SettingsFragment : Fragment() {

    private lateinit var mContext: Context
    private lateinit var currentLocale: LocalizationLocale


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

        currentLocale = Localization.getLocalizationLocale()
        onSetSelectionCurrentLanguage(currentLocale)
        setToolbar(mContext, toolBar, getString(R.string.settings), false)
        setListeners()

    }


    private fun setListeners() {
        tvLangArabic.setOnClickListener { onLangArabicClicked() }
        tvLangEnglish.setOnClickListener { onLangEnglishClicked() }
    }

    private fun onLangEnglishClicked() {
        currentLocale = LocalizationLocale.English
        onSetSelectionCurrentLanguage(currentLocale)
    }

    private fun onLangArabicClicked() {
        currentLocale = LocalizationLocale.Arabic
        onSetSelectionCurrentLanguage(currentLocale)
    }

    private fun onSetSelectionCurrentLanguage(lang: LocalizationLocale) {
        when (lang) {
            LocalizationLocale.Arabic -> {
                onChangeLangText(tvLangArabic, tvLangEnglish)

            }
            LocalizationLocale.English -> {
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

    private fun changeLang(newLocale: LocalizationLocale) {
        // TODO: Call [setLocaleAndRestart] to change app language with new locale and restart
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
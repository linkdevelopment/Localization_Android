package com.linkdev.localizatitonsample.ui.navigation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.linkdev.localization.Localization
import com.linkdev.localization.data.models.Locales
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.utils.Constants
import com.linkdev.localizatitonsample.utils.UIUtils.setToolbar
import kotlinx.android.synthetic.main.langs_layout.*
import kotlinx.android.synthetic.main.tool_bar_layout.*

class SettingsFragment : Fragment() {


    val layoutID: Int
        get() = R.layout.settings_fragment

    protected lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutID, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null)
            mContext = this.activity as Context
        setListeners()
        setToolbar(mContext, toolBar, getString(R.string.settings), false)

    }


    private fun setListeners() {
        btnArabicLang.setOnClickListener {
            Localization.setLocaleAndRestart(
                activity,
                Locales.Arabic,
                Constants.DeepLinks.SETTINGS_PAGE_DEEP_LINK,
                Constants.Extras.CHANGE_LANGUAGE_REDIRECTION
            )

        }
        btnEnglishLang.setOnClickListener {
            Localization.setLocaleAndRestart(
                activity,
                Locales.English,
                Constants.DeepLinks.SETTINGS_PAGE_DEEP_LINK,
                Constants.Extras.CHANGE_LANGUAGE_REDIRECTION
            )
        }
    }


}
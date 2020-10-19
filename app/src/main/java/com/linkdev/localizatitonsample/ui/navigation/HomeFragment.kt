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
import com.linkdev.localizatitonsample.utils.UIUtils
import kotlinx.android.synthetic.main.langs_layout.*
import kotlinx.android.synthetic.main.tool_bar_layout.*

class HomeFragment : Fragment() {


    protected lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null)
            mContext = this.activity as Context
        setListeners()
        UIUtils.setToolbar(mContext, toolBar, getString(R.string.home), false)

    }

    private fun setListeners() {
        btnArabicLang.setOnClickListener {
            Localization.setLocaleAndRestart(
                activity,
                Locales.Arabic,
                Constants.Extras.CHANGE_LANGUAGE_REDIRECTION,
                R.id.action_home_to_search_fragment
            )

        }
        btnEnglishLang.setOnClickListener {
            Localization.setLocaleAndRestart(
                activity,
                Locales.English,
                Constants.Extras.CHANGE_LANGUAGE_REDIRECTION,
                R.id.action_home_to_search_fragment
            )

        }
    }


}
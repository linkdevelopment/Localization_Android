package com.linkdev.localizationHelper.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.linkdev.localization.LocalHelper
import com.linkdev.localization.Locales
import com.linkdev.localizationHelper.R
import com.linkdev.localizationHelper.uitils.IToolbar
import kotlinx.android.synthetic.main.settings_fragment.*
import kotlinx.android.synthetic.main.tool_bar_layout.*

class SettingsFragment : Fragment(), IToolbar {

    override var mToolbar: Toolbar?
        get() = toolBar
        set(value) {}

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
        setToolbar(mContext, getString(R.string.settings), false)

    }


    private fun setListeners() {
        btnChangeLang.setOnClickListener {
            if (LocalHelper.getLocale(mContext).equals(Locales.English))
                LocalHelper.setLocaleAndRestart(activity, Locales.Arabic)
            else
                LocalHelper.setLocaleAndRestart(activity, Locales.English)
        }
    }


}
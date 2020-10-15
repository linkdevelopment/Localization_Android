package com.linkdev.localizatitonsample.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.utils.IToolbar
import kotlinx.android.synthetic.main.tool_bar_layout.*

class SearchFragment : Fragment(), IToolbar {

    override var mToolbar: Toolbar?
        get() = toolBar
        set(value) {}

    val layoutID: Int
        get() = R.layout.search_fragment

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
        setToolbar(mContext, getString(R.string.search), false)

    }


}
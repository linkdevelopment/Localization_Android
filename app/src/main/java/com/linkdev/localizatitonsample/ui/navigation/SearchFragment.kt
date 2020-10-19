package com.linkdev.localizatitonsample.ui.navigation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.utils.IToolbar
import com.linkdev.localizatitonsample.utils.UIUtils.setToolbar
import kotlinx.android.synthetic.main.tool_bar_layout.*

class SearchFragment : Fragment() {


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
        setToolbar(mContext, toolBar,getString(R.string.search), false)

    }


}
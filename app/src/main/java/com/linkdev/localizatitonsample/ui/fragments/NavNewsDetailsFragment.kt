package com.linkdev.localizatitonsample.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.linkdev.localization.Localization
import com.linkdev.localization.data.models.Locales
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.ui.navigation.NavigationActivity
import com.linkdev.localizatitonsample.utils.Constants
import com.linkdev.localizatitonsample.utils.UIUtils.setToolbar
import kotlinx.android.synthetic.main.langs_layout.*
import kotlinx.android.synthetic.main.layout_news_details.*
import kotlinx.android.synthetic.main.tool_bar_layout.*
import java.util.*

class NavNewsDetailsFragment : Fragment() {

    companion object {
        private fun getNavNewsDetailsBundle(): Bundle {
            val bundle = Bundle()
            bundle.putInt(
                Constants.Extras.CHANGE_LANGUAGE_REDIRECTION,
                R.id.home
            )
            return bundle
        }

    }

    protected lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null)
            mContext = this.activity as Context
        setToolbar(mContext, toolBar, getString(R.string.newsDetails), false)
        setNewsDetails()
        setListeners()
    }

    private fun setNewsDetails() {
        val args: NavNewsDetailsFragmentArgs by navArgs()
        val newsModel = args.newsModel
        tvTitleNews.text = getString(newsModel.title)
        tvContentIemNews.text = getString(newsModel.content)
        imgNews.setImageDrawable(ContextCompat.getDrawable(mContext, newsModel.imgIDRes))
    }

    private fun setListeners() {
        btnArabicLang.setOnClickListener {
            changeLang(Locales.Arabic)

        }
        btnEnglishLang.setOnClickListener {
            changeLang(Locales.English)
        }
    }

    private fun changeLang(newLocale: Locale) {
        Localization.setLocaleAndRestart(
            activity,
            newLocale,
            NavigationActivity::class.java,
            bundle = getNavNewsDetailsBundle()
        )
    }
}
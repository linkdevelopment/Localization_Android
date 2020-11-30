package com.linkdev.localizatitonsample.ui.navigation.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.linkdev.localization.Localization
import com.linkdev.localization.data.models.Locales
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.ui.navigation.NavigationActivity
import com.linkdev.localizatitonsample.utils.UIUtils.setToolbar
import kotlinx.android.synthetic.main.layout_news_details.*
import kotlinx.android.synthetic.main.tool_bar_layout.*
import java.util.*

class NewsDetailsFragment : Fragment() {


    protected lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nav_news_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null)
            mContext = this.activity as Context
        setToolbar(mContext, toolBar, getString(R.string.newsDetails), false)
        loadNewsDetails()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.news_details_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return (when (item.itemId) {
            R.id.arabic -> {
                changeLang(Locales.Arabic)
                true
            }
            R.id.english -> {
                changeLang(Locales.English)
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        })
    }

    private fun loadNewsDetails() {
        val args: NewsDetailsFragmentArgs by navArgs()
        val newsModel = args.newsModel
        tvTitleNews.text = getString(newsModel.title)
        tvContentIemNews.text = getString(newsModel.content)
        imgNews.setImageDrawable(ContextCompat.getDrawable(mContext, newsModel.imgIDRes))
    }


    private fun changeLang(newLocale: Locales) {
        // TODO: Call [setLocaleAndRestart] to change app language with new locale and restart
        Localization.setLocaleAndRestart(
            activity,
            newLocale,
            NavigationActivity::class.java,

            )
    }


}
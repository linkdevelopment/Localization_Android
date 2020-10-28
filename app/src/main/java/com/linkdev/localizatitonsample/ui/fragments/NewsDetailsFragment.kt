package com.linkdev.localizatitonsample.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.linkdev.localization.Localization
import com.linkdev.localization.data.models.Locales
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.data.NewsModel
import com.linkdev.localizatitonsample.ui.fragments_sample.FragmentsSampleActivity
import kotlinx.android.synthetic.main.langs_layout.view.*
import kotlinx.android.synthetic.main.layout_news_details.*
import java.util.*


class NewsDetailsFragment : Fragment() {
    private lateinit var mContext: Context

    companion object {
        const val NEWS_MODEL_TAG = "NEWS_MODEL_TAG"
        const val TAG = "NewsDetailsFragment"
        fun newInstance(newsModel: NewsModel): NewsDetailsFragment {
            val newsFragment = NewsDetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(NEWS_MODEL_TAG, newsModel)
            newsFragment.arguments = bundle
            return newsFragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null)
            mContext = this.activity as Context
        setNewsDetails()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.layout_news_details, container, false)
        setListeners(rootView)
        return rootView
    }

    private fun setNewsDetails() {
        val newsModel: NewsModel = arguments?.getParcelable(NEWS_MODEL_TAG) ?: return
        tvTitleNews.text = getString(newsModel.title)
        tvContentIemNews.text = getString(newsModel.content)
        imgNews.setImageDrawable(ContextCompat.getDrawable(mContext, newsModel.imgIDRes))
    }

    private fun setListeners(rootView: View) {
        rootView.btnArabicLang.setOnClickListener {
            changeLang(Locales.Arabic)


        }
        rootView.btnEnglishLang.setOnClickListener {
            changeLang(Locales.English)
        }
    }

    private fun changeLang(newLocale: Locale) {
        Localization.setLocaleAndRestart(
            activity,
            newLocale,
            FragmentsSampleActivity::class.java
        )
    }


}
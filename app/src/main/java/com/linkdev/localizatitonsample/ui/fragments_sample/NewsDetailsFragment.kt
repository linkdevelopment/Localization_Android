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
package com.linkdev.localizatitonsample.ui.fragments_sample

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.linkdev.localization.Localization
import com.linkdev.localization.data.models.Locales
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.data.NewsModel
import kotlinx.android.synthetic.main.layout_news_details.*
import java.util.*


class NewsDetailsFragment : Fragment() {
    private lateinit var mContext: Context

    companion object {
        const val NEWS_MODEL_TAG = "NEWS_MODEL_TAG"
        const val TAG = "NewsDetailsFragment"
        fun newInstance(newsModel: NewsModel): NewsDetailsFragment {
            val newsFragment =
                NewsDetailsFragment()
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
        loadNewsDetails()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.layout_news_details, container, false)
        return rootView
    }

    private fun loadNewsDetails() {
        val newsModel: NewsModel = arguments?.getParcelable(NEWS_MODEL_TAG) ?: return
        tvTitleNews.text = getString(newsModel.title)
        tvContentIemNews.text = getString(newsModel.content)
        imgNews.setImageDrawable(ContextCompat.getDrawable(mContext, newsModel.imgIDRes))
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

    private fun changeLang(newLocale: Locale) {
        // TODO: Call [setLocaleAndRestart] to change app language with new locale and restart
        Localization.setLocaleAndRestart(
            activity,
            newLocale,
            FragmentsSampleActivity::class.java
        )
    }


}
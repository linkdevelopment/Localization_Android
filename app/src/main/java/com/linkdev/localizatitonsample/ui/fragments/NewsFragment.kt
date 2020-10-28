package com.linkdev.localizatitonsample.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.data.NewsModel
import com.linkdev.localizatitonsample.ui.news.NewsAdapter
import com.linkdev.localizatitonsample.utils.UIUtils
import kotlinx.android.synthetic.main.layout_news.*


class NewsFragment : Fragment(), NewsAdapter.OnAdapterNewsInteraction {
    private lateinit var mContext: Context
    private var onAdapterNewsInteraction: NewsAdapter.OnAdapterNewsInteraction? =
        null

    companion object {
        const val TAG = "NewsFragment"
        fun newInstance(): NewsFragment {
            return NewsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.layout_news, container, false)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null)
            mContext = this.activity as Context
        updateNewsList()
    }

    private fun updateNewsList() {
        val linearLayoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        rvNews.layoutManager = linearLayoutManager
        val newsAdapter = NewsAdapter(mContext, UIUtils.createNewsList(), this)
        rvNews.adapter = newsAdapter
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            onAdapterNewsInteraction =
                context as NewsAdapter.OnAdapterNewsInteraction
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onItemNewsClicked(newsModel: NewsModel) {
        onAdapterNewsInteraction?.onItemNewsClicked(newsModel)

    }
}
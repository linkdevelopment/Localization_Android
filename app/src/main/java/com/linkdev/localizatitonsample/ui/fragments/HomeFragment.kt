package com.linkdev.localizatitonsample.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.data.NewsModel
import com.linkdev.localizatitonsample.ui.news.NewsAdapter
import com.linkdev.localizatitonsample.ui.news.OnAdapterNewsInteraction
import com.linkdev.localizatitonsample.utils.UIUtils
import kotlinx.android.synthetic.main.layout_news.*
import kotlinx.android.synthetic.main.tool_bar_layout.*

class HomeFragment : Fragment(), OnAdapterNewsInteraction {

    private lateinit var mContext: Context

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
        UIUtils.setToolbar(mContext, toolBar, getString(R.string.home), false)
        loadNewsList()
    }

    private fun loadNewsList() {
        val linearLayoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        rvNews.layoutManager = linearLayoutManager
        val newsAdapter = NewsAdapter(mContext, UIUtils.createNewsList(), this)
        rvNews.adapter = newsAdapter
    }


    override fun onItemNewsClicked(newsModel: NewsModel) {
        navigateTo(HomeFragmentDirections.actionHomeToNavNewsDetailsFragment(newsModel))
    }

    private fun navigateTo(navDirections: NavDirections) {
        val navController = findNavController()
        if (findNavController().currentDestination?.getAction(navDirections.actionId) != null)
            navController.navigate(navDirections)

    }
}
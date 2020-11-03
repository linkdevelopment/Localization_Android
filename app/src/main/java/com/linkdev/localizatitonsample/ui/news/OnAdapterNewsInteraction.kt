package com.linkdev.localizatitonsample.ui.news

import com.linkdev.localizatitonsample.data.NewsModel

interface OnAdapterNewsInteraction {
    fun onItemNewsClicked(newsModel: NewsModel)
}
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
package com.linkdev.localizatitonsample.ui.common.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.linkdev.localizatitonsample.R
import com.linkdev.localizatitonsample.data.NewsModel
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(
    private val context: Context, private val mData: List<NewsModel>,
    private val onAdapterNewsInteraction: OnAdapterNewsInteraction?
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val dItem = mData[position]
        holder.onBindView(dItem)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        fun onBindView(newsModel: NewsModel) {
            itemView.tvTitleItemNews.text = context.getText(newsModel.title)
            itemView.tvContentIemNews.text = context.getText(newsModel.content)
            itemView.setOnClickListener { view: View? ->
                onItemClicked(
                    mData[adapterPosition]
                )
            }
            itemView.imgItemNews.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    newsModel.imgIDRes
                )
            )
        }

        private fun onItemClicked(newsModel: NewsModel) {
            onAdapterNewsInteraction?.onItemNewsClicked(newsModel)
        }


    }


}
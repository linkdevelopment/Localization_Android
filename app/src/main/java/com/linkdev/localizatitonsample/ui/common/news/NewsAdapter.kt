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
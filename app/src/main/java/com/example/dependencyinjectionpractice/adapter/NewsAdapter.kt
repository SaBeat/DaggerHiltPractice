package com.example.dependencyinjectionpractice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dependencyinjectionpractice.databinding.ItemRowBinding
import com.example.dependencyinjectionpractice.model.Article
import kotlinx.android.synthetic.main.item_row.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, diffUtil)

    fun submitList(list: List<Article>) = differ.submitList(list)

    inner class MyViewHolder(bind: ItemRowBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val a = differ.currentList[position]

        holder.itemView.apply {
            text_title.text = a.title
        }
        Glide.with(holder.itemView.imageView).load(a.urlToImage).into(holder.itemView.imageView)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
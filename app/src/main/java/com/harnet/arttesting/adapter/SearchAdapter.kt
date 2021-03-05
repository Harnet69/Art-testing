package com.harnet.arttesting.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.harnet.arttesting.R
import javax.inject.Inject

class SearchAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    // on clicking to a image image url is taken and sending back
    private var onItemClickListener: ((String) -> Unit) ?= null

    private val diffUtil = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerViewDiffer = AsyncListDiffer(this, diffUtil)

    var images: List<String>
        get() = recyclerViewDiffer.currentList
        set(value) = recyclerViewDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_searched_art, parent, false)
        return SearchViewHolder(view)
    }

    // apply onItemClickListener
    fun setOnItemClickListener(listener: (String) -> Unit){
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        //TODO implement ViewBinding here

        val imageView = holder.itemView.findViewById<ImageView>(R.id.artImg_ItemSearch)
        val imageUrl = images[position]
        holder.itemView.apply {
            glide.load(imageUrl).into(imageView)
            // own realisation
            setOnItemClickListener {
                onItemClickListener?.let {
                    it(imageUrl)
                }
            }
        }

    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int {
        return images.size
    }
}
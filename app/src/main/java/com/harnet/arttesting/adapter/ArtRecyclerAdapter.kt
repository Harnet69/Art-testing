package com.harnet.arttesting.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.harnet.arttesting.R
import com.harnet.arttesting.roomDb.Art
import javax.inject.Inject

class ArtRecyclerAdapter @Inject constructor(
    private val glide: RequestManager
): RecyclerView.Adapter<ArtRecyclerAdapter.ArtViewHolder>() {

    // More efficient way!!! calculates the difference between two lists and outputs a list of update operations that
    // converts the first list into the second one
    private val diffUtil = object : DiffUtil.ItemCallback<Art>(){
        override fun areItemsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerViewDiffer = AsyncListDiffer(this, diffUtil)

    var arts: List<Art>
        get() = recyclerViewDiffer.currentList
        set(value) = recyclerViewDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_art, parent, false)
        return ArtViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
        //TODO implement dataBinding
        val artImg = holder.itemView.findViewById<ImageView>(R.id.art_img)
        val artName = holder.itemView.findViewById<TextView>(R.id.art_name)
        val artAuthor = holder.itemView.findViewById<TextView>(R.id.art_author)
        val artYear = holder.itemView.findViewById<TextView>(R.id.art_year)

        val art = arts[position]
        holder.itemView.apply {
            artName.text = "Name: ${art.name}"
            artAuthor.text = "Author: ${art.author}"
            artYear.text = "Year: ${art.year.toString()}"
            glide.load(art.imageUrl).into(artImg)
        }

    }

    class ArtViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int {
        return arts.size
    }
}
package com.keepcoding.imgram.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keepcoding.imgram.R
import com.keepcoding.imgram.databinding.ItemListBinding
import com.keepcoding.imgram.model.presentation.TvShowPresentation
import com.keepcoding.imgram.visible

class TvShowAdapter(
    private val clickListener: (TvShowPresentation) -> Unit,
    private val favoriteButtonClickListener: (TvShowPresentation) -> Unit,
) : RecyclerView.Adapter<TvShowAdapter.ImageViewHolder>() {

    var data = mutableListOf<TvShowPresentation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addAll(items: List<TvShowPresentation>){
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    inner class ImageViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        private val binding = ItemListBinding.bind(itemView)
        private lateinit var item: TvShowPresentation

        init {
            binding.image.setOnClickListener {
                clickListener(item)
            }

            binding.favoriteButton.setOnClickListener {
                favoriteButtonClickListener(item)
                val modifiedTvShow = data.find { it.id == item.id }
                modifiedTvShow?.favorite = modifiedTvShow?.favorite != true
                if (item.favorite!!) {
                    binding.favoriteStar.setColorFilter(Color.argb(255, 255, 0, 0))
                    binding.favoriteText.visible(true)
                } else {
                    binding.favoriteStar.setColorFilter(Color.argb(255, 0, 0, 255))
                    binding.favoriteText.visible(false)
                }
            }
        }

        fun bind(item: TvShowPresentation) {
            this.item = item
            with(binding) {
                imageTitle.text = item.name
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/${item.posterPath}")
                    .placeholder(ContextCompat.getDrawable(itemView.context, R.mipmap.ic_launcher))
                    .into(image)
                if (item.favorite!!) {
                    favoriteStar.setColorFilter(Color.argb(255, 255, 0, 0))
                    favoriteText.visible(true)
                } else {
                    favoriteStar.setColorFilter(Color.argb(255, 0, 0, 255))
                    favoriteText.visible(false)
                }
            }
        }
    }
}
package com.umutcansahin.recyclerviewautoscroll

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umutcansahin.recyclerviewautoscroll.databinding.ImageAdapterItemBinding

class ImageAdapter(private val images: List<Image>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(
        private val binding: ImageAdapterItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Image) {
            with(binding) {
                ivImage.setImageResource(data.image)
                tvName.text = data.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImageAdapterItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageViewHolder(binding)
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val data = images[position]
        holder.onBind(data = data)
    }
}
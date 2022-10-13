package com.gsolutions.testproject.views.adapters


import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gsolutions.testproject.R
import com.gsolutions.testproject.data.Photos
import com.gsolutions.testproject.databinding.ItemEmptyPhotosAdapterBinding
import com.gsolutions.testproject.databinding.ItemPhotosAdapterBinding
import com.gsolutions.testproject.utils.Constants

class PhotosAdapter(private val photoClick: (Photos) -> Unit) :
    ListAdapter<Photos, RecyclerView.ViewHolder>(PhotosDiffCallback) {


    class PhotosViewHolder(
        val binding: ItemPhotosAdapterBinding,
        val photoClick: (Photos) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root)

    class EmptyPhotosViewHolder(
        val binding: ItemEmptyPhotosAdapterBinding,
    ) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            Constants.TYPE_PHOTO_1 -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: ItemPhotosAdapterBinding =
                    DataBindingUtil.inflate(
                        layoutInflater,
                        R.layout.item_photos_adapter,
                        parent,
                        false
                    )
                return PhotosViewHolder(binding, photoClick)
            }

            else -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: ItemEmptyPhotosAdapterBinding =
                    DataBindingUtil.inflate(
                        layoutInflater,
                        R.layout.item_empty_photos_adapter,
                        parent,
                        false
                    )
                return EmptyPhotosViewHolder(binding)
            }

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun bindPhotos(holder: PhotosViewHolder, position: Int) {
        val photo = getItem(position)

        holder.apply {
            holder.binding.item = photo
            holder.binding.executePendingBindings()
            binding.photoImg.setOnClickListener {
                photoClick(photo)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun bindEmptyPhoto(holder: EmptyPhotosViewHolder, position: Int) {
        val photo = getItem(position)
        holder.apply {
            holder.binding.item = photo
            holder.binding.executePendingBindings()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            Constants.TYPE_PHOTO_1 -> {
                bindPhotos(holder as PhotosViewHolder, position)
            }
            else -> {
                bindEmptyPhoto(holder as EmptyPhotosViewHolder, position)
            }
        }
    }
}


object PhotosDiffCallback : DiffUtil.ItemCallback<Photos>() {
    override fun areItemsTheSame(oldItem: Photos, newItem: Photos): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Photos, newItem: Photos): Boolean {
        return oldItem.id == newItem.id
    }
}
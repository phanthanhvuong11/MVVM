package com.example.androidbaseproject.ui.coin.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidbaseproject.arch.extensions.convertDpToPx
import com.example.androidbaseproject.arch.extensions.onClick
import com.example.androidbaseproject.arch.util.Constant.IMAGE_THUMBNAIL_SIZE
import com.example.androidbaseproject.data.model.Coin
import com.example.androidbaseproject.databinding.ItemCoinBinding

class CoinAdapter : RecyclerView.Adapter<CoinAdapter.ViewHolder>() {
    var coins: List<Coin> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    internal var onItemClicked: (() -> Unit) = {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCoinBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(coins[position])
    }

    override fun getItemCount(): Int = coins.size

    inner class ViewHolder(private val binding: ItemCoinBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val imageThumbnailSize = itemView.context.convertDpToPx(IMAGE_THUMBNAIL_SIZE)

        init {
            binding.root.onClick {
                onItemClicked.invoke()
            }
        }

        fun onBind(coin: Coin) {
            Glide.with(itemView.context).load(coin.item.thumb)
                .override(imageThumbnailSize).fitCenter()
                .into(binding.imgAvatar)
            binding.tvName.text = coin.item.name
        }
    }
}

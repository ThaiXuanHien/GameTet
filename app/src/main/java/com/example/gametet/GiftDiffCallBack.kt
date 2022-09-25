package com.example.gametet

import androidx.recyclerview.widget.DiffUtil

class GiftDiffCallBack : DiffUtil.ItemCallback<Gift>() {
    override fun areItemsTheSame(oldItem: Gift, newItem: Gift): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: Gift, newItem: Gift): Boolean {
        return oldItem.value == newItem.value
    }
}
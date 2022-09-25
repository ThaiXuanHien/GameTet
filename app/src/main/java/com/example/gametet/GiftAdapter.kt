package com.example.gametet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gametet.databinding.ItemGiftBinding

class GiftAdapter(val onClick : (value: Int, view: View) -> Unit) : ListAdapter<Gift, GiftAdapter.GiftVH>(GiftDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftVH {
        val binding = ItemGiftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GiftVH(binding)
    }

    override fun onBindViewHolder(holder: GiftVH, position: Int) {
        with(holder){
            with(getItem(position)){
                binding.ivValueGift.setOnClickListener {
                    onClick(value, it)
                    setClickable(it, false)
                }
            }
        }
    }

    private fun setClickable(view: View?, clickable: Boolean) {
        if (view != null) {
            if (view is ViewGroup) {
                for (i in 0 until view.childCount) {
                    setClickable(view.getChildAt(i), clickable)
                }
            }
            view.isClickable = clickable
        }
    }

    inner class GiftVH(val binding: ItemGiftBinding) : RecyclerView.ViewHolder(binding.root)
}
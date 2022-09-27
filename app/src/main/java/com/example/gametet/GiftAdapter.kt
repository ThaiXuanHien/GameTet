package com.example.gametet

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gametet.databinding.ItemGiftBinding

class GiftAdapter(var listGift: ArrayList<Gift>, val onClick : (value: Int, view: View) -> Unit) : RecyclerView.Adapter<GiftAdapter.GiftVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftVH {
        val binding = ItemGiftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GiftVH(binding)
    }

    override fun onBindViewHolder(holder: GiftVH, position: Int) {
        with(holder){
            with(listGift[position]){
                binding.ivValueGift.setImageResource(R.drawable.front_gift)
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

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: ArrayList<Gift>){
        listGift = list
        notifyDataSetChanged()
    }

    inner class GiftVH(val binding: ItemGiftBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return listGift.size
    }
}
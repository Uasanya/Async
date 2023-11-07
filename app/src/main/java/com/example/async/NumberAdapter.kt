package com.example.async

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.async.databinding.ItemNumberBinding

class NumberAdapter : ListAdapter<Int, NumberAdapter.NumberViewHolder>(NumberDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val binding = ItemNumberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NumberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NumberViewHolder(private val binding: ItemNumberBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(number: Int) {
            binding.tvNumber.text = number.toString()
        }
    }

    class NumberDiffCallback : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }
    }
}


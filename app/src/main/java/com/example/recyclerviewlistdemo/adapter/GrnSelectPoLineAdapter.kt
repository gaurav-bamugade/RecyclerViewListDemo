package com.example.recyclerviewlistdemo.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewlistdemo.R
import com.example.recyclerviewlistdemo.RecyclerAdapter
import com.example.recyclerviewlistdemo.databinding.PoLineItemLayoutBinding
import com.example.recyclerviewlistdemo.grnmodel.PoLineItem
import com.example.recyclerviewlistdemo.model.BatchInfoItem

class GrnSelectPoLineAdapter(
    private val items: MutableList<PoLineItem>,
    private val onItemCheckedChange: (PoLineItem) -> Unit
) : RecyclerView.Adapter<GrnSelectPoLineAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: PoLineItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = PoLineItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.binding.itemCode.setText(item.itemCode)
        holder.binding.ponumber.setText(item.posapLineItemNumber.toString())
        holder.binding.cbPoline.isChecked = item.isChecked
        holder.binding.cbPoline.setOnCheckedChangeListener(null) // Clear before setting to avoid recursion
        holder.binding.cbPoline.setOnCheckedChangeListener { _, isChecked ->
            item.isChecked = isChecked
            onItemCheckedChange(item)
        }
    }

    override fun getItemCount() = items.size

}
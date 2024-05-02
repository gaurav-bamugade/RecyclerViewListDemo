package com.example.recyclerviewlistdemo.poselectiondemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewlistdemo.R

class PoAdapter (private val originalList: MutableList<PoDialogRcModel>) :
    RecyclerView.Adapter<PoAdapter.ViewHolder>() {

    private var dataList: MutableList<PoDialogRcModel> = originalList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.po_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.textViewDetails.text = "${item.poNumber} - ${item.value}"
        holder.checkBoxStatus.isChecked = item.isChecked

        // Set a tag to prevent checkbox from firing off on bind
        holder.checkBoxStatus.tag = position

        holder.checkBoxStatus.setOnCheckedChangeListener { _, isChecked ->
            val pos = holder.adapterPosition
            if (pos != RecyclerView.NO_POSITION && holder.checkBoxStatus.tag == pos) {
                item.isChecked = isChecked
                if (isChecked) {
                    filterListByCode(item.code)
                } else {
                    if (dataList.none { it.isChecked }) {
                        resetFilter()
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    private fun filterListByCode(code: String) {
        dataList = originalList.filter { it.code == code }.toMutableList()
        notifyDataSetChanged()
    }
    private fun resetFilter() {
        dataList = originalList.toMutableList()
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBoxStatus: CheckBox = itemView.findViewById(R.id.checkBox)
        val textViewDetails: TextView = itemView.findViewById(R.id.tvPoNo)
    }
}
package com.example.recyclerviewlistdemo

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(
    private val itemList: MutableList<demomodel>,
    private val context: Context, private val addItem: (demomodel) -> Unit,
    private val updateItem: (Int, String, Boolean) -> Unit
) : RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.text1.text = currentItem.id
        holder.text2.setText(currentItem.name)

        if (currentItem.isUpdate) {
            holder.cl.setBackgroundColor(Color.LTGRAY)
            holder.text3.setImageResource(R.drawable.ic_black_save)
        } else {
            holder.cl.setBackgroundColor(Color.TRANSPARENT)
            holder.text3.setImageResource(R.drawable.ic_add_black)
        }

        val isAddDrawable = holder.text3.drawable.constantState == ContextCompat.getDrawable(context, R.drawable.ic_add_black)?.constantState

        holder.text3.setOnClickListener {
            if (!isAddDrawable) {
                val baseUsername = currentItem.username
                val highestNumber =
                    itemList.mapNotNull { it.id.substringAfterLast("-").toIntOrNull() }.maxOrNull()
                        ?: 0
                // Add new item with default values
                val newItem = demomodel(
                    "$baseUsername-${highestNumber + 1}",
                    holder.text2.text.toString().trim(),
                    "$baseUsername",
                    false)
                addItem(newItem)
                // Update current item's username
                updateItem(position, holder.text2.text.toString(), true)
            } else {
                updateItem(position, holder.text2.text.toString(), true)
            }
        }


        // Example color change


        holder.text4.setOnClickListener {
            if (itemList.size > 1) { // Prevent removal if only one item remains
                itemList.removeAt(position)
                notifyItemRemoved(position)
            }
        }

    }

    override fun getItemCount() = itemList.size

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text1: TextView = itemView.findViewById(R.id.text1)
        var text2: EditText = itemView.findViewById(R.id.text2)
        var text3: ImageView = itemView.findViewById(R.id.text3)
        var text4: TextView = itemView.findViewById(R.id.text4)
        var cl: ConstraintLayout = itemView.findViewById(R.id.cl)
    }
}





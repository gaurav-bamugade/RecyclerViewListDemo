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
import com.example.recyclerviewlistdemo.adapter.CustomKeyboard
import com.example.recyclerviewlistdemo.model.BatchInfoItem

class RecyclerAdapter(
    private val context: Context,
    private val  batches: MutableList<BatchInfoItem>,
    private val onSave: (Int, BatchInfoItem) -> Unit
)
: RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {
    private var customKeyboard: CustomKeyboard? = null

    fun setCustomKeyboard(customKeyboard: CustomKeyboard) {
        this.customKeyboard = customKeyboard
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.batch_item_layout, parent, false)
        return ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val batch = batches[position]
      /*  holder.text1.text = currentItem.id
        holder.text2.setText(currentItem.name)

        if (currentItem.isUpdate) {
            holder.cl.setBackgroundColor(Color.LTGRAY)
            holder.text3.setImageResource(R.drawable.ic_black_save)
        } else {
            holder.cl.setBackgroundColor(Color.TRANSPARENT)
            holder.text3.setImageResource(R.drawable.ic_add_black)
        }*/

        holder.srNo.setText("${position+1}")
        holder.tvBatchBarcode.setText("${batch.batchBarcodeNo }")
        holder.tvGeneratedBatchBarcode.setText("${batch.generatedBatchBarcodeNo }")
        updateView(holder, batch)
        holder.ivSave.setOnClickListener {
            batch.isUpdate = true
            updateView(holder, batch)
            onSave(position, batch)
        }

        holder.ivDelete.setOnClickListener {
            /*if (batches.size >= 1) {  // Check to avoid removing the last item
                batches.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, batches.size) // To update the positions
            }*/
            holder.ivDelete.setOnClickListener {
                // Remove the current item
                batches.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, batches.size) // To update the positions of the remaining items
            }
        }
        holder.tvBatchBarcode.showSoftInputOnFocus = false
        holder.tvBatchBarcode.setOnClickListener {
            customKeyboard!!.setTargetEditText(holder.tvBatchBarcode)
            customKeyboard!!.showAt(holder.itemView)
        }
        holder.tvBatchBarcode.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                customKeyboard!!.setTargetEditText(holder.tvBatchBarcode)
                customKeyboard!!.showAt(holder.itemView)
            }
        }



    }
    private fun updateView(holder: ItemViewHolder, batchInfoItem: BatchInfoItem) {
        with(holder) {
            if (batchInfoItem.isUpdate) {
                cl.setBackgroundColor(Color.LTGRAY)
            } else {
                cl.setBackgroundColor(Color.TRANSPARENT)

            }
        }
    }

    override fun getItemCount() = batches.size
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivSave: ImageView = itemView.findViewById(R.id.ivSave)
        var srNo: TextView = itemView.findViewById(R.id.srNo)
        var tvBatchBarcode: EditText = itemView.findViewById(R.id.tvBatchBarcode)
        var tvGeneratedBatchBarcode: TextView = itemView.findViewById(R.id.tvGeneratedBatchBarcode)
        var ivDelete: ImageView = itemView.findViewById(R.id.ivDelete)
        var cl:ConstraintLayout = itemView.findViewById(R.id.cl)

    }
}





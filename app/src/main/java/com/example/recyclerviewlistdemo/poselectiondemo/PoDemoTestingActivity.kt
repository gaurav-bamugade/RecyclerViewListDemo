package com.example.recyclerviewlistdemo.poselectiondemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewlistdemo.R
import com.example.recyclerviewlistdemo.databinding.ActivityPoDemoTestingBinding
import com.example.recyclerviewlistdemo.databinding.PoSelectionDialogBinding
import com.example.recyclerviewlistdemo.databinding.SelectPoLineItemDialogBinding

class PoDemoTestingActivity : AppCompatActivity() {
    lateinit var binding: ActivityPoDemoTestingBinding
    lateinit var poDialogRcList:MutableList<PoDialogRcModel>
    private lateinit var dialogBinding: PoSelectionDialogBinding
    private lateinit var dialog: AppCompatDialog
    private lateinit var adapter: PoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_po_demo_testing)

        poDialogRcList = mutableListOf(
            PoDialogRcModel("INR", "po13", "inprogress", "13",false),
            PoDialogRcModel("USD", "po15", "completed", "15",false),
            PoDialogRcModel("EUR", "po16", "pending", "16",false),
            PoDialogRcModel("INR", "po17", "inprogress", "17",false),
            PoDialogRcModel("INR", "po18", "completed", "18",false),
            PoDialogRcModel("EUR", "po19", "pending", "19",false),
            PoDialogRcModel("INR", "po20", "inprogress", "20",false),
            PoDialogRcModel("USD", "po21", "completed", "21",false),
            PoDialogRcModel("EUR", "po22", "pending", "22",false),
            PoDialogRcModel("INR", "po23", "inprogress", "23",false),
            PoDialogRcModel("USD", "po24", "completed", "24",false),
            PoDialogRcModel("EUR", "po25", "pending", "25",false),
            )
        dialogBinding = PoSelectionDialogBinding.inflate(LayoutInflater.from(this))
        // Create the dialog
        dialog = AppCompatDialog(this).apply {
            setContentView(dialogBinding.root)
            window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
        }
        dialogBinding.rcPoList .layoutManager = LinearLayoutManager(this)
        adapter = PoAdapter(poDialogRcList)
        dialogBinding.rcPoList.adapter = adapter
        dialogBinding.btnPoListSubmit.setOnClickListener {
            Log.e("oglist",poDialogRcList.toString())
        }

        binding.podialog.setOnClickListener {
            dialog.show()
        }
    }
}
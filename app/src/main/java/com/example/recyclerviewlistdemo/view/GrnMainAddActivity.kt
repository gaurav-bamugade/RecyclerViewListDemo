package com.example.recyclerviewlistdemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewlistdemo.R
import com.example.recyclerviewlistdemo.adapter.GrnSelectPoLineAdapter
import com.example.recyclerviewlistdemo.databinding.ActivityGrnMainAddBinding
import com.example.recyclerviewlistdemo.databinding.CreateBatchesDialogBinding
import com.example.recyclerviewlistdemo.databinding.SelectPoLineItemDialogBinding
import com.example.recyclerviewlistdemo.grnmodel.GrnMainPoLineItemModelItem
import com.example.recyclerviewlistdemo.grnmodel.PoLineItem
import com.example.recyclerviewlistdemo.model.PoLineItemds
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

class GrnMainAddActivity : AppCompatActivity() {
    lateinit var binding:ActivityGrnMainAddBinding

    private var grnMainPoLineItemModelItem = mutableListOf<GrnMainPoLineItemModelItem>()
    lateinit var poListModel: ArrayList<PoLineItem>
    private lateinit var dialogBinding: SelectPoLineItemDialogBinding
    private lateinit var dialog: AppCompatDialog
    private val selectedItems = mutableListOf<PoLineItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_grn_main_add)
        poListModel= ArrayList()
        dialogBinding = SelectPoLineItemDialogBinding.inflate(LayoutInflater.from(this))
        // Create the dialog
        dialog = AppCompatDialog(this).apply {
            setContentView(dialogBinding.root)
            window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
        }
        addDefaultData()
        setupRecyclerView()
        binding.selectPoLineItemDialog.setOnClickListener {
            // Show the dialog
            dialog.show()
        }
        dialogBinding.submitPolIneItem.setOnClickListener {

        }
    }
    private fun setupRecyclerView() {
        dialogBinding.rcSelectPoLineItem.adapter = GrnSelectPoLineAdapter(poListModel) { item ->
            if (item.isChecked) {
                selectedItems.add(item)
                Log.d("selectedItems",selectedItems.toString())
            } else {
                selectedItems.remove(item)
                Log.d("selectedItems",selectedItems.toString())
            }
        }
        dialogBinding.rcSelectPoLineItem.layoutManager = LinearLayoutManager(this)
        dialogBinding.rcSelectPoLineItem!!.setHasFixedSize(true)
    }
    private fun addDefaultData() {
        val jsonString = """
        [
            {
                "poId": 1,
                "poNumber": "PO001",
                "posapNumber": "POSAP01",
                "poDate": "2024-03-09T00:00:00",
                "bpCode": "KMR12",
                "bpName": "Kemar Automation 10",
                "poValidity": "2024-03-15T00:00:00",
                "poCurrency": null,
                "poStatus": "Open",
                "poLineItems": [
                    {
                        "poLineItemId": 1,
                        "poId": 1,
                        "posapLineItemNumber": "KSAP01",
                        "itemCode": "KMR10",
                        "itemName": "Fastener -3",
                        "itemDescription": "Fastener of size 3",
                        "poLineNo": 0,
                        "pouom": "QTY",
                        "mhType": "Batch",
                        "poqty": 0,
                        "balQTY": 0,
                        "unitPrice": null,
                        "isActive": true,
                        "createdBy": "",
                        "createdDate": "0001-01-01T00:00:00",
                        "modifiedBy": "",
                        "modifiedDate": null
                    },
                    {
                        "poLineItemId": 2,
                        "poId": 1,
                        "posapLineItemNumber": "KSAP01",
                        "itemCode": "KMR10",
                        "itemName": "Fastener -3",
                        "itemDescription": "Fastener of size 3",
                        "poLineNo": 0,
                        "pouom": "QTY",
                        "mhType": "Batch",
                        "poqty": 0,
                        "balQTY": 0,
                        "unitPrice": null,
                        "isActive": true,
                        "createdBy": "",
                        "createdDate": "0001-01-01T00:00:00",
                        "modifiedBy": "",
                        "modifiedDate": null
                    }
                ],
                "isActive": true,
                "createdBy": "Super Admin",
                "createdDate": "2024-04-15T18:30:28.623902",
                "modifiedBy": "",
                "modifiedDate": null
            },
            {
                "poId": 2,
                "poNumber": "PO002",
                "posapNumber": "POSAP02",
                "poDate": "2024-03-09T00:00:00",
                "bpCode": "KMR12",
                "bpName": "Kemar Automation 10",
                "poValidity": "2024-03-15T00:00:00",
                "poCurrency": null,
                "poStatus": "Open",
                "poLineItems": [
                    {
                        "poLineItemId": 3,
                        "poId": 2,
                        "posapLineItemNumber": "KSAP01",
                        "itemCode": "KMR17",
                        "itemName": "KMR -12",
                        "itemDescription": "Fastener of size 1",
                        "poLineNo": 0,
                        "pouom": "QTY",
                        "mhType": "Batch",
                        "poqty": 0,
                        "balQTY": 0,
                        "unitPrice": null,
                        "isActive": true,
                        "createdBy": "",
                        "createdDate": "0001-01-01T00:00:00",
                        "modifiedBy": "",
                        "modifiedDate": null
                    },
                    {
                        "poLineItemId": 4,
                        "poId": 2,
                        "posapLineItemNumber": "KSAP01",
                        "itemCode": "KMR18",
                        "itemName": "KMR -12",
                        "itemDescription": "Fastener of size 1",
                        "poLineNo": 0,
                        "pouom": "QTY",
                        "mhType": "Batch",
                        "poqty": 0,
                        "balQTY": 0,
                        "unitPrice": null,
                        "isActive": true,
                        "createdBy": "",
                        "createdDate": "0001-01-01T00:00:00",
                        "modifiedBy": "",
                        "modifiedDate": null
                    }
                ],
                "isActive": true,
                "createdBy": "Super Admin",
                "createdDate": "2024-04-15T18:32:42.7209888",
                "modifiedBy": "",
                "modifiedDate": null
            }
        ]
    """

        val listType = object : TypeToken<ArrayList<GrnMainPoLineItemModelItem>>() {}.type
        val poDataList: ArrayList<GrnMainPoLineItemModelItem> = Gson().fromJson(jsonString, listType)

        for(po in poDataList)
        {
            for(p in po.poLineItems)
            {
                poListModel.add(p)
            }
        }
    }
}
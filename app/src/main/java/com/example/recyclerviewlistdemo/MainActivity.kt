package com.example.recyclerviewlistdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewlistdemo.databinding.ActivityMainBinding
import com.example.recyclerviewlistdemo.databinding.CreateBatchesDialogBinding
import com.example.recyclerviewlistdemo.model.BatchInfoItem
import com.example.recyclerviewlistdemo.model.PoLineItemds
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var listmodel: ArrayList<demomodel>
    lateinit var adapter: RecyclerAdapter
    lateinit var batchInfoItem: BatchInfoItem
    lateinit var poLineItem: PoLineItemds
    private val batches = mutableListOf<BatchInfoItem>()



    private lateinit var dialogBinding: CreateBatchesDialogBinding
    private lateinit var dialog: AppCompatDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        addDefaultData()
        dialogBinding = CreateBatchesDialogBinding.inflate(LayoutInflater.from(this))
        // Create the dialog
        dialog = AppCompatDialog(this).apply {
            setContentView(dialogBinding.root)
            window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
        }
        // Set up click listener for the button in the dialog
        dialogBinding.btnCreate.setOnClickListener {
            val batchBarcode = dialogBinding.edBatchBarcode.text.toString()
            val generatedBarcode = getLatestGeneratedBarcode(batchBarcode,batches)
            val newItem = BatchInfoItem(
                bpCode = "Code", bpName = "Name", balQTY = 0, itemCode = "IC",
                itemDescription = "Desc", itemName = "Item", mhType = "Type", poId = 1,
                poLineItemId = 1, poLineNo = 1, poqty = 0, posapLineItemNumber = "SAP",
                pouom = "QTY", unitPrice = 0, receiveQuantity = "0", batchBarcodeNo = batchBarcode,
                generatedBatchBarcodeNo = generatedBarcode, isUpdate = false
            )
            batches.add(newItem)
            adapter.notifyItemInserted(batches.size - 1)
        }

        adapter = RecyclerAdapter(batches) { position, updatedItem ->
            batches[position] = updatedItem.copy()
            adapter.notifyItemChanged(position)
        }
        dialogBinding.rcBatches.adapter=adapter
        dialogBinding.rcBatches.layoutManager=LinearLayoutManager(this)


        dialogBinding.rcBatches!!.setHasFixedSize(true)
        binding.clBtn.setOnClickListener {
            // Show the dialog
            dialog.show()
        }

    }

    private fun getLatestGeneratedBarcode(batchBarcode: String, batches: List<BatchInfoItem>): String {
        var latestGeneratedBarcode = 0
        for (batch in batches) {
            if (batch.batchBarcodeNo.startsWith(batchBarcode)) {
                val generatedBarcodeNumber = batch.generatedBatchBarcodeNo.substringAfterLast('-').toIntOrNull()
                if (generatedBarcodeNumber != null && generatedBarcodeNumber > latestGeneratedBarcode) {
                    latestGeneratedBarcode = generatedBarcodeNumber
                }
            }
        }
        return "$batchBarcode-${latestGeneratedBarcode + 1}"
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

        val listType = object : TypeToken<PoLineItemds>() {}.type
        val poDataList: PoLineItemds = Gson().fromJson(jsonString, listType)

        // Step 3: Add instances to an ArrayList
        poLineItem = poDataList
    }
}


/*
class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var listmodel:ArrayList<demomodel>
    lateinit var adapter :RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        listmodel = ArrayList()
        adapter = RecyclerAdapter(listmodel, this, addItem = { newItem ->
            listmodel.add(newItem)
            Log.d("listadd(newItem)", listmodel.toString())
            binding.recyclerView.adapter?.notifyItemInserted(listmodel.size - 1)
        }, updateItem = { position, newUsername, boolean ->
            listmodel[position].name = newUsername
            listmodel[position].isUpdate = boolean
            Log.d("listupdateItem(newItem)", listmodel.toString())
            binding.recyclerView.adapter?.notifyItemChanged(position)
        })


        val addButton: Button = findViewById(R.id.clBtn)
        addButton.setOnClickListener {
            addNewItem()
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

    }
    private fun addNewItem() {
        val baseUsername = binding.edTx.text.toString()
        val highestNumber = listmodel.filter { it.username.startsWith(baseUsername) }
            .mapNotNull { it.username.removePrefix(baseUsername).toIntOrNull() }
            .maxOrNull() ?: 0 // Find the highest existing number

        val newItem = demomodel(
            id = "$baseUsername-${highestNumber + 1}",
            name = "",
            username = "$baseUsername",
            isUpdate = false
        )

        listmodel.add(newItem)
        Log.d("listDefaultadd(newItem)", listmodel.toString())
        adapter.notifyItemInserted(listmodel.size - 1)
    }
}*/

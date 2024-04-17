package com.example.recyclerviewlistdemo.model

data class BatchInfoItem(
    val bpCode: String,
    val bpName: String,
    val balQTY: Int,
    val itemCode: String,
    val itemDescription: String,
    val itemName: String,
    val mhType: String,
    val poId: Int,
    val poLineItemId: Int,
    val poLineNo: Int,
    val poqty: Int,
    val posapLineItemNumber: String,
    val pouom: String,
    val unitPrice: Any,
    val receiveQuantity:String,
    val batchBarcodeNo:String,
    val generatedBatchBarcodeNo:String,
    var isUpdate:Boolean,


    )

package com.example.recyclerviewlistdemo.grnmodel

data class PoLineItem(
    val balQTY: Int,
    val createdBy: String,
    val createdDate: String,
    val isActive: Boolean,
    val itemCode: String,
    val itemDescription: String,
    val itemName: String,
    val mhType: String,
    val modifiedBy: String,
    val modifiedDate: Any,
    val poId: Int,
    val poLineItemId: Int,
    val poLineNo: Int,
    val poqty: Int,
    val posapLineItemNumber: String,
    val pouom: String,
    val unitPrice: Any,
    var isChecked: Boolean = false

)
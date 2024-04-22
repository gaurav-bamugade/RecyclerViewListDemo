package com.example.recyclerviewlistdemo.grnmodel

data class GrnMainPoLineItemModelItem(
    val bpCode: String,
    val bpName: String,
    val createdBy: String,
    val createdDate: String,
    val isActive: Boolean,
    val modifiedBy: String,
    val modifiedDate: Any,
    val poCurrency: Any,
    val poDate: String,
    val poId: Int,
    val poLineItems: List<PoLineItem>,
    val poNumber: String,
    val poStatus: String,
    val poValidity: String,
    val posapNumber: String
)
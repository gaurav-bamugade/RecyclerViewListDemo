package com.example.recyclerviewlistdemo.model

data class jsonResponse(
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
    val poLineItems: List<PoLineItemds>,
    val poNumber: String,
    val poStatus: String,
    val poValidity: String,
    val posapNumber: String
)
package com.example.recyclerviewlistdemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.recyclerviewlistdemo.R
import com.example.recyclerviewlistdemo.databinding.ActivityDemoCalculationBinding

class demoCalculation : AppCompatActivity() {
    lateinit var binding:ActivityDemoCalculationBinding
    var balance="100"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this@demoCalculation,R.layout.activity_demo_calculation)

        val itemList = listOf(
            Model("10.455"),
            Model("15.456"),
            Model("8.123")
        )
        binding.btnsubmit.setOnClickListener {
           /* val enteredQty = binding.edQty.text.toString().toDoubleOrNull() ?: return@setOnClickListener
           // val remainingQty = balance.toDouble() - enteredQty
            val remainingQty = balance.toBigDecimal() - enteredQty
            binding.tvReceivedQty.text = "Received Quantity: $remainingQty"*/
            val result = itemList.sumByDouble { it.qty.toDouble() }
            val receivedQty = String.format("%.2f", remainingQty)
            binding.tvReceivedQty.text = "Received Quantity: $result"

        }
    }
    data class Model(val qty: String)
}
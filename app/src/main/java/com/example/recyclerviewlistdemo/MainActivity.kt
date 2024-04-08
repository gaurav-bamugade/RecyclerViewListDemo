package com.example.recyclerviewlistdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewlistdemo.databinding.ActivityMainBinding

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
}
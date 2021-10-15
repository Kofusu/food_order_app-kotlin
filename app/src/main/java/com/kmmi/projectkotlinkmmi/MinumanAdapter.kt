package com.kmmi.projectkotlinkmmi

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kmmi.projectkotlinkmmi.databinding.ListItemsMinumanBinding

class MinumanAdapter(private val context: Context, result: ArrayList<MenuMinuman>) : RecyclerView.Adapter<MinumanAdapter.MyViewHolder>() {
    private var Items = ArrayList<MenuMinuman>()
    private lateinit var sqlite : SQLiteHelperDB

    init {
        this.Items = result
        sqlite = SQLiteHelperDB(context)
    }

    inner class MyViewHolder(val binding: ListItemsMinumanBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ListItemsMinumanBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val result = Items[position]
        with(holder){
            binding.namaMinuman.text = result.nama_minuman
            binding.hargaMinuman.text = result.harga_minuman
            binding.order.setOnClickListener{
                sqlite.addCart(result.nama_minuman, result.harga_minuman)
            }
        }
    }

    override fun getItemCount(): Int {
        return Items.size
    }
}
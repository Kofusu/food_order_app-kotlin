package com.kmmi.projectkotlinkmmi

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kmmi.projectkotlinkmmi.databinding.ListItemsMakananBinding

class MakananAdapter(private val context: Context, result: ArrayList<MenuMakanan>) : RecyclerView.Adapter<MakananAdapter.MyViewHolder>() {
    private var Items = ArrayList<MenuMakanan>()
    private lateinit var sqlite : SQLiteHelperDB

    init {
        this.Items = result
        sqlite = SQLiteHelperDB(context)
    }

    inner class MyViewHolder(val binding: ListItemsMakananBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ListItemsMakananBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val result = Items[position]
        with(holder){
            binding.namaMakanan.text = result.nama_makanan
            binding.hargaMakanan.text = result.harga_makanan
            binding.order.setOnClickListener{
                sqlite.addCart(result.nama_makanan, result.harga_makanan)
            }
        }
    }

    override fun getItemCount(): Int {
       return Items.size
    }
}
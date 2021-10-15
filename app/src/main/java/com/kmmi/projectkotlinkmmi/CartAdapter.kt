package com.kmmi.projectkotlinkmmi

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kmmi.projectkotlinkmmi.databinding.ListItemsCartBinding

class CartAdapter(private val context: Context, result: ArrayList<MenuCart>) : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {
    private var Items = ArrayList<MenuCart>()
    private lateinit var sqlite : SQLiteHelperDB

    init {
        this.Items = result
        sqlite = SQLiteHelperDB(context)
    }

    inner class MyViewHolder(val binding: ListItemsCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ListItemsCartBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val result = Items[position]
        with(holder){
            binding.namaCart.text = result.nama_pesanan
            binding.hargaCart.text = result.harga_pesanan
            binding.order.setOnClickListener{
                sqlite.removeCart(result.id)
            }
        }
    }

    override fun getItemCount(): Int {
        return Items.size
    }
}
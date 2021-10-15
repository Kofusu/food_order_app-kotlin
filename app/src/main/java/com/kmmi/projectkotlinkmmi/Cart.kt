package com.kmmi.projectkotlinkmmi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kmmi.projectkotlinkmmi.databinding.ActivityCartBinding

class Cart : AppCompatActivity() {
    private lateinit var binding : ActivityCartBinding
    private lateinit var sqlite : SQLiteHelperDB
    var cartAdapter: CartAdapter? = null
    var result = ArrayList<MenuCart>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sqlite = SQLiteHelperDB(this)
    }
    override fun onResume() {
        super.onResume()
        tampilCart()
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun tampilCart(){
        val cursor = sqlite.displayCart()
        result.clear()
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                val cart = MenuCart()
                cart.id = cursor.getInt(0)
                cart.nama_pesanan = cursor.getString(1)
                cart.harga_pesanan = cursor.getString(2)
                result.add(cart)
            }while (cursor.moveToNext())

            val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            linearLayoutManager.scrollToPositionWithOffset(0, 0)
            cartAdapter = CartAdapter(this, result)
            binding.listItem.layoutManager = linearLayoutManager
            binding.listItem.adapter = cartAdapter
        } else {
            Toast.makeText(this, "Tidak Ada List Order", Toast.LENGTH_LONG).show()
        }
        cursor.close()
    }
}
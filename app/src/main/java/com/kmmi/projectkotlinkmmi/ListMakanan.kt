package com.kmmi.projectkotlinkmmi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kmmi.projectkotlinkmmi.databinding.ActivityListMakananBinding

class ListMakanan : AppCompatActivity() {
    private lateinit var binding : ActivityListMakananBinding
    private lateinit var sqlite : SQLiteHelperDB
    var makananAdapter: MakananAdapter? = null
    var result = ArrayList<MenuMakanan>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_makanan)

        binding = ActivityListMakananBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sqlite = SQLiteHelperDB(this)

        val cart = findViewById<ImageView>(R.id.cart)
        cart.setOnClickListener {
            val intent = Intent(this, Cart::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        tampilMakanan()
    }
    private fun tampilMakanan(){
        val cursor = sqlite.displayMakanan()
        result.clear()
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                val makanan = MenuMakanan()
                makanan.id = cursor.getInt(0)
                makanan.nama_makanan = cursor.getString(1)
                makanan.harga_makanan = cursor.getString(2)
                result.add(makanan)
            }while (cursor.moveToNext())

            val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            linearLayoutManager.scrollToPositionWithOffset(0, 0)
            makananAdapter = MakananAdapter(this, result)
            binding.listItem.layoutManager = linearLayoutManager
            binding.listItem.adapter = makananAdapter
        } else {
            Toast.makeText(this, "Tidak Ada Makanan", Toast.LENGTH_LONG).show()
        }
        cursor.close()
    }
}
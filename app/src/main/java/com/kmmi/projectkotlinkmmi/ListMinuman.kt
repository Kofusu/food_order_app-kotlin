package com.kmmi.projectkotlinkmmi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kmmi.projectkotlinkmmi.databinding.ActivityListMinumanBinding

class ListMinuman : AppCompatActivity() {
    private lateinit var binding : ActivityListMinumanBinding
    private lateinit var sqlite : SQLiteHelperDB
    var minumanAdapter: MinumanAdapter? = null
    var result = ArrayList<MenuMinuman>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_minuman)

        binding = ActivityListMinumanBinding.inflate(layoutInflater)
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
        tampilMinuman()
    }
    private fun tampilMinuman(){
        val cursor = sqlite.displayMinuman()
        result.clear()
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                val minuman = MenuMinuman()
                minuman.id = cursor.getInt(0)
                minuman.nama_minuman = cursor.getString(1)
                minuman.harga_minuman = cursor.getString(2)
                result.add(minuman)
            }while (cursor.moveToNext())

            val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            linearLayoutManager.scrollToPositionWithOffset(0, 0)
            minumanAdapter = MinumanAdapter(this, result)
            binding.listItem.layoutManager = linearLayoutManager
            binding.listItem.adapter = minumanAdapter
        } else {
            Toast.makeText(this, "Tidak Ada Minuman", Toast.LENGTH_LONG).show()
        }
        cursor.close()
    }
}
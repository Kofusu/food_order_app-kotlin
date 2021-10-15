package com.kmmi.projectkotlinkmmi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.LinearLayoutCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val makanan = findViewById<LinearLayoutCompat>(R.id.makanan)
        makanan.setOnClickListener {
            val intent = Intent(this, ListMakanan::class.java)
            startActivity(intent)
        }

        val minuman = findViewById<LinearLayoutCompat>(R.id.minuman)
        minuman.setOnClickListener {
            val intent = Intent(this, ListMinuman::class.java)
            startActivity(intent)
        }

        val cart = findViewById<ImageView>(R.id.cart)
        cart.setOnClickListener {
            val intent = Intent(this, Cart::class.java)
            startActivity(intent)
        }
    }
}
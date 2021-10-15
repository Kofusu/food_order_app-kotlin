package com.kmmi.projectkotlinkmmi

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelperDB(context: Context) : SQLiteOpenHelper(context, "menu.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        //Create Table
        val menuMakanan = "CREATE TABLE makanan(id_makanan INTEGER PRIMARY KEY AUTOINCREMENT, nama_makanan TEXT, harga_makanan INTEGER)"
        val menuMinuman = "CREATE TABLE minuman(id_minuman INTEGER PRIMARY KEY AUTOINCREMENT, nama_minuman TEXT, harga_minuman INTEGER)"
        val cart = "CREATE TABLE cart(id_cart INTEGER PRIMARY KEY AUTOINCREMENT, nama_pesanan TEXT, harga_pesanan INTEGER)"
        db?.execSQL(menuMakanan)
        db?.execSQL(menuMinuman)
        db?.execSQL(cart)

        // Deafult Data makanan
        db?.execSQL("INSERT INTO makanan(nama_makanan, harga_makanan) " +
                "values" +
                "('Nasi Goreng', 13000)," +
                "('Roti Bakar', 10000)," +
                "('Mie Goreng', 5000)")

        // Deafult Data minuman
        db?.execSQL("INSERT INTO minuman(nama_minuman, harga_minuman) " +
                "values" +
                "('Teh Manis', 3000)," +
                "('Ice Tea', 20000)," +
                "('Air Mineral', 1000)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS makanan")
        db?.execSQL("DROP TABLE IF EXISTS minuman")
        db?.execSQL("DROP TABLE IF EXISTS cart")
        onCreate(db)
    }

    fun displayMakanan():Cursor{
        val db:SQLiteDatabase = this.readableDatabase
        return db.rawQuery("SELECT * FROM makanan", null)
    }

    fun displayMinuman():Cursor{
        val db:SQLiteDatabase = this.readableDatabase
        return db.rawQuery("SELECT * FROM minuman", null)
    }

    fun displayCart():Cursor{
        val db:SQLiteDatabase = this.readableDatabase
        return db.rawQuery("SELECT * FROM cart", null)
    }

    fun addCart(namaPesanan: String?, hargaPesanan: String?): Boolean {
        val db:SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put("nama_pesanan", namaPesanan)
        values.put("harga_pesanan", hargaPesanan)
        val success = db.insert("cart", null, values)
        return (("$success").toInt() != -1)
    }

    fun removeCart(id: Int?):Boolean {
        val db:SQLiteDatabase = this.writableDatabase
        val success = db.delete("cart", "id_cart = $id", null)
        return (("$success").toInt() != -1)
    }

}
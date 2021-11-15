package ac.id.ukdw.pertemuan10_71190422

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(val context: Context): SQLiteOpenHelper(context,DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{ //isinya adalah static variabel hampir sama seperti di java
        const val DATABASE_NAME = "MyDB"
        const val DATABASE_VERSION = 1

    }

    //hanya akan dijalankan satu kali ketika apk pertama kali dijalankan
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(Database.Penduduk.SQL_CREATE_TABLE)
    }

    //hanya dijalankan ketika versi database terpasang berbeda dengan database terbaru
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}
package ac.id.ukdw.pertemuan10_71190422

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var dbHpr: SQLiteOpenHelper? = null
    var db: SQLiteDatabase? = null
    var listPenduduk = ArrayList<String>()
    var adapter: PendudukAdapter? = null

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHpr = DatabaseHelper(this)
        db = dbHpr?.writableDatabase

        val edtNama: EditText = findViewById(R.id.edtNama)
        val edtUsia: EditText = findViewById(R.id.edtUsia)
        val btnSimpan: Button = findViewById(R.id.btnSimpan)
        val btnCari: Button = findViewById(R.id.btnCari)
        val txtHasil: TextView = findViewById(R.id.txtQuerySelect)
        btnSimpan.setOnClickListener {
            val values = ContentValues().apply {
                put(Database.Penduduk.COLUMN_NAME_USIA, edtUsia.text.toString())
                put(Database.Penduduk.COLUMN_NAME_NAMA, edtNama.text.toString())
            }
            db?.insert(Database.Penduduk.TABLE_NAME, null, values)
            edtNama.setText("")
            edtUsia.setText("")
            reloadData()
        }

        btnCari.setOnClickListener {

            val query = "SELECT * FROM ${Database.Penduduk.TABLE_NAME} WHERE ${Database.Penduduk.COLUMN_NAME_NAMA} LIKE ? OR ${Database.Penduduk.COLUMN_NAME_USIA} LIKE ?"
            val select = arrayOf(edtNama.text.toString(),edtUsia.text.toString())
            val cursor = db?.rawQuery(
                query,
                select
            )
            var hasil =""
            with(cursor!!){
                while (moveToNext()) {
                    val nama = getString(getColumnIndex(Database.Penduduk.COLUMN_NAME_NAMA))
                    val usia = getString(getColumnIndex(Database.Penduduk.COLUMN_NAME_USIA))
                    hasil += "${nama} dengan usia ${usia}\n"
                }
            }
            txtHasil.text = hasil
        }

        val rcyPenduduk = findViewById<RecyclerView>(R.id.rcyPenduduk)
        rcyPenduduk.layoutManager = LinearLayoutManager(this)
        adapter = PendudukAdapter(listPenduduk, db)
        rcyPenduduk.adapter = adapter
        reloadData()
        adapter?.notifyDataSetChanged()
    }

    @SuppressLint("Range", "NotifyDataSetChanged")
    fun reloadData(){
        listPenduduk.clear()
        val columns = arrayOf(
            BaseColumns._ID,
            Database.Penduduk.COLUMN_NAME_NAMA,
            Database.Penduduk.COLUMN_NAME_USIA
        )

        val cursor = db?.query(
            Database.Penduduk.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor!!){
            while (moveToNext()){
                val nama = getString(getColumnIndex(Database.Penduduk.COLUMN_NAME_NAMA))
                val usia = getString(getColumnIndex(Database.Penduduk.COLUMN_NAME_USIA))
                listPenduduk.add("${nama} (${usia})")
            }
            adapter?.notifyDataSetChanged()
        }

    }

}
package com.example.final_71190422

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore

class TambahActivity : AppCompatActivity() {
    var fireStore: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah)

        fireStore = FirebaseFirestore.getInstance()

        val edtTxtKTP: EditText = findViewById(R.id.edtTxtKTP)
        val edtTxtNama: EditText = findViewById(R.id.edtTxtNama)
        val edtTxtAgama: EditText = findViewById(R.id.edtTxtAgama)
        val btnSimpan: Button = findViewById(R.id.btnSimpan)


        val btmNavbar: BottomNavigationView = findViewById(R.id.btmNav)

        btmNavbar.setSelectedItemId(R.id.person)

        btmNavbar.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.home ->{
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }

                R.id.list ->{
                    val intent = Intent(this, ViewActivity::class.java)
                    startActivity(intent)
                }

                R.id.person ->{
                    val intent = Intent(this, TambahActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }



        btnSimpan.setOnClickListener {
            if (edtTxtKTP.text.toString().isEmpty() || edtTxtNama.text.toString().isEmpty() || edtTxtAgama.text.toString().isEmpty() ){
                Toast.makeText(this,"Nomor KTP dan Nama harus diisi!",Toast.LENGTH_SHORT).show()
            }else{
                val penduduk = Penduduk(edtTxtKTP.text.toString(), edtTxtNama.text.toString(), edtTxtAgama.text.toString())
                fireStore?.collection("penduduk")?.add(penduduk)
                Toast.makeText(this,"Nomor KTP dan Nama tersimpan!",Toast.LENGTH_SHORT).show()
                edtTxtKTP.setText("")
                edtTxtNama.setText("")
            }
        }

    }
}

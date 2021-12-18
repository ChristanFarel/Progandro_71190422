package com.example.final_71190422

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore

class CariDataActivity : AppCompatActivity() {
    var fireStore: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cari_data)

        fireStore = FirebaseFirestore.getInstance()

        val nama: EditText = findViewById(R.id.edtTxtNamaCari)
        val ktp: EditText = findViewById(R.id.edtTxtKTPCari)
        val txtHasil: TextView = findViewById(R.id.txtHasilCari)
        val btnCari: Button = findViewById(R.id.btnCari)

        btnCari.setOnClickListener {

            if(nama.text.toString().isEmpty() && ktp.text.toString().isNotEmpty()){
                fireStore?.collection("penduduk")
                    ?.whereEqualTo("noKTP",ktp.text.toString())
                    ?.get()?.addOnSuccessListener {
                        var hasil = ""
                        for (document in it) {
                            hasil += "No KTP: ${document.data["noKTP"]} \n Nama: ${document.data["nama"]}"
                        }
                        txtHasil.setText(hasil)
                    }
            } else if(nama.text.toString().isNotEmpty() && ktp.text.toString().isEmpty()){
                fireStore?.collection("penduduk")
                    ?.whereEqualTo("nama",nama.text.toString())
                    ?.get()?.addOnSuccessListener {
                        var hasil = ""
                        for (document in it) {
                            hasil += "No KTP: ${document.data["noKTP"]} \n Nama: ${document.data["nama"]}"
                        }
                        txtHasil.setText(hasil)
                    }
            } else if (nama.text.toString().isEmpty() && ktp.text.toString().isEmpty()){
                Toast.makeText(this,"Setidaknya salah satu field harus diisi!",Toast.LENGTH_SHORT).show()
            } else{
                fireStore?.collection("penduduk")
                    ?.whereEqualTo("noKTP",ktp.text.toString())
                    ?.whereEqualTo("nama",nama.text.toString())
                    ?.get()?.addOnSuccessListener {
                        var hasil = ""
                        for (document in it) {
                            hasil += "No KTP: ${document.data["noKTP"]}\nNama: ${document.data["nama"]}\nAgama: ${document.data["agama"]}\n"
                        }
                        txtHasil.setText(hasil)
                    }
            }

            nama.setText("")
            ktp.setText("")


        }

        val btmNavbar: BottomNavigationView = findViewById(R.id.btmNav)

        btmNavbar.setSelectedItemId(R.id.home)

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



    }
}
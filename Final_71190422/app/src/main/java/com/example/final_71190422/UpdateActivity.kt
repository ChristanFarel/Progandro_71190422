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

class UpdateActivity : AppCompatActivity() {
    var fireStore: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        fireStore = FirebaseFirestore.getInstance()


        val ktp = intent.getStringExtra("noktp")
        val nama = intent.getStringExtra("nama")
        val agama = intent.getStringExtra("agama")


        val txtKTPLama: TextView = findViewById(R.id.noKTPLama)
        txtKTPLama.setText(ktp)

        val edtTxtNamaBaru: EditText = findViewById(R.id.edtTxtNamaUpdate)
        val edtTxtAgamaBaru: EditText = findViewById(R.id.edtTxtAgamaUpdate)
        val btnUpdate: Button = findViewById(R.id.btnUpdate)

        btnUpdate.setOnClickListener {

            if (edtTxtAgamaBaru.text.toString().isNotEmpty() || edtTxtNamaBaru.text.toString().isNotEmpty()){
                fireStore?.collection("penduduk")
                    ?.whereEqualTo("noKTP", ktp)
                    ?.get()
                    ?.addOnSuccessListener {
                        for (document in it){
                            fireStore?.collection("penduduk")?.document(document.id)
                                ?.update("noKTP",ktp,"nama",edtTxtNamaBaru.text.toString(),"agama", edtTxtAgamaBaru.text.toString())
                        }
                    }?.addOnSuccessListener {
                        Toast.makeText(this,"Update Berhasil!",Toast.LENGTH_SHORT).show()
                        edtTxtAgamaBaru.setText("")
                        edtTxtNamaBaru.setText("")
                    }
            }else{
                Toast.makeText(this,"Semua Field harus terisi!",Toast.LENGTH_SHORT).show()
            }
        }



        val btmNavbar: BottomNavigationView = findViewById(R.id.btmNav)

        btmNavbar.setSelectedItemId(R.id.list)

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
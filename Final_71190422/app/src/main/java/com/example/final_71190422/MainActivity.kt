package com.example.final_71190422

import android.R.attr
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.widget.Toast

import com.google.firebase.auth.AuthResult

import androidx.annotation.NonNull

import com.google.android.gms.tasks.OnCompleteListener

import android.R.attr.password
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    lateinit var user: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         user = FirebaseAuth.getInstance()

        val btnTambah: Button = findViewById(R.id.btnTambah)
        val btnView: Button = findViewById(R.id.btnView)
        val btnCari: Button = findViewById(R.id.btnCari)
        val btnLogout: Button = findViewById(R.id.btnLogout)

        btnTambah.setOnClickListener {
            val intent1 = Intent(this,TambahActivity::class.java)
            startActivity(intent1)
        }

        btnView.setOnClickListener {
            val intent2 = Intent(this, ViewActivity::class.java)
            startActivity(intent2)
        }


        btnCari.setOnClickListener {
            val intent5 = Intent(this, CariDataActivity::class.java)
            startActivity(intent5)
        }

        btnLogout.setOnClickListener {
            user.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
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
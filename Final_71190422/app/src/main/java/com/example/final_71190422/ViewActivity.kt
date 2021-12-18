package com.example.final_71190422

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class ViewActivity : AppCompatActivity() {
    var fireStore: FirebaseFirestore? = null
    private lateinit var list: ArrayList<Penduduk>
    private lateinit var rcy: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

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

        fireStore = FirebaseFirestore.getInstance()
        rcy = findViewById(R.id.rcy)


        fireStore?.collection("penduduk")?.get()
            ?.addOnSuccessListener{

                list = arrayListOf()
                list.clear()

                var pendudukAdp = PendudukAdapter(list)

                for (document in it) {
                    list.add(Penduduk(document.data["noKTP"] as String,document.data["nama"] as String, document.data["agama"] as String))

                }

                rcy.apply {
                    rcy.layoutManager = LinearLayoutManager(context)
                    rcy.setHasFixedSize(true)
                    rcy.adapter = pendudukAdp
                }
            }

    }
}
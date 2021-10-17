package ac.id.ukdw.pertemuan7_71190422

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), OnClick {
    val listKontak = arrayListOf<Kontak>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val listKontak = arrayListOf<Kontak>()
        listKontak.add(Kontak(nama= "Christan Farel Pamungkas", nomer= "085701708030", R.mipmap.ic_launcher, email="farel@gmail.com"))
        listKontak.add(Kontak(nama= "Revyn Pradana Putra", nomer= "081395315404", R.mipmap.ic_launcher, email = "revyn@gmail.com"))
        listKontak.add(Kontak(nama= "Yohanes Yonathan Pratama", nomer= "085029809182", R.mipmap.ic_launcher, email = "yohanes@gmail.com"))
        listKontak.add(Kontak(nama= "Sindu Putra Sumbogo", nomer= "081098038342", R.mipmap.ic_launcher, email = "sindu@gmail.com"))
        listKontak.add(Kontak(nama= "Ginting Lukaku", nomer= "087012389032", R.mipmap.ic_launcher, email = "ginting@gail.com"))

        val rv = findViewById<RecyclerView>(R.id.rvKontak)
        rv.layoutManager = LinearLayoutManager(this)
        val adapter = KontakAdapter(listKontak,this,this)
        rv.adapter = adapter


    }

    override fun onKontakClick(position: Int) {
//        Toast.makeText(this, "Kontak "+position +" clicked", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("nama",listKontak[position].nama)
        intent.putExtra("nomer",listKontak[position].nomer)
        intent.putExtra("email",listKontak[position].email)
//        intent.putExtra("gambar",listKontak[position].gambar)
        startActivity(intent)
    }
}
package ac.id.ukdw.pertemuan7_71190422

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val nama = intent.getStringExtra("nama")
        val nomer = intent.getStringExtra("nomer")
        val email = intent.getStringExtra("email")
        val txtNama: TextView = findViewById(R.id.txtnama)
        val txtNomer: TextView = findViewById(R.id.txtnomer)
        val txtEmail: TextView = findViewById(R.id.txtEmail)
        val img: ImageView = findViewById(R.id.imgkontak)
        val b: Bundle? = getIntent().extras
        val gambar = b?.getInt("gambar")
        txtNama.text =  nama
        txtNomer.text = nomer
        txtEmail.text = email
        if (gambar != null) {
            img.setImageResource(gambar)
        }
    }

}
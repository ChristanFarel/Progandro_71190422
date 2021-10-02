package ac.id.ukdw.pertemuan5_71190422

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = findViewById<EditText>(R.id.edtTextUserName)
        val pass = findViewById<EditText>(R.id.edtTextPassword)
        val btnLog = findViewById<Button>(R.id.btnLogin)

        btnLog.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("nama",username.text.toString())
            startActivity(intent)

        }
    }
}
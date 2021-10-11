package ac.id.ukdw.pertemuan6_71190422

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.add
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<FirstFragment>(R.id.fragslot1)
            add<SecondFragment>(R.id.fragslot2)
        }


//        val btn: Button = findViewById(R.id.btnFragA)
//
//        btn.setOnClickListener {
//            val intent = Intent (this, HalDuaActivity::class.java)
//            startActivity(intent)
//        }
    }
}
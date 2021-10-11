package ac.id.ukdw.pertemuan6_71190422

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit

class HalDuaActivity: AppCompatActivity (){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_haldua)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<SecondFragment>(R.id.fragslot3)
            add<ThirdFragment>(R.id.fragslot4)
        }

    }
}
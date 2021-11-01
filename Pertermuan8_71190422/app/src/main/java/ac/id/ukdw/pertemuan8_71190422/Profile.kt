package ac.id.ukdw.pertemuan8_71190422

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit

class Profile: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<FragmentDua>(R.id.fragProfile)
        }
    }
}
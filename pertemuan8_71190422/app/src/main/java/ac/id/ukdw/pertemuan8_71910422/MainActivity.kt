package ac.id.ukdw.pertemuan8_71910422


import ac.id.ukdw.pertemuan8_71190422.FragmentDua
import ac.id.ukdw.pertemuan8_71190422.FragmentSatu
import ac.id.ukdw.pertemuan8_71190422.FragmentTiga
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.mytoolbar))
        supportActionBar?.setDisplayShowHomeEnabled(false)


        val viewPager = findViewById<ViewPager2>(R.id.mypager)
        val listFragment: ArrayList<Fragment> = arrayListOf(
            FragmentSatu(),
            FragmentDua(),
            FragmentTiga()
        )
        val adapter = PagerAdapter(this,listFragment)

        viewPager.adapter = adapter
        viewPager.setCurrentItem(0)
    }

    class PagerAdapter(val activity: AppCompatActivity, val listFragment: ArrayList<Fragment>): FragmentStateAdapter(activity) {
        override fun getItemCount(): Int {
            return listFragment.size
        }
        override fun createFragment(position: Int): Fragment {
            return listFragment.get(position)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_profile -> {
//                Toast.makeText(this,"ini menu profile", Toast.LENGTH_SHORT).show()
                val viewPager = findViewById<ViewPager2>(R.id.mypager)
                viewPager.setCurrentItem(0)
                true
            }
            R.id.menu_settings -> {
//                Toast.makeText(this,"ini menu setting", Toast.LENGTH_SHORT).show()
                val viewPager = findViewById<ViewPager2>(R.id.mypager)
                viewPager.setCurrentItem(1)
                true
            }

            R.id.menu_chat ->{
//                Toast.makeText(this,"ini menu chat", Toast.LENGTH_SHORT).show()
                val viewPager = findViewById<ViewPager2>(R.id.mypager)
                viewPager.setCurrentItem(2)
                true
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
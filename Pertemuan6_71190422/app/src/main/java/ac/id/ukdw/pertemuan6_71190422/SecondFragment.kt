package ac.id.ukdw.pertemuan6_71190422

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class SecondFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ret = inflater.inflate(R.layout.fragment_second, container, false)

        val btn: Button = ret.findViewById(R.id.btnFragB)

        btn.setOnClickListener {
            val intent = Intent (this@SecondFragment.context, HalTigaActivity::class.java)
            getActivity()?.startActivity(intent)
        }
        return ret
    }
}
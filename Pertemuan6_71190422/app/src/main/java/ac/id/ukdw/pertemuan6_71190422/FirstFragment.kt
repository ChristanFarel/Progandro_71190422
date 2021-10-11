package ac.id.ukdw.pertemuan6_71190422

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class FirstFragment: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val ret = inflater.inflate(R.layout.fragment_first, container, false)
        val btn: Button = ret.findViewById(R.id.btnFragA)

        btn.setOnClickListener {
            val intent = Intent (this@FirstFragment.context, HalDuaActivity::class.java)
            getActivity()?.startActivity(intent)
        }


        return ret
    }

}
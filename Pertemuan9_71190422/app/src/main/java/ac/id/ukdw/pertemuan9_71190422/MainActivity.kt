package ac.id.ukdw.pertemuan9_71190422

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    var shP: SharedPreferences? = null
    var shPedit: SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        shP = getSharedPreferences("log", Context.MODE_PRIVATE)
        shPedit = shP?.edit()

        if (shP?.getBoolean("Login", false) == true) {
            setContentView(R.layout.activity_akun)
            val spin: Spinner = findViewById(R.id.spnBahasa)
            val adapterSpin = ArrayAdapter.createFromResource(this, R.array.bahasa, R.layout.support_simple_spinner_dropdown_item)
            spin.adapter = adapterSpin
            spin.setSelection(shP!!.getInt("bahasa",0))

            spin.onItemSelectedListener= object:AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    shPedit?.putInt("bahasa",p2)
                    shPedit?.commit()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

            val font: EditText = findViewById(R.id.edtTextFont)
            font.setText(shP!!.getString("ukur","12"))
            font.addTextChangedListener(object:TextWatcher{
                override fun afterTextChanged(p0: Editable?) {
                    shPedit?.putString("ukur",p0.toString())
                    shPedit?.commit()
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
            })

            val btnLogout: Button = findViewById(R.id.btnLogout)
            btnLogout.setOnClickListener {
                shPedit?.putBoolean("Login",false)
                shPedit?.commit()
                var intent2 = Intent(this, MainActivity::class.java)
                startActivity(intent2)
                finish()
            }


        } else {
            setContentView(R.layout.activity_main)
            val username = findViewById<EditText>(R.id.edtTextUserName)
            val pass = findViewById<EditText>(R.id.edtTextPassword)
            val btnLog = findViewById<Button>(R.id.btnLogin)

            btnLog.setOnClickListener {
                if (pass.text.toString().equals("1234")) {
                    shPedit?.putBoolean("Login", true)
                    shPedit?.commit()

                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    pass.error = "Password anda salah"
                }

            }
        }
    }
}
package ac.id.ukdw.pertemuan11_71190422

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MainActivity : AppCompatActivity() {

    var fireStore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fireStore = FirebaseFirestore.getInstance()

        val edtNIM: EditText = findViewById(R.id.edtNIM)
        val edtNama: EditText = findViewById(R.id.edtNama)
        val edtIPK: EditText = findViewById(R.id.edtIPK)
        val btnSimpan: Button = findViewById(R.id.btnSimpan)
        val btnCariNIM: Button = findViewById(R.id.btnCariNIM)
        val btnCariNama: Button = findViewById(R.id.btnCariNama)
        val btnCariIPK: Button = findViewById(R.id.btnCariIPK)
        val txtHasil: TextView = findViewById(R.id.txtHasilCari)
        val rg1: RadioGroup = findViewById(R.id.grup1)
        val rbASC: RadioButton = findViewById(R.id.rbASC)
        val rbDSC: RadioButton = findViewById(R.id.rbDSC)

        btnSimpan.setOnClickListener {
            val mahasiswa = Mahasiswa(edtNIM.text.toString(), edtNama.text.toString(), edtIPK.text.toString().toDouble())
            edtNIM.setText("")
            edtNama.setText("")
            edtIPK.setText("")
            fireStore?.collection("mahasiswa")?.add(mahasiswa)
        }

        btnCariNIM.setOnClickListener {
            if(rbASC.isChecked){
                fireStore?.collection("mahasiswa")
                    ?.orderBy("nim",Query.Direction.ASCENDING)
                    ?.get()!!
                    .addOnSuccessListener { doc ->
                        var output = ""
                        for(d in doc){
                            output += "\n${d["nim"]} , ${d["nama"]}, ${d["ipk"]}"
                        }
                        txtHasil.setText(output)
                    }
            } else if (rbDSC.isChecked){
                fireStore?.collection("mahasiswa")
                    ?.orderBy("nim",Query.Direction.DESCENDING)
                    ?.get()!!
                    .addOnSuccessListener { doc ->
                        var output = ""
                        for(d in doc){
                            output += "\n${d["nim"]} , ${d["nama"]}, ${d["ipk"]}"
                        }
                        txtHasil.setText(output)
                    }
            }

                fireStore?.collection("mahasiswa")?.whereEqualTo("nim",edtNIM.text.toString())
                    ?.get()!!
                    .addOnSuccessListener { doc ->
                        var output = ""
                        for(d in doc){
                            output += "\n${d["nim"]} , ${d["nama"]}, ${d["ipk"]}"
                        }
                        txtHasil.setText(output)
                    }


        }

        btnCariNama.setOnClickListener {
            if(rbASC.isChecked){
                fireStore?.collection("mahasiswa")
                    ?.orderBy("nama",Query.Direction.ASCENDING)
                    ?.get()!!
                    .addOnSuccessListener { doc ->
                        var output = ""
                        for(d in doc){
                            output += "\n${d["nim"]} , ${d["nama"]}, ${d["ipk"]}"
                        }
                        txtHasil.setText(output)
                    }
            }else if (rbDSC.isChecked){
                fireStore?.collection("mahasiswa")
                    ?.orderBy("nama",Query.Direction.DESCENDING)
                    ?.get()!!
                    .addOnSuccessListener { doc ->
                        var output = ""
                        for(d in doc){
                            output += "\n${d["nim"]} , ${d["nama"]}, ${d["ipk"]}"
                        }
                        txtHasil.setText(output)
                    }
            }
                fireStore?.collection("mahasiswa")?.whereEqualTo("nama",edtNama.text.toString())
                    ?.get()!!
                    .addOnSuccessListener { doc ->
                        var output = ""
                        for(d in doc){
                            output += "\n${d["nim"]} , ${d["nama"]}, ${d["ipk"]}"
                        }
                        txtHasil.setText(output)
                    }


        }

        btnCariIPK.setOnClickListener {
            if(rbASC.isChecked){
                fireStore?.collection("mahasiswa")
                    ?.orderBy("ipk",Query.Direction.ASCENDING)
                    ?.get()!!
                    .addOnSuccessListener { doc ->
                        var output = ""
                        for(d in doc){
                            output += "\n${d["nim"]} , ${d["nama"]}, ${d["ipk"]}"
                        }
                        txtHasil.setText(output)
                    }
            }else if (rbDSC.isChecked){
                fireStore?.collection("mahasiswa")
                    ?.orderBy("ipk",Query.Direction.DESCENDING)
                    ?.get()!!
                    .addOnSuccessListener { doc ->
                        var output = ""
                        for(d in doc){
                            output += "\n${d["nim"]} , ${d["nama"]}, ${d["ipk"]}"
                        }
                        txtHasil.setText(output)
                    }
            }
            fireStore?.collection("mahasiswa")?.whereEqualTo("ipk",edtIPK.text.toString().toDouble())
                ?.get()!!
                .addOnSuccessListener { doc ->
                    var output = ""
                    for(d in doc){
                        output += "\n${d["nim"]} , ${d["nama"]}, ${d["ipk"]}"
                    }
                    txtHasil.setText(output)
                }
        }





//        btnCari.setOnClickListener {
//
//            fireStore?.collection("mahasiswa")?.whereEqualTo("nim",edtNIM.text.toString())
//                ?.get()!!
//                .addOnSuccessListener { doc ->
//                    var output = ""
//                    for(d in doc){
//                        output += "\n${d["nim"]} , ${d["nama"]}, ${d["ipk"]}"
//                    }
//                    txtHasil.setText(output)
//                }



//            if(!edtNIM.toString().equals("")){
//                fireStore?.collection("mahasiswa")?.whereEqualTo("nim",edtNIM.text.toString())?.get()!!
//                    .addOnSuccessListener { doc ->
//                        var output = ""
//                        for(d in doc){
//                            output += "\n${d["nim"]} , ${d["nama"]}, ${d["ipk"]}"
//                        }
//                        txtHasil.setText(output)
//                    }
//            }else if (!edtNama.toString().equals("")){
//                fireStore?.collection("mahasiswa")?.whereEqualTo("nama",edtNama.text.toString())?.get()!!
//                    .addOnSuccessListener { doc ->
//                        var output = ""
//                        for(d in doc){
//                            output += "\n${d["nim"]} , ${d["nama"]}, ${d["ipk"]}"
//                        }
//                        txtHasil.setText(output)
//                    }
//            } else if (!edtIPK.toString().toDouble().equals("")){
//                fireStore?.collection("mahasiswa")?.whereEqualTo("ipk",edtIPK.text.toString().toDouble())?.get()!!
//                    .addOnSuccessListener { doc ->
//                        var output = ""
//                        for(d in doc){
//                            output += "\n${d["nim"]} , ${d["nama"]}, ${d["ipk"]}"
//                        }
//                        txtHasil.setText(output)
//                    }
//            }




    }
}
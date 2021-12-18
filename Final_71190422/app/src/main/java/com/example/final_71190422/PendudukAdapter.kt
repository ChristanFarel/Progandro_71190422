package com.example.final_71190422

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text

class PendudukAdapter(private  val listPenduduk: ArrayList<Penduduk>): RecyclerView.Adapter<PendudukAdapter.PendudukViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendudukViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.penduduk_view, parent, false)
        return PendudukViewHolder(view)
    }

    inner class PendudukViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        var fireStore = FirebaseFirestore.getInstance()
        var penduduk: Penduduk? = null

        fun bindView(penduduk: Penduduk){
            this.penduduk = penduduk

            itemView.findViewById<TextView>(R.id.namaRcy).text = penduduk.nama
            itemView.findViewById<TextView>(R.id.noKtpRcy).text = penduduk.noKTP
            itemView.findViewById<TextView>(R.id.agamaRcy).text = penduduk.agama

            itemView.findViewById<Button>(R.id.btnHapusView).setOnClickListener {
                fireStore?.collection("penduduk")
                    ?.whereEqualTo("noKTP",penduduk.noKTP)
//                    ?.whereEqualTo("nama",penduduk.nama)
                    ?.get()?.addOnSuccessListener {
                        for (document in it){
                            fireStore?.collection("penduduk")?.document(document.id)?.delete()
                                ?.addOnSuccessListener {
                                    Toast.makeText(itemView.context,"Berhasil dihapus!",Toast.LENGTH_SHORT).show()
                                }
                        }
                    }?.addOnFailureListener {
                        Toast.makeText(itemView.context,"Gagal dihapus!",Toast.LENGTH_SHORT).show()
                    }
                }

            itemView.findViewById<Button>(R.id.btnUpdateView).setOnClickListener {

                val intent = Intent(itemView.context, UpdateActivity::class.java)

                intent.putExtra("noktp",penduduk.noKTP)
                intent.putExtra("nama",penduduk.nama)
                intent.putExtra("agama",penduduk.agama)

                itemView.context.startActivity(intent)
            }
        }

    }

    override fun onBindViewHolder(holder: PendudukViewHolder, position: Int) {
        holder.bindView(listPenduduk[position])
    }

    override fun getItemCount(): Int {
        return listPenduduk.size
    }
}
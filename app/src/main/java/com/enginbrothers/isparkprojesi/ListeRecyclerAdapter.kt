package com.enginbrothers.isparkprojesi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row.view.*

class ListeRecyclerAdapter(val araclistesi: ArrayList<String>,val idListesi:ArrayList<Int>):
    RecyclerView.Adapter<ListeRecyclerAdapter.AracHolder>() {
    class AracHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AracHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.recycler_row,parent,false)
        return AracHolder(view)
        //hangi satır hangi tasarımla oluşturulacak
    }
    override fun onBindViewHolder(holder: AracHolder, position: Int) {
        holder.itemView.recycler_row_text.text=araclistesi[position]
        holder.itemView.setOnClickListener{
            val action=ListeFragmentDirections.actionListeFragmentToIsparkFragment("recyclerdangeldim",idListesi[position])
            Navigation.findNavController(it).navigate(action)
            //text box daki yazacak şeyleri belirlediğimiz yer
        }
    }
    override fun getItemCount(): Int {
        return araclistesi.size
//kaç satır oluşturulacağını söylüyoruz
    }
}
package com.enginbrothers.isparkprojesi

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_liste.*

class ListeFragment : Fragment() {
    var adsoyadListesi= ArrayList<String>()
    var aracIdListesi=ArrayList<Int>()
    private lateinit var listeAdapter:ListeRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_liste, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listeAdapter= ListeRecyclerAdapter(adsoyadListesi,aracIdListesi)
        recyclerView.layoutManager= LinearLayoutManager(context)
        recyclerView.adapter=listeAdapter

        sqlverialma()
    }


    fun sqlverialma(){
        try{
            activity?.let {
                val database=it.openOrCreateDatabase("Araclar", Context.MODE_PRIVATE,null)
                val cursor=database.rawQuery("SELECT * FROM Araclar",null)
                val adsoyadIndex=cursor.getColumnIndex("adsoyad")
                val aracIdIndex=cursor.getColumnIndex("id")

                adsoyadListesi.clear()
                aracIdListesi.clear()

                while(cursor.moveToNext()){
                    adsoyadListesi.add(cursor.getString(adsoyadIndex))
                    aracIdListesi.add(cursor.getInt(aracIdIndex))
                }
                listeAdapter.notifyDataSetChanged()
                cursor.close()
            }
        }catch (e:Exception){
        }
    }
}
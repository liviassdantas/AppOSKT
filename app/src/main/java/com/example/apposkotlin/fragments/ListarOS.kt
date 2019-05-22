package com.example.apposkotlin.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import com.example.apposkotlin.OSBancoRoom
import com.example.apposkotlin.OSCardviewAdapter
import com.example.apposkotlin.OSDaoRoom
import com.example.apposkotlin.R

class ListarOS : Fragment() {
    lateinit var listaOS: OSDaoRoom

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.constraintlayout_listaros_principal, container, false)

        val exibirCardOS:RecyclerView = view.findViewById(R.id.lista_cards)
        exibirCardOS.layoutManager =
            GridLayoutManager(context, 1, GridLayoutManager.VERTICAL,false)
        val cardVadapter = OSCardviewAdapter(context!!,listaOS)
        cardVadapter.ListaOS.getAll()



        return view
    }
}

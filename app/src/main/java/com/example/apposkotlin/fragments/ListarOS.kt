package com.example.apposkotlin.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apposkotlin.*

class ListarOS : Fragment() {
    var listarOS: OS? = null
    var database: osDatabase? = null
    var lista: List<OS>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        database = osDatabase.getInstance(context!!)

        val view: View = inflater.inflate(R.layout.constraintlayout_listaros_principal, container, false)

        val exibirCardOS: RecyclerView = view.findViewById(R.id.lista_cards)
        exibirCardOS.layoutManager =
            GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)

        Thread(Runnable {
            lista = database?.oSDao()?.getAll()
            activity?.runOnUiThread {
                exibirCardOS.adapter = OSCardviewAdapter(context!!,lista!!)
            }
        }).start()

        return view
    }
}

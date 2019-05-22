package com.example.apposkotlin

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView

class OSCardviewAdapter(val context: Context, val ListaOS: MutableList<OS>) :
    RecyclerView.Adapter<OSCardviewAdapter.Holder>() {
    override fun onBindViewHolder(p0: OSCardviewAdapter.Holder, p1: Int) {
        //criando a lista de OS que irá aparecer na tela
        val itemOSLista = ListaOS[p1]
        p0.num_os.text = itemOSLista.num_os.toString()
        p0.servico.text = itemOSLista.lista_serv
        p0.cliente.text= itemOSLista.cliente



    }
    //getItemCount
    override fun getItemCount(): Int = ListaOS.size


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): OSCardviewAdapter.Holder {
        return Holder(LayoutInflater.from(context).inflate(R.layout.cardview_constraintlayout, p0, false))

    }

    //criando a classe Holder
    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        //id cardview
        val cardV: CardView = view.findViewById(R.id.cardViewGroup)
        //id dos TextViews
        val num_os: TextView = view.findViewById(R.id.adapter_cardView_txvOS)
        val servico: TextView = view.findViewById(R.id.adapter_cardView_txvServico)
        val cliente: TextView = view.findViewById(R.id.adapter_cardView_txvCliente)
        //id dos ImageButtons
        val btnCamera: ImageButton = view.findViewById(R.id.adapter_cardView_botaoCamera)
        val btnmapa: ImageButton = view.findViewById(R.id.adapter_cardView_botaoMapa)

    }


}
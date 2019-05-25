package com.example.apposkotlin.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apposkotlin.*
//Função listar OS
//Retorna os objetos cadastrados no cardview
class ListarOS : Fragment() {
    //instância do banco
    var database: osDatabase? = null
    lateinit var btnadd: FloatingActionButton
    //instância da lista
    var lista: List<OS>? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        //acesso ao banco
        database = osDatabase.getInstance(context!!)

        //inflando o menu da lista de elementos
        val view: View = inflater.inflate(
            R.layout.constraintlayout_listaros_principal, container, false)

        //FloatButton
        //botão adicionar
        btnadd = view.findViewById(R.id.btnadd)
        btnadd.setOnClickListener(criarOS())


        //adapter
        val exibirCardOS: RecyclerView = view.findViewById(R.id.lista_cards)
        exibirCardOS.layoutManager =
            GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)

        //Thread que faz o retorno do banco pra listar os
        Thread(Runnable {
            lista = database?.oSDao()?.getAll()
            activity?.runOnUiThread {
               val escolha = ConfigPrefs.getPrefCor(activity!!, "")
                val corEscolhida = when(escolha){
                    1 -> context!!.getDrawable(R.color.colorAccent)
                    2 -> context!!.getDrawable(R.color.orange_material)
                    else -> context!!.getDrawable(R.color.colorAccent)
                }
                exibirCardOS.adapter = OSCardviewAdapter(this,lista!!, corEscolhida)
            }
        }).start()

        return view
    }
    private fun criarOS() = View.OnClickListener {
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.ContainerFragment, CadastroOS(), "Cadastrar OS")
            .commit()
    }




}

package com.example.apposkotlin.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.system.Os
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.apposkotlin.OS
import com.example.apposkotlin.R

//fragmento Cadastrar OS
class CadastroOS : Fragment() {
    //variáveis do layout
    private lateinit var num_os: EditText
    private lateinit var cliente: EditText
    private lateinit var lista_servico: Spinner
    private lateinit var prod: EditText
    private lateinit var endereco: EditText
    private lateinit var numEndereco: EditText
    private lateinit var bairro: EditText
    private lateinit var cidade: EditText
    private lateinit var estado: EditText
    private lateinit var cep: EditText
    private lateinit var btnSalvar: Button
    private lateinit var btnLocalizar: Button
    //Inflar layout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.linearlayout_cadastraros_principal, container, false)
        //IDS
        //Componetes da tela
        num_os = view.findViewById<EditText>(R.id.n_os)
        cliente = view.findViewById<EditText>(R.id.cliente)
        lista_servico = view.findViewById<Spinner>(R.id.lista_servico)
        prod = view.findViewById<EditText>(R.id.produto)
        endereco = view.findViewById<EditText>(R.id.endereco)
        numEndereco = view.findViewById<EditText>(R.id.numero_endereco)
        bairro = view.findViewById<EditText>(R.id.bairro)
        cidade = view.findViewById<EditText>(R.id.cidade)
        estado = view.findViewById<EditText>(R.id.estado)
        cep = view.findViewById<EditText>(R.id.cep)
        btnSalvar = view.findViewById<Button>(R.id.btnSalvar)
        btnLocalizar = view.findViewById<Button>(R.id.btnLocalizar)

        //botão salvar
        btnSalvar.setOnClickListener(salvarDados())
        return view
    }
    //botão Salvar
    private fun salvarDados() = OnClickListener {
        var os = OS() //instância da OS
        //set error - caso a OS esteja vazia
        if (os.num_os.toString().isBlank()) {
            num_os.error=("Digite o número da OS")
        } else {
            os.num_os = num_os.text.toString().toInt()
        }
        //recebendo as informações
        os.lista_serv = lista_servico.selectedItem.toString()
        os.bairro = bairro.text.toString()
        os.cep = cep.text.toString()
        os.cliente = cliente.text.toString()
        os.estado = estado.text.toString()
        os.numEndereco = numEndereco.text.toString()
        os.prod = prod.text.toString()
        os.cidade = cidade.text.toString()
        os.endereco = endereco.text.toString()
        Toast.makeText(context, "OS salva", Toast.LENGTH_SHORT).show()

    }
}




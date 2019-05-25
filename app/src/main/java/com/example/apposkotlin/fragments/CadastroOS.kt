package com.example.apposkotlin.fragments


import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.arch.persistence.room.Room
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.system.Os
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.support.v4.content.ContextCompat.checkSelfPermission
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.apposkotlin.*
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.tablelayout_cadastraros_bottom.*
import java.io.IOException
import java.util.*

//fragmento Cadastrar OS
class CadastroOS() : Fragment() {
    //banco
    var banco: osDatabase? = null
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
    private var edita: OS? = null

    @SuppressLint("ValidFragment")
    constructor(os: OS) : this() {
        this.edita = os
    }

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
        btnLocalizar.setOnClickListener(localizarEndereco())


        banco = osDatabase.getInstance(context!!)
        if (edita != null) {
            recarregarDados()
        }
        return view

    }


    //função localizar endereço
    private fun localizarEndereco() = View.OnClickListener {
        if (checkSelfPermission(context!!, Manifest.permission.ACCESS_COARSE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1010
            )
        } else {
            getLocalizacao()
        }

    }

    //requisitando a permissão para acessar a localização do usuário
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1010) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED)
                getLocalizacao()
        }

    }

    //iniciando a georeferência
    @SuppressLint("MissingPermission")
    fun getLocalizacao() {
        val flpc = LocationServices
            .getFusedLocationProviderClient(
                this.context!!
            )
        flpc.lastLocation.addOnSuccessListener() {
            try {
                geoReferenciar(it)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    //função geoReferencia
    @Throws(IOException::class)
    fun geoReferenciar(location: Location) {
        val geo = Geocoder(context, Locale.getDefault())
        var enderecos: MutableList<Address> =
            geo.getFromLocation(location.latitude, location.longitude, 1)
        if (enderecos != null && enderecos.size > 0) {
            val address = enderecos.get(0)

            //enviando informações
            //numero
            var num_casa = address.featureName
            //formatando o número
            if (num_casa.contains("-")) {
                num_casa = num_casa.substring(0, num_casa.indexOf("-"))
            }
            //enviando para a txt view
            numEndereco.setText(num_casa)
            //endereco
            var endereco_nome_rua = address.thoroughfare
            endereco.setText(endereco_nome_rua)
            //bairro
            var bairro_endereco = address.subLocality
            bairro.setText(bairro_endereco)
            //cidade
            var cidade_endereco = address.subAdminArea
            cidade.setText(cidade_endereco)
            //estado
            var estado_endereco = address.adminArea
            estado.setText(estado_endereco)
            //cep
            var cep_endereco = address.postalCode
            cep.setText(cep_endereco)
        }
    }//fim botão lozalizar


    //botão Salvar
    private fun salvarDados() = OnClickListener {
        var os = OS() //instância da OS

        //set error - caso a OS esteja vazia
        if (num_os.text.isBlank()) {
            num_os.error = "Digite o número da OS"
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


        if (edita != null) {
            Thread(Runnable {
                this.banco?.oSDao()?.Update(os)
                activity?.runOnUiThread {
                    Toast.makeText(activity?.applicationContext, "OS salva", Toast.LENGTH_SHORT).show()
                    fragmentManager?.beginTransaction()
                        ?.replace(R.id.ContainerFragment, ListarOS(), "Listar OS")
                        ?.commit()
                }

            }).start()
        } else {
            //Thread que cria a os no banco
            Thread(Runnable {
                if (this.banco?.oSDao()?.Insert(os) != -1L) {
                    activity?.runOnUiThread {
                        Toast.makeText(activity?.applicationContext, "OS salva", Toast.LENGTH_SHORT).show()
                        fragmentManager?.beginTransaction()
                            ?.replace(R.id.ContainerFragment, ListarOS(), "Listar OS")
                            ?.commit()
                    }
                }
            }).start()
        }

    }

    private fun recarregarDados() {
        num_os.isEnabled = false
        num_os.setText(edita?.num_os.toString())
        var position = when (edita?.lista_serv) {
            "Instalação" -> 0
            "Reparo" -> 1
            "Desinstalação" -> 2
            else -> -1
        }

        lista_servico.getItemAtPosition(position)
        endereco.setText(edita?.endereco)
        cliente.setText(edita?.cliente)
        cidade.setText(edita?.cidade)
        cep.setText(edita?.cep)
        bairro.setText(edita?.bairro)
        estado.setText(edita?.estado)
        numEndereco.setText(edita?.numEndereco)
        prod.setText(edita?.prod)
    }


}




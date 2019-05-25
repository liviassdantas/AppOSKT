package com.example.apposkotlin

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.FileProvider
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.apposkotlin.fragments.CadastroOS
import java.io.File
import java.io.IOException


class OSCardviewAdapter(
    val frag: Fragment,
    val ListaOS: List<OS>,
    var corEscolhida: Drawable?

) :
    RecyclerView.Adapter<OSCardviewAdapter.Holder>() {
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onBindViewHolder(p0: OSCardviewAdapter.Holder, p1: Int) {
        //criando a lista de OS que irá aparecer na tela
        val itemOSLista = ListaOS[p1]
        p0.num_os.text = itemOSLista.num_os.toString()
        p0.servico.text = itemOSLista.lista_serv.toString()
        p0.cliente.text = itemOSLista.cliente.toString()

        p0.itemBgkground.background = corEscolhida
        p0.cardV.setOnClickListener(editarOS(itemOSLista))
        p0.cardV.setOnLongClickListener(deletarOS(itemOSLista))

        //mapa
        p0.btnmapa.setOnClickListener{
            val mapaURI = Uri.parse("geo:0,0?q=80 Rua Maria de Carvalho,Padre Miguel, Rio de Janeiro")
            val maps = Intent(Intent.ACTION_VIEW, mapaURI)
            frag.startActivity(maps)
        }

        //câmera
        p0.btnCamera.setOnClickListener {
            //pedindo permissão para a câmera
            if (ActivityCompat.checkSelfPermission(
                    frag.context!!,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(frag.context as Activity, arrayOf(Manifest.permission.CAMERA), 102)
                return@setOnClickListener
            }

            //instanciando a camera
            val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (camera.resolveActivity(frag.context!!.packageManager) != null) {
                //caminho da foto
                var arvfoto: File? = null
                fun ArquivoFoto() : File{
                    val nomeFoto : String = "JPEG" + p0.num_os.toString() +"_"
                    val armz = frag.context!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                    return File.createTempFile(nomeFoto, ".jpg", armz)
                }
                 fun ExibirImagem() {
                    val btmop = BitmapFactory.Options()
                    btmop.inJustDecodeBounds = true
                    BitmapFactory.decodeFile(ArquivoFoto().absolutePath, btmop)
                    val bitmap = BitmapFactory.decodeFile(ArquivoFoto().absolutePath, btmop)
                }
                /*arvfoto = ArquivoFoto()
                val uri = FileProvider.getUriForFile(
                    frag.context!!,
                    "com.example.apposkotlin.fileprovider", arvfoto
                )
                camera.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                camera.putExtra("caminhoFoto", arvfoto.absolutePath)*/
                frag.startActivityForResult(camera, 102)
            }
        }
    }


    private fun editarOS(os:OS) = View.OnClickListener {
        frag.fragmentManager?.beginTransaction()
            ?.replace(R.id.ContainerFragment, CadastroOS(os), "Listar OS")
            ?.commit()
    }

    private fun deletarOS(os: OS) = View.OnLongClickListener {
        val banco = osDatabase.getInstance(frag.activity!!.applicationContext)
        Thread(Runnable {
            banco?.oSDao()?.Delete(os)
            frag.activity!!.runOnUiThread {
                Toast.makeText(frag.activity!!.applicationContext, "Os Deletada com sucesso", Toast.LENGTH_SHORT).show()
                frag.activity!!.recreate()
            }
        }).start()
        true
    }

    //getItemCount
    override fun getItemCount() = ListaOS.size


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): OSCardviewAdapter.Holder {
        return Holder(
            LayoutInflater.from(frag.activity!!.applicationContext)
                .inflate(R.layout.cardview_constraintlayout, p0, false)
        )

    }

    //criando a classe Holder
    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        //id cardview
        val cardV: CardView = view.findViewById(R.id.cardViewGroup)
        //id dos TextViews
        val num_os: TextView = view.findViewById(R.id.adapter_cardView_txvOS)
        val servico: TextView = view.findViewById(R.id.adapter_cardView_txvServico)
        val cliente: TextView = view.findViewById(R.id.adapter_cardView_txvCliente)
        val itemBgkground : LinearLayout = view.findViewById(R.id.linearCardView)
        //id dos ImageButtons
        val btnCamera: ImageButton = view.findViewById(R.id.adapter_cardView_botaoCamera)
        val btnmapa: ImageButton = view.findViewById(R.id.adapter_cardView_botaoMapa)


    }


}
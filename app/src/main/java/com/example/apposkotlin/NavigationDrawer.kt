package com.example.apposkotlin

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.Layout
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.apposkotlin.fragments.CadastroOS
import com.example.apposkotlin.fragments.ListarOS
import kotlinx.android.synthetic.main.linearlayout_alertdialog_colors.view.*
import kotlinx.android.synthetic.main.navdrawer_activity_main.*
import java.util.zip.Inflater

//Navigation Drawer Layout
class NavigationDrawer : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    //iniciando as variáveis
    var toolbar: Toolbar? = null
    lateinit var drwerlayout: DrawerLayout
    lateinit var nvgview: NavigationView
    lateinit var builder: AlertDialog.Builder
    lateinit var builderView: View
    var rosa: Switch? = null
    var laranja: Switch? = null


    //OnCreate method
    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navdrawer_activity_main)



       /* //alert dialog do trocar cor
        builder = AlertDialog.Builder(this)
        builderView = LayoutInflater.from(this@NavigationDrawer).inflate(R.layout.linearlayout_alertdialog_colors,null)
        builder.setView(builderView)
        //switches
        rosa = builderView.findViewById(R.id.switchRosa)
        laranja = builderView.findViewById(R.id.switchOrange)*/
        //navigation view - variaveis e functions
        toolbar = findViewById(R.id.nvtoolbar)
        setSupportActionBar(toolbar)
        drwerlayout = findViewById(R.id.nvdrawerLayout)
        nvgview = findViewById(R.id.navigationView)

        //toogle
        val toogle = ActionBarDrawerToggle(
            this, drwerlayout, toolbar, R.string.abrir_drawer, R.string.fechar_drawer
        )
        drwerlayout.addDrawerListener(toogle)
        toogle.syncState()

        //clique do navigation
        nvgview.setNavigationItemSelectedListener(this)

        supportFragmentManager.beginTransaction()
            .replace(R.id.ContainerFragment, ListarOS(), "Listar OS")
            .commit()

    }//fim onCreate



    //botão voltar
    override fun onBackPressed() {
        if (drwerlayout.isDrawerOpen(GravityCompat.START)) {
            drwerlayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }

    }//fim navigation drawer

    //função para trocar de cor
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun trocaCor(): Int {

        //se for utilizado o switch rosa
        if (rosa!!.isChecked) {
            ConfigPrefs.setColor(this, "", 1)
            Toast.makeText(this, "CardRosa", Toast.LENGTH_SHORT).show()
        } //se for utilizado o switch laranja
        else if (laranja!!.isChecked) {
            ConfigPrefs.setColor(this, "", 2)
            Toast.makeText(this, "CardLaranja", Toast.LENGTH_SHORT).show()
        }
        return ConfigPrefs.getPrefCor(this,"key")

    }

    //itens da navigation bar
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            //caso item 1
            R.id.item1 -> {
                //alert dialog do trocar cor
               val builder2 = AlertDialog.Builder(this)
                val builderView2 = LayoutInflater.from(this@NavigationDrawer).inflate(R.layout.linearlayout_alertdialog_colors,null)
                builder2.setView(builderView2)
                //switches
                rosa = builderView2.findViewById(R.id.switchRosa)
                laranja = builderView2.findViewById(R.id.switchOrange)
                builder2.setPositiveButton("Cor selecionada") { _: DialogInterface, _: Int ->
                    ConfigPrefs.setColor(this, "key", trocaCor())
                    supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.ContainerFragment, ListarOS(), "Listar OS")
                        ?.commit()

                }
                builder2.setNegativeButton("Nenhuma cor acionada", null)
                builder2.setTitle("Troca de cor do Card")
                builder2.setMessage("Deslize o switch para trocar de cor")
                builder2.setCancelable(false)
                builder2.create().show()


            }
            //caso item 2
            R.id.item2 -> {
                Toast.makeText(this, "Item 2", Toast.LENGTH_SHORT).show()
            }

            else -> Toast.makeText(this, "Escolha algum item", Toast.LENGTH_SHORT).show()
        }
        drwerlayout.closeDrawer(GravityCompat.START)
        return true

    }
}//fim navbar







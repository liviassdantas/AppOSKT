package com.example.apposkotlin

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Toast

//Navigation Drawer Layout
class NavigationDrawer : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    //iniciando as variáveis
     var toolbar : Toolbar ?= null
     lateinit var drwerlayout : DrawerLayout
     lateinit var nvgview : NavigationView

    //OnCreate method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navdrawer_activity_main)
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
    }//fim onCreate
    //botão voltar
    override fun onBackPressed() {
        if (drwerlayout.isDrawerOpen(GravityCompat.START)) {
            drwerlayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }

    }//fim navigation drawer

    //itens da navigation bar
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
         when (menuItem.itemId) {
            //caso item 1
            R.id.item1-> {
                Toast.makeText(this, "Item 1", Toast.LENGTH_SHORT).show()
            }
            //caso item 2
            R.id.item2 -> {
                Toast.makeText(this, "Item 2", Toast.LENGTH_SHORT).show()
            }

            else -> Toast.makeText(this,"Escolha algum item", Toast.LENGTH_SHORT).show()
        }
        drwerlayout.closeDrawer(GravityCompat.START)
        return true
    }
}//fim navbar

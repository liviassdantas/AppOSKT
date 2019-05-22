package com.example.apposkotlin

import android.arch.persistence.room.Room
import android.content.Context

class OSBancoRoom {
    lateinit var context: Context
    val db = Room.databaseBuilder(
        context, osDatabase::class.java, "os_bd"
    ).build()
}
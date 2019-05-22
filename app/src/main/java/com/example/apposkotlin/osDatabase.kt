package com.example.apposkotlin

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
//criando o banco de dados
@Database(entities = arrayOf(OS::class), version = 1)
abstract class osDatabase : RoomDatabase() {
    abstract fun OSDao():OSDaoRoom
}
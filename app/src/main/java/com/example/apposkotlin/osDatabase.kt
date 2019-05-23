package com.example.apposkotlin

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.apposkotlin.fragments.ListarOS

//criando o banco de dados
@Database(entities = arrayOf(OS::class), version = 1)
abstract class osDatabase : RoomDatabase() {

    abstract fun oSDao():OSDaoRoom

    companion object{
        /*
        private var INSTANCE: CarDatabase? = null

        fun getInstance(context: Context): CarDatabase? {
            if (INSTANCE == null) {
                synchronized(CarDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            CarDatabase::class.java, "car.db")
                            .build()
                }
            }
            return INSTANCE
         */

        private var INSTANCE :osDatabase? = null

        fun getInstance(context:Context): osDatabase? {
            if (INSTANCE == null) {
                synchronized(osDatabase::class){
                    //INSTANCE = OSBancoRoom().db
                    INSTANCE =Room.databaseBuilder(context,
                        osDatabase::class.java, "os_bd")
                        .build()
                }
            }
            return INSTANCE
        }
    }
}
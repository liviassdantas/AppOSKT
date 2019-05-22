package com.example.apposkotlin

import android.arch.persistence.room.*
import android.system.Os

@Dao
class OSDaoRoom {
    //CRUD
    //INSERT
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun Insert(vararg os: OS): Long {return Insert()}
    //UPDATE
    @Update
    fun Update(vararg os: OS){}
    //DELETE
    @Delete
    fun Delete(os: OS){}
    //SELECIONANDO AS OS PELO ID
    @Query("SELECT * FROM OS WHERE num_os = :id")
    fun getOSbyID(id:Long) {}
    //SELECIONANDO TUDO
    @Query("SELECT * FROM OS")
    fun getAll(): List<OS>{return getAll()}
    //DELETANDO UMA OS
    @Query("DELETE FROM OS WHERE num_os = :id")
    fun deleteOSByID(id:Long){}

}
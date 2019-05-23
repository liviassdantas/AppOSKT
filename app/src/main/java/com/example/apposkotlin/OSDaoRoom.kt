package com.example.apposkotlin

import android.arch.persistence.room.*
import android.system.Os

@Dao
interface OSDaoRoom {
    //CRUD
    //INSERT
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun Insert(os: OS): Long

    //UPDATE
    @Update
    fun Update(os: OS)

    //DELETE
    @Delete
    fun Delete(os: OS)

    //SELECIONANDO AS OS PELO ID
    @Query("SELECT * FROM OS WHERE num_os = :id")
    fun getOSbyID(id:Long):OS

    //SELECIONANDO TUDO
    @Query("SELECT * FROM OS")
    fun getAll(): List<OS>

    //DELETANDO UMA OS
    @Query("DELETE FROM OS WHERE num_os = :id")
    fun deleteOSByID(id:Long)

}
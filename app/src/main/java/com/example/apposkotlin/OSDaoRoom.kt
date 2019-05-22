package com.example.apposkotlin

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface OSDaoRoom {
    @Query("SELECT * FROM OS")
    fun all(): List<OS>

    @Insert
    fun add(vararg os: OS)

    @Delete
    fun delete(os: OS)
    
}
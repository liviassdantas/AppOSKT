package com.example.apposkotlin

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.RequiresApi

class ConfigPrefs {
    companion object {
        private val const: String = "Myprefs"
        fun setColor(activity: Activity, key: String, colorIni: Int): Boolean {
            val sharedPreferences = activity.getSharedPreferences(const, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putInt("key", colorIni)
            editor.apply()
            return true
        }


        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun getPrefCor(activity: Activity, key: String): Int{
            val sharedPreferences = activity.getSharedPreferences(const, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("key", 1)
        }
    }
}
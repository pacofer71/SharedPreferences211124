package com.example.sharedpreferences211124

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Preferences(c: Context) {
    val storage=c.getSharedPreferences("USER_DATOS", MODE_PRIVATE)
    //val storage=c.getSharedPreferences("USER_DATOS", 0)
    fun setEmail(email: String){
        storage.edit().putString("EMAIL", email).apply()
    }
    fun setVip(b: Boolean){
        storage.edit().putBoolean("VIP", b).apply()
    }
    fun getEmail(): String{
        return storage.getString("EMAIL", "").toString()
    }
    fun isVip(): Boolean{
        return  storage.getBoolean("VIP", false)
    }
    fun borrarTodo(){
        storage.edit().clear().apply()
    }
}
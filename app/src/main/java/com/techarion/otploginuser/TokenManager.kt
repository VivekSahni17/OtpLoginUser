package com.techarion.otploginuser

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext context:Context) {
     val pref:SharedPreferences = context.getSharedPreferences(Cons.PREFS_TOKEN_FILES,Context.MODE_PRIVATE)

    @SuppressLint("CommitPrefEdits")
    fun saveToken(token:String){
    val editor = pref.edit()
        editor.putString(Cons.USER_TOKEN,token)
        editor.apply()
    }

    fun getToken():String?{
        return pref.getString(Cons.USER_TOKEN,null)
    }
}
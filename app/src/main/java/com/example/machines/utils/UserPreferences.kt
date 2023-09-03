package com.example.machines.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity


class UserPreferences(
    context: Context
) {

    private var pref: SharedPreferences
    private var editor: SharedPreferences.Editor

    companion object {
        const val myPREFERENCES = "MyPrefs"
    }


    init {
        pref = context.getSharedPreferences(myPREFERENCES, AppCompatActivity.MODE_PRIVATE)
        editor = pref.edit()
    }


    fun saveData(key: String?, value: String?) {
        editor.putString(key, value)
        editor.apply()
    }

    fun retrieveData(key: String?): String? {
        return pref.getString(key, "")
    }
}
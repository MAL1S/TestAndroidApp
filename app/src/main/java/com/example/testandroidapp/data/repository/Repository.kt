package com.example.testandroidapp.data.repository

import android.content.Context
import com.example.testandroidapp.data.models.Valute
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private const val SHARED_PREFS_NAME = "shared_prefs_name"
private const val KEY_VALUTE_LIST = "valute"

class Repository(
    context: Context
) {
    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    fun saveValuteList(valuteList: List<Valute>) {
        sharedPreferences.edit().putString(KEY_VALUTE_LIST, Json.encodeToString(valuteList)).apply()
    }

    fun getValuteList(): List<Valute>? {
        val valuteList = sharedPreferences.getString(KEY_VALUTE_LIST, null)

        return if (valuteList == null) null
        else Json.decodeFromString(valuteList)
    }
}
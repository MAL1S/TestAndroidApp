package com.example.testandroidapp.data.models


import com.google.gson.annotations.SerializedName

data class Valute(
    @SerializedName("CharCode")
    val charCode: String,
    @SerializedName("ID")
    val iD: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Nominal")
    val nominal: Int,
    @SerializedName("NumCode")
    val numCode: String,
    @SerializedName("Previous")
    val previous: Double,
    @SerializedName("Value")
    val value: Double
)
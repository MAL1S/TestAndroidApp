package com.example.testandroidapp.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Valute(
    @SerializedName("ID")
    val ID: String,
    @SerializedName("NumCode")
    val NumCode: String,
    @SerializedName("CharCode")
    val CharCode: String,
    @SerializedName("Nominal")
    val Nominal: String,
    @SerializedName("Name")
    val Name: String,
    @SerializedName("Value")
    val Value: String,
    @SerializedName("Previous")
    val Previous: String
)
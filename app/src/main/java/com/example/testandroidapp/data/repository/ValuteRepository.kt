package com.example.testandroidapp.data.repository

import com.example.testandroidapp.data.models.ValuteList

interface ValuteRepository {

    fun getValutes(): ValuteList
}
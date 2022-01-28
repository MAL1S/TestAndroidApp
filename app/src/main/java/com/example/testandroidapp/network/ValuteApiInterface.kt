package com.example.testandroidapp.network

import com.example.testandroidapp.data.models.ValuteListResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ValuteApiInterface {

    @GET("daily_json.js")
    fun getValutes(): Single<ValuteListResponse>
}
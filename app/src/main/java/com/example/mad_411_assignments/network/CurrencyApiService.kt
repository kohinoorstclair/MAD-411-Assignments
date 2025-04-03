package com.example.mad_411_assignments.network

import com.example.mad_411_assignments.model.CurrencyConversion
import retrofit2.http.GET


interface CurrencyApiService {


    @GET("v1/currencies/cad.json")
    suspend fun getExchangeRates(): CurrencyConversion
}

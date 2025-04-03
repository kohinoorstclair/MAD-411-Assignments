package com.example.mad_411_assignments.network

import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApiService {


    @GET("latest/v1/currencies.json")
    suspend fun getCurrencies(): Map<String, String>


    // to get thr code
    @GET("latest/v1/currencies/{baseCurrency}.json")
    suspend fun getExchangeRates(@Path("baseCurrency") baseCurrency: String): Map<String, Map<String, Double>>
}

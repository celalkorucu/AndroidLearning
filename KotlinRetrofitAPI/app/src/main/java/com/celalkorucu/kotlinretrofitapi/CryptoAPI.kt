package com.celalkorucu.kotlinretrofitapi

import io.reactivex.Observable
import retrofit2.Callback
import retrofit2.http.GET

interface CryptoAPI {



    @GET("K21-JSONDataSet/master/crypto.json")
    fun getData(): Observable<List<CryptoModel>>
}
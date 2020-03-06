package com.example.oghatmvvm

import com.example.oghatmvvm.PojoModel.AladhanResponseModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface RetrofitInterface {
    @GET("timingsByCity")
    fun GetSharei(
        @Query("city") city: String,
        @Query("country") country: String,
        @Query("method") method: String
    ): Observable<AladhanResponseModel>
}
package com.mobarok.rokomarytest.model

import dagger.Provides
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header

public interface BookApi {
   @GET("books?page=1&limit=30&new_arrival=True")
   fun getNewArrival(@Header("Authorization") token :String?): Single<ResponseBook>

    @GET("books?page=1&limit=30")
    fun getExploreBooks(@Header("Authorization") token :String?): Single<ResponseBook>
}
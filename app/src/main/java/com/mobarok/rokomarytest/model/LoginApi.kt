package com.mobarok.rokomarytest.model

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

import retrofit2.http.FormUrlEncoded




interface LoginApi {
    @FormUrlEncoded
    @POST("auth/token/")
    fun loginNow(
        @Field("username") userName: String?,
        @Field("password") password: String?
    ): Single<ResponseLogin>

    @FormUrlEncoded
    @POST("auth/register/")
    fun registerNow(
        @Field("username") userName: String?,
        @Field("password") password: String?,
        @Field("email") email: String?
    ): Single<ResponseRegister>
}
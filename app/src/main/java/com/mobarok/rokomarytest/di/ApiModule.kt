package com.mobarok.rokomarytest.di

import com.mobarok.rokomarytest.model.LoginApi
import com.mobarok.rokomarytest.model.LoginService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private  val baseUrl : String = "http://202.84.44.253:5005/api/v1/"
    var count = 0 ;

    @Provides
    fun provideLoginApi():LoginApi{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(LoginApi::class.java)
    }
    @Provides
    fun provideLoginService(): LoginService{
        return LoginService()
    }

    @Provides
    fun getCountVal(): Int{
        return  count
    }
}
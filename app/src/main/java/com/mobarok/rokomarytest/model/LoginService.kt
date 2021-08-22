package com.mobarok.rokomarytest.model

import com.mobarok.rokomarytest.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class LoginService {
    @Inject
    lateinit var api : LoginApi;

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun loginNow(userName:String?,password:String?): Single<ResponseLogin> {
        return api.loginNow(userName,password)
    }

    fun registerNow(userName:String?,email : String?,password:String?): Single<ResponseRegister> {
        return api.registerNow(userName,email,password)
    }
}
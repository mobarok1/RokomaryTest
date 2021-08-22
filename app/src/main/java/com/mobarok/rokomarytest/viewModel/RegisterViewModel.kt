package com.mobarok.rokomarytest.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobarok.rokomarytest.di.DaggerApiComponent
import com.mobarok.rokomarytest.model.LoginService
import com.mobarok.rokomarytest.model.ResponseLogin
import com.mobarok.rokomarytest.model.ResponseRegister
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegisterViewModel : ViewModel() {
    @Inject
    lateinit var  loginService : LoginService
    private val disposable = CompositeDisposable()

    val responseRegister = MutableLiveData<ResponseRegister>();
    val registerError = MutableLiveData<Boolean>();
    val loading = MutableLiveData<Boolean>();

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun registerNow(userName:String?,email:String?,password:String?) {
        loading.value = true
        disposable.add(
            loginService.registerNow(userName,email,password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<ResponseRegister>(){
                    override fun onSuccess(value: ResponseRegister?) {
                        responseRegister.value = value!!;
                        registerError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("mmmmmm", "onError: "+e.toString())
                        registerError.value = true;
                        loading.value = false;
                    }

                })

        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}
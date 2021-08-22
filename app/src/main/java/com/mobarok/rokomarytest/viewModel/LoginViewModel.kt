package com.mobarok.rokomarytest.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobarok.rokomarytest.di.DaggerApiComponent
import com.mobarok.rokomarytest.model.LoginService
import com.mobarok.rokomarytest.model.ResponseLogin
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel : ViewModel() {
    @Inject
    lateinit var  loginService : LoginService
    private val disposable = CompositeDisposable()

    val responseLogin = MutableLiveData<ResponseLogin>();
    val loginError = MutableLiveData<Boolean>();
    val loading = MutableLiveData<Boolean>();

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun login(userName:String?,password:String?) {
        loading.value = true
        disposable.add(
            loginService.loginNow(userName,password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<ResponseLogin>(){
                    override fun onSuccess(value: ResponseLogin?) {
                        responseLogin.value = value!!;
                        loginError.value = false
                        loading.value = false
                    }
                    override fun onError(e: Throwable?) {
                        print("mmmm "+e.toString())
                        loginError.value = true;
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
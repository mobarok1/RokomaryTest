package com.mobarok.rokomarytest.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobarok.rokomarytest.di.DaggerApiComponent
import com.mobarok.rokomarytest.model.BookModel
import com.mobarok.rokomarytest.model.BookService
import com.mobarok.rokomarytest.model.ResponseBook
import com.mobarok.rokomarytest.util.SharedPrefManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewArrivalViewModel : ViewModel() {
    @Inject
    lateinit var  bookService: BookService
    private val disposable = CompositeDisposable()

    val books = MutableLiveData<List<BookModel>>();
    val bookLoadError = MutableLiveData<Boolean>();
    val loading = MutableLiveData<Boolean>();
    val count = MutableLiveData<Int>();

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun fetchBooks(context: Context) {
        val token : String? = SharedPrefManager.with(context).getString("access","");
        loading.value = true
        count.value = 0
        disposable.add(
            bookService.getNewArrival(token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object:DisposableSingleObserver<ResponseBook>(){
                    override fun onSuccess(value: ResponseBook?) {
                        books.value = value?.models!!;
                        bookLoadError.value = false
                        loading.value = false
                        count.value = 0
                        print(books.value!!.size)
                    }

                    override fun onError(e: Throwable?) {
                        bookLoadError.value = true;
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
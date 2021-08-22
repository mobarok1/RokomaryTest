package com.mobarok.rokomarytest.model

import com.mobarok.rokomarytest.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

public class BookService {
    @Inject
    lateinit var api : BookApi;

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getNewArrival(token : String?): Single<ResponseBook> {
        return api.getNewArrival("Bearer "+token!!)
    }

    fun getExploreBook(token : String?): Single<ResponseBook> {
        return api.getExploreBooks("Bearer "+token!!)
    }
}
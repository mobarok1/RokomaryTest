package com.mobarok.rokomarytest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobarok.rokomarytest.R
import com.mobarok.rokomarytest.util.SharedPrefManager
import com.mobarok.rokomarytest.viewModel.ExploreViewModel
import com.mobarok.rokomarytest.viewModel.NewArrivalViewModel

class Home : AppCompatActivity() {
    var token : String? = null
    var newArrivalAdapter =  NewArrivalAdapter(arrayListOf());
    var exploreAdapter =  ExploreAdapter(arrayListOf());
    lateinit var newArrivalRecyclerView : RecyclerView
    lateinit var exploreRecyclerView : RecyclerView
    private lateinit var viewModelNewArrival : NewArrivalViewModel;
    private lateinit var viewModelExplore : ExploreViewModel;
    private lateinit var progressDialog : ProgressBar;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        newArrivalRecyclerView = findViewById(R.id.recyclerViewNewArrival)
        exploreRecyclerView = findViewById(R.id.recyclerViewExplore)
        progressDialog = findViewById(R.id.progressDialog)
        viewModelNewArrival = ViewModelProvider(this).get(NewArrivalViewModel::class.java)
        viewModelExplore = ViewModelProvider(this).get(ExploreViewModel::class.java)
        token =  SharedPrefManager.with(this).getString("access","mmmmm")

        newArrivalRecyclerView.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            adapter = newArrivalAdapter
        }
        exploreRecyclerView.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            adapter = exploreAdapter
        }
        viewModelNewArrival.fetchBooks(this)
        viewModelExplore.fetchBooks(this)
        observerNewArrivalViewModel()
        observerExploreViewModel()
    }

    private fun observerNewArrivalViewModel(){
        viewModelNewArrival.books.observe(this, Observer {
                books-> books?.let{
            newArrivalRecyclerView.visibility = View.VISIBLE
            newArrivalAdapter.updateCountries(it)
        }
        })

        viewModelNewArrival.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                progressDialog.visibility = if(isLoading) View.VISIBLE else View.GONE
                if(it) {

                    newArrivalRecyclerView.visibility = View.INVISIBLE

                }
            }
        })
    }
    private fun observerExploreViewModel(){
        viewModelNewArrival.books.observe(this, Observer {
                books-> books?.let{
            exploreRecyclerView.visibility = View.VISIBLE
            exploreAdapter.updateCountries(it)
        }
        })

        viewModelExplore.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                progressDialog.visibility = if(isLoading) View.VISIBLE else View.GONE
                if(it) {

                    exploreRecyclerView.visibility = View.INVISIBLE

                }
            }
        })
    }
}
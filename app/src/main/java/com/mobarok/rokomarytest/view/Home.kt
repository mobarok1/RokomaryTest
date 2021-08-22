package com.mobarok.rokomarytest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobarok.rokomarytest.R
import com.mobarok.rokomarytest.util.SharedPrefManager

class Home : AppCompatActivity() {
    var token : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        token =  SharedPrefManager.with(this).getString("access","mmmmm");
    }
}
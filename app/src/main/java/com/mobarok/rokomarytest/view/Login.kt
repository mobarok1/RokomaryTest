package com.mobarok.rokomarytest.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.mobarok.rokomarytest.R
import com.mobarok.rokomarytest.util.SharedPrefManager
import com.mobarok.rokomarytest.viewModel.LoginViewModel
import com.mobarok.rokomarytest.viewModel.RegisterViewModel

class Login : AppCompatActivity() {
    lateinit var btnLogin:MaterialButton
    lateinit var btnRegister:Button
    lateinit var userName:EditText
    lateinit var password:EditText
    private lateinit var viewModel : LoginViewModel;
    lateinit var cardView: CardView
    lateinit var loader: ProgressBar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java);
        btnLogin = findViewById(R.id.btn_login)
        btnRegister = findViewById(R.id.btn_register)
        userName = findViewById(R.id.et_userName)
        password = findViewById(R.id.et_userPassword)
        loader = findViewById(R.id.progressDialog)
        cardView = findViewById(R.id.cardView)

        btnLogin.setOnClickListener{
            viewModel.login(userName = userName.text.toString(),password = password.text.toString())
        }
        btnRegister.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
        observerViewModel();

    }

    private fun observerViewModel(){
        viewModel.responseLogin.observe(this, Observer {
                response-> response?.let{
            cardView.visibility = View.VISIBLE
            SharedPrefManager.with(this).save("refresh",response.refresh);
            SharedPrefManager.with(this).save("access",response.access);

            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish()

            print("mmmm" + response.access);
        }
        })

        viewModel.loginError.observe(this,Observer{
                isError-> isError?.let{
            if(isError)
                Toast.makeText(applicationContext,"Error found", Toast.LENGTH_LONG).show()
            else{
                Toast.makeText(applicationContext,"Login Success", Toast.LENGTH_LONG).show()
            }
        }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loader.visibility = if(isLoading) View.VISIBLE else View.GONE
                if(it) {
                    cardView.visibility = View.GONE
                }
            }
        })
    }
}
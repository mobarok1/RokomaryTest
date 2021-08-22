package com.mobarok.rokomarytest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.mobarok.rokomarytest.R
import com.mobarok.rokomarytest.util.SharedPrefManager
import com.mobarok.rokomarytest.viewModel.RegisterViewModel

class Register : AppCompatActivity() {
    lateinit var btnRegister: MaterialButton
    lateinit var btnBack: Button
    lateinit var userName: EditText
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var loader: ProgressBar
    lateinit var cardView: CardView
    private lateinit var viewModel : RegisterViewModel;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java);
        btnRegister = findViewById(R.id.btn_register)
        btnBack = findViewById(R.id.btn_back)
        userName = findViewById(R.id.et_userName)
        password = findViewById(R.id.et_userPassword)
        email = findViewById(R.id.et_userMail)
        cardView = findViewById(R.id.cardView)
        loader = findViewById(R.id.progressDialog)

        btnRegister.setOnClickListener{
            viewModel.registerNow(userName = userName.text.toString(),email = email.text.toString(),password = password.text.toString())
        }
        btnBack.setOnClickListener{
            finish()
        }
        observerViewModel();
    }

    private fun observerViewModel(){
        viewModel.responseRegister.observe(this, Observer {
                response-> response?.let{
            cardView.visibility = VISIBLE
            print("mmmm" + response.success);
        }
        })

        viewModel.registerError.observe(this,Observer{
                isError-> isError?.let{
                    cardView.visibility = VISIBLE
                    if(isError)
                        Toast.makeText(applicationContext,"Error found",Toast.LENGTH_LONG).show()
                    else{
                       showSuccessMessage()
                    }
                }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loader.visibility = if(isLoading) View.VISIBLE else View.GONE
                if(it) {
                    cardView.visibility = View.GONE
                }else{
                    cardView.visibility = View.VISIBLE

                }
            }
        })
    }

    private fun showSuccessMessage(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmation")
            .setMessage("Registration successfully Completed")
            .setPositiveButton("Login"
            ) { dialogInterface, _ ->
                dialogInterface.dismiss()
                finish()
            }.setNegativeButton("Close"){ dialogInterface, _ ->
                dialogInterface.dismiss()
            }.create().show()
    }

}
package com.example.tasktodo.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.tasktodo.R
import com.example.tasktodo.data.AppPreferences
import com.example.tasktodo.data.MainListeners
import com.example.tasktodo.ui.viewmodel.LoginViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity(), MainListeners{

    private lateinit var editTextNumberDocument: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonSingIn: Button
    private lateinit var backgroundProgress: View
    private lateinit var progressBar: ProgressBar
    private lateinit var appBar: Toolbar
    private val viewModel: LoginViewModel by viewModels()
    private var databaseReference: DatabaseReference = Firebase.database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        backgroundProgress = findViewById(R.id.backgroundProgress)
        progressBar = findViewById(R.id.progressBar)
        appBar = findViewById(R.id.actionBar)
        editTextNumberDocument = findViewById(R.id.editTextNumberDocument)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonSingIn = findViewById(R.id.buttonSignIn)
        appBar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        setSupportActionBar(appBar)
        AppPreferences.init(this)
        buttonLogin.setOnClickListener {
            showProgress()
            if(viewModel.validateFieldsLogin(editTextNumberDocument, editTextPassword)){
                databaseReference.child("users/${editTextNumberDocument.text.toString()}")
                    .get().addOnSuccessListener { data ->
                    hideProgress()
                    if(data.exists()){
                        val passwordUser = data.child("password").value
                        if(editTextPassword.text.toString() == passwordUser){
                            AppPreferences.userName = data.child("names").value.toString()
                            AppPreferences.userSurname = data.child("surnames").value.toString()
                            AppPreferences.userDocument = data.child("numberDocument").value.toString()
                            AppPreferences.userPassword = data.child("password").value.toString()
                            AppPreferences.userNationality = data.child("nationality").value.toString()
                            AppPreferences.userCellphone = data.child("numberCellphone").value.toString()
                            AppPreferences.userTypeDocument = data.child("typeDocument").value.toString()
                            AppPreferences.userBirthday = data.child("dateBirthday").value.toString()
                            AppPreferences.userEmail = data.child("email").value.toString()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }else{
                            editTextPassword.error = "la contraseña no es correcta"
                        }
                    }else{
                        Toast.makeText(this, "Este usuario no esta registrado en la aplicacion", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener {
                    hideProgress()
                    Toast.makeText(this, "Este usuario no esta registrado en la aplicacion", Toast.LENGTH_SHORT).show()
                }
            }else{
                hideProgress()
            }
        }
        buttonSingIn.setOnClickListener {
            startActivity(Intent(this, RegisterNewUserActivity::class.java))
        }

        if(AppPreferences.userDocument.isNotEmpty() && AppPreferences.userName.isNotEmpty() && AppPreferences.userEmail.isNotEmpty()){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

    override fun showProgress() {
        backgroundProgress.visibility = View.VISIBLE
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        backgroundProgress.visibility = View.GONE
        progressBar.visibility = View.GONE
    }
}
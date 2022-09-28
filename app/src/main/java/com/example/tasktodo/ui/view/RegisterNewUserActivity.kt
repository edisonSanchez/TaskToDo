package com.example.tasktodo.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.tasktodo.R
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import com.example.tasktodo.data.User
import com.example.tasktodo.ui.viewmodel.RegisterNewUserViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

const val NAME_TABLE = "Users"

class RegisterNewUserActivity : AppCompatActivity() {

    private lateinit var names: TextInputEditText
    private lateinit var surnames: TextInputEditText
    private lateinit var nationality: TextInputEditText
    private lateinit var typeDocument: TextInputEditText
    private lateinit var numberDocument: TextInputEditText
    private lateinit var dateBirthday: TextInputEditText
    private lateinit var numberCellphone: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var replyPassword: TextInputEditText
    private lateinit var btnRegister: Button
    private lateinit var backgroundProgress: View
    private lateinit var progressBar: ProgressBar
    private var reference: DatabaseReference = Firebase.database.reference
    private val viewModel: RegisterNewUserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_new_user)
        names = findViewById(R.id.names)
        surnames = findViewById(R.id.surnames)
        nationality = findViewById(R.id.nationality)
        typeDocument = findViewById(R.id.typeDocument)
        numberDocument = findViewById(R.id.numberDocument)
        dateBirthday = findViewById(R.id.dateBirthday)
        numberCellphone = findViewById(R.id.numberCellphone)
        backgroundProgress = findViewById(R.id.backgroundProgress)
        progressBar = findViewById(R.id.progressBar)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        replyPassword = findViewById(R.id.replyPassword)
        btnRegister = findViewById(R.id.btnRegister)
        btnRegister.setOnClickListener {
            backgroundProgress.visibility = View.VISIBLE
            progressBar.visibility = View.VISIBLE
            if(viewModel.validateFields(names, surnames, nationality, typeDocument, numberDocument, dateBirthday,
                numberCellphone, email, password, replyPassword)){
                reference.child("users/${numberDocument.text.toString()}").setValue(User(names.text.toString(), surnames.text.toString(),
                nationality.text.toString(), typeDocument.text.toString(), numberDocument.text.toString(),
                dateBirthday.text.toString(), numberCellphone.text.toString(), email.text.toString(),
                password.text.toString())).addOnSuccessListener {
                    backgroundProgress.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    Toast.makeText(this@RegisterNewUserActivity, "Usuario registrado con exito", Toast.LENGTH_SHORT).show()
                    val handler = Handler(Looper.myLooper()!!)
                    handler.postDelayed({
                        finish()
                    }, 2000)
                }.addOnFailureListener {
                    backgroundProgress.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    Toast.makeText(this@RegisterNewUserActivity, "No fue posible registrar este usuario", Toast.LENGTH_SHORT).show()
                }
            }else{
                backgroundProgress.visibility = View.GONE
                progressBar.visibility = View.GONE
            }
        }


    }


}
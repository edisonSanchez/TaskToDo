package com.example.tasktodo.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import com.example.tasktodo.R
import androidx.activity.viewModels
import com.example.tasktodo.data.DatePickerDialog
import com.example.tasktodo.data.User
import com.example.tasktodo.data.showTwoDigits
import com.example.tasktodo.ui.viewmodel.RegisterNewUserViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

const val NAME_TABLE = "Users"

class RegisterNewUserActivity : AppCompatActivity() {

    private lateinit var names: TextInputEditText
    private lateinit var surnames: TextInputEditText
    private lateinit var nationality: TextInputEditText
    private lateinit var contentTypeDocument: TextInputLayout
    private lateinit var typeDocument: Spinner
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
    private var valueSpinner = ""
    private var listTypeDocument = ArrayList<String>()
    private var initialYear = 0
    private var initialMonth = 0
    private var initialDay = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_new_user)
        names = findViewById(R.id.names)
        surnames = findViewById(R.id.surnames)
        nationality = findViewById(R.id.nationality)
        typeDocument = findViewById(R.id.fieldTypeDocument)
        numberDocument = findViewById(R.id.numberDocument)
        dateBirthday = findViewById(R.id.dateBirthday)
        numberCellphone = findViewById(R.id.numberCellphone)
        backgroundProgress = findViewById(R.id.backgroundProgress)
        progressBar = findViewById(R.id.progressBar)
        contentTypeDocument =  findViewById(R.id.contentTypeDocument)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        replyPassword = findViewById(R.id.replyPassword)
        btnRegister = findViewById(R.id.btnRegister)
        getCurrentDate()
        listTypeDocument = resources.getStringArray(R.array.typeDocument).toList() as ArrayList<String>
        val adapterSpinner = ArrayAdapter.createFromResource(this, R.array.typeDocument, android.R.layout.simple_spinner_item)
        typeDocument.adapter = adapterSpinner
        typeDocument.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapter: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                if(pos != 0){
                    valueSpinner = listTypeDocument[pos]
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
        dateBirthday.setOnClickListener {
            val datePicker = DatePickerDialog(this, initialYear, initialMonth-1, initialDay) {
                _, year, month, day ->
                initialYear = year
                initialMonth = month + 1
                initialDay = day
                val currentDate  ="$initialYear-${showTwoDigits(initialMonth)}-${showTwoDigits(initialDay)}"
                dateBirthday.setText(currentDate)
            }
            datePicker.show(supportFragmentManager, "DatePicker")
        }
        btnRegister.setOnClickListener {
            backgroundProgress.visibility = View.VISIBLE
            progressBar.visibility = View.VISIBLE
            if(viewModel.validateFields(names, surnames, nationality, valueSpinner, contentTypeDocument, numberDocument, dateBirthday,
                numberCellphone, email, password, replyPassword)){
                reference.child("users/${numberDocument.text.toString()}").setValue(User(names.text.toString(), surnames.text.toString(),
                nationality.text.toString(), valueSpinner, numberDocument.text.toString(),
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

    private fun getCurrentDate(){
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        initialYear = calendar.get(Calendar.YEAR)
        initialMonth = calendar.get(Calendar.MONTH) + 1
        initialDay = calendar.get(Calendar.DAY_OF_MONTH)
        val currentDate  ="$initialYear-${showTwoDigits(initialMonth)}-${showTwoDigits(initialDay)}"
        dateBirthday.setText(currentDate)
    }


}
package com.example.tasktodo.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText

class LoginViewModel : ViewModel() {

    fun validateFieldsLogin(numberDocument: TextInputEditText, password: TextInputEditText) : Boolean {
        var isValidate = true
        val fields = ArrayList<Boolean>()
        if(numberDocument.text.toString().isEmpty()){
            numberDocument.error = "ingresa un numero de identificacion valido"
            numberDocument.requestFocus()
            fields.add(false)
        }else{
            fields.add(true)
        }

        if(password.text.toString().isEmpty()){
            password.error = "la contraseña no es correcta"
            password.requestFocus()
            fields.add(false)
        }else{
            fields.add(true)
        }

        for(boolean in fields){
            if (!boolean){
                isValidate = false
                break
            }
        }
        return isValidate
    }

}
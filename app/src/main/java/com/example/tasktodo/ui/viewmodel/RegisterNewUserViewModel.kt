package com.example.tasktodo.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText

class RegisterNewUserViewModel : ViewModel() {


    fun validateFields(names: TextInputEditText, surnames: TextInputEditText, nationality: TextInputEditText,
        documentType: TextInputEditText, numberDocument: TextInputEditText, dateBirthday: TextInputEditText,
        numberCellphone: TextInputEditText, email: TextInputEditText, password: TextInputEditText,
        repeatPassword: TextInputEditText) : Boolean {
        var isValidate = true
        val validateFields: ArrayList<Boolean> = ArrayList()
        if(names.text.toString().isEmpty()){
            names.error = "Este campo es obligatorio"
            names.requestFocus()
            validateFields.add(false)
        }else{
           validateFields.add(true)
        }

        if(surnames.text.toString().isEmpty()){
            surnames.error = "Este campo es obligatorio"
            surnames.requestFocus()
            validateFields.add(false)
        }else{
            validateFields.add(true)
        }

        if (nationality.text.toString().isEmpty()){
            nationality.error = "Este campo es obligatorio"
            nationality.requestFocus()
            validateFields.add(false)
        }else{
            validateFields.add(true)
        }

        if(documentType.text.toString().isEmpty()){
            documentType.error = "Este campo es obligatorio"
            documentType.requestFocus()
            validateFields.add(false)
        }else{
            validateFields.add(true)
        }

        if(numberDocument.text.toString().isEmpty()){
            numberDocument.error = "Este campo es obligatorio"
            numberDocument.requestFocus()
            validateFields.add(false)
        }else{
            validateFields.add(true)
        }

        if (dateBirthday.text.toString().isEmpty()){
            dateBirthday.error = "Este campo es obligatorio"
            dateBirthday.requestFocus()
            validateFields.add(false)
        }else{
            validateFields.add(true)
        }

        if(numberCellphone.text.toString().isEmpty()){
            numberCellphone.error = "Este campo es obligatorio"
            numberCellphone.requestFocus()
            validateFields.add(false)
        }else{
            validateFields.add(true)
        }

        if(email.text.toString().isEmpty()){
            email.error = "Este campo es obligatorio"
            email.requestFocus()
            validateFields.add(false)
        }else{
            validateFields.add(true)
        }

        if(password.text.toString().isEmpty()){
            password.error = "Este campo es obligatorio"
            password.requestFocus()
            validateFields.add(false)
        }else{
            validateFields.add(true)
        }

        if(repeatPassword.text.toString().isNotEmpty() && repeatPassword.text.toString() == password.text.toString()){
            validateFields.add(true)
        }else{
            repeatPassword.error = "las contraseñas no coinciden"
            repeatPassword.requestFocus()
            validateFields.add(false)
        }

        for(boolean in validateFields){
            if(!boolean){
                 isValidate = false
                break
            }
        }

        return isValidate
    }


}
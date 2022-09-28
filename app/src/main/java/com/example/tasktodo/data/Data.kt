package com.example.tasktodo.data

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

data class User (
    var names: String,
    var surnames: String,
    var  nationality: String,
    var typeDocument: String,
    var numberDocument: String,
    var dateBirthday: String,
    var numberCellphone: String,
    var email: String,
    var password: String)

data class Case (
    var title: String,
    var description: String,
    val numberBankAccount: String,
    val contactNumber: String)

fun hideKeyboard(context: Context, view: View){
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
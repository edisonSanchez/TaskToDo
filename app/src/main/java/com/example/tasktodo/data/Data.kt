package com.example.tasktodo.data

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.io.File

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

data class Event (
    var name: String,
    var image: File,
    val hour: String,
    val days: ArrayList<String>)

fun hideKeyboard(context: Context, view: View){
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun showTwoDigits(num: Int) : String{
    return if(num > 9) num.toString() else "0$num"
}
package com.example.tasktodo.data

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

object AppPreferences {

    private const val USER_NAME = "userName"
    private const val USER_SURNAME = "userSurname"
    private const val USER_DATE_BIRTHDAY = "birthday"
    private const val USER_NATIONALITY = "nationality"
    private const val USER_CELLPHONE = "cellphone"
    private const val USER_DOCUMENT = "userDocument"
    private const val USER_PASSWORD = "userPassword"
    private const val USER_TYPE_DOCUMENT = "typeDocument"
    private const val USER_EMAIL = "userEmail"
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit ) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var userName: String
    get() = sharedPreferences.getString(USER_NAME, "") ?:""
    set(value) = sharedPreferences.edit {
        it.putString(USER_NAME, value)
    }

    var userSurname: String
    get() = sharedPreferences.getString(USER_SURNAME, "") ?:""
    set(value) = sharedPreferences.edit {
        it.putString(USER_SURNAME, value)
    }

    var userEmail: String
    get() = sharedPreferences.getString(USER_EMAIL, "") ?:""
    set(value) = sharedPreferences.edit {
        it.putString(USER_EMAIL, value)
    }

    var userNationality: String
    get() = sharedPreferences.getString(USER_NATIONALITY, "") ?:""
    set(value) = sharedPreferences.edit {
        it.putString(USER_NATIONALITY, value)
    }

    var userTypeDocument: String
    get() = sharedPreferences.getString(USER_TYPE_DOCUMENT, "") ?:""
    set(value) = sharedPreferences.edit {
        it.putString(USER_TYPE_DOCUMENT, value)
    }

    var userDocument: String
    get() = sharedPreferences.getString(USER_DOCUMENT, "") ?:""
    set(value) = sharedPreferences.edit {
        it.putString(USER_DOCUMENT, value)
    }

    var userBirthday: String
    get() = sharedPreferences.getString(USER_DATE_BIRTHDAY, "") ?:""
    set(value) = sharedPreferences.edit {
        it.putString(USER_DATE_BIRTHDAY, value)
    }

    var userCellphone: String
    get() = sharedPreferences.getString(USER_CELLPHONE, "") ?:""
    set(value) = sharedPreferences.edit {
        it.putString(USER_CELLPHONE, value)
    }

    var userPassword: String
    get() = sharedPreferences.getString(USER_PASSWORD, "") ?:""
    set(value) = sharedPreferences.edit {
        it.putString(USER_PASSWORD, value)
    }

    fun removeData(){
        userEmail = ""
        userDocument = ""
        userBirthday = ""
        userSurname = ""
        userName = ""
        userPassword = ""
        userCellphone = ""
        userTypeDocument = ""
        userNationality = ""
    }
}
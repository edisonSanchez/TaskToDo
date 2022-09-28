package com.example.tasktodo.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import com.example.tasktodo.R
import com.example.tasktodo.data.AppPreferences
import com.example.tasktodo.data.User
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class EditProfileFragment : Fragment() {

    private lateinit var fieldName: TextInputEditText
    private lateinit var fieldSurname: TextInputEditText
    private lateinit var fieldEmail: TextInputEditText
    private lateinit var fieldCellphone: TextInputEditText
    private lateinit var fieldNationality: TextInputEditText
    private lateinit var fieldPassword: TextInputEditText
    private lateinit var fieldBirthday: TextInputEditText
    private lateinit var backgroundProgress: View
    private lateinit var progressBar: ProgressBar
    private lateinit var buttonEdit: Button
    private var databaseReference: DatabaseReference = Firebase.database.reference
    private var enableEdit = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_edit_profile, container, false)
        fieldName = view.findViewById(R.id.fieldNames)
        fieldSurname = view.findViewById(R.id.fieldSurname)
        fieldEmail = view.findViewById(R.id.fieldEmail)
        fieldCellphone = view.findViewById(R.id.fieldCellphone)
        fieldNationality = view.findViewById(R.id.fieldNationality)
        fieldPassword = view.findViewById(R.id.fieldPassword)
        fieldBirthday = view.findViewById(R.id.fieldBirthday)
        buttonEdit = view.findViewById(R.id.buttonEdit)
        backgroundProgress = view.findViewById(R.id.backgroundProgress)
        progressBar = view.findViewById(R.id.progressBar)
        AppPreferences.init(requireContext())
        showDataUser()
        buttonEdit.setOnClickListener {
            if(!enableEdit){
                fieldName.isEnabled = true
                fieldSurname.isEnabled = true
                fieldEmail.isEnabled = true
                fieldCellphone.isEnabled = true
                fieldNationality.isEnabled = true
                fieldPassword.isEnabled = true
                fieldBirthday.isEnabled = true
                enableEdit = true
                buttonEdit.text = "Guardar"
            }else{
                backgroundProgress.visibility = View.VISIBLE
                progressBar.visibility = View.VISIBLE
                fieldName.isEnabled = false
                fieldSurname.isEnabled = false
                fieldEmail.isEnabled = false
                fieldCellphone.isEnabled = false
                fieldNationality.isEnabled = false
                fieldPassword.isEnabled = false
                fieldBirthday.isEnabled = false
                enableEdit = false
                val user = User(fieldName.text.toString(), fieldSurname.text.toString(), fieldNationality.text.toString(),
                AppPreferences.userTypeDocument, AppPreferences.userDocument, fieldBirthday.text.toString(), fieldCellphone.text.toString(),
                fieldEmail.text.toString(), fieldPassword.text.toString())
                databaseReference.child("users/${AppPreferences.userDocument}").setValue(user).addOnSuccessListener {
                    AppPreferences.userName = fieldName.text.toString()
                    AppPreferences.userSurname = fieldSurname.text.toString()
                    AppPreferences.userNationality = fieldNationality.text.toString()
                    AppPreferences.userBirthday = fieldBirthday.text.toString()
                    AppPreferences.userCellphone = fieldCellphone.text.toString()
                    AppPreferences.userPassword = fieldPassword.text.toString()
                    AppPreferences.userEmail = fieldEmail.text.toString()
                    showDataUser()
                    backgroundProgress.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Se han actualizado sus datos con exito!", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    backgroundProgress.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Algo salio mal! intentalo de nuevo", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return view
    }

    private fun showDataUser(){
        fieldName.setText(AppPreferences.userName)
        fieldSurname.setText(AppPreferences.userSurname)
        fieldEmail.setText(AppPreferences.userEmail)
        fieldCellphone.setText(AppPreferences.userCellphone)
        fieldNationality.setText(AppPreferences.userNationality)
        fieldPassword.setText(AppPreferences.userPassword)
        fieldBirthday.setText(AppPreferences.userBirthday)
        buttonEdit.text = "Editar"
    }

}
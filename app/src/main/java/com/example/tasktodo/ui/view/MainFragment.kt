package com.example.tasktodo.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.tasktodo.R
import com.example.tasktodo.data.Case
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainFragment : Fragment() {

    private lateinit var backgroundProgress: View
    private lateinit var progressBar: ProgressBar
    private var databaseReference: DatabaseReference = Firebase.database.reference
    private var cases = ArrayList<Case>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        backgroundProgress = view.findViewById(R.id.backgroundProgress)
        progressBar = view.findViewById(R.id.progressBar)
        backgroundProgress.visibility = View.VISIBLE
        progressBar.visibility = View.VISIBLE
        databaseReference.child("cases").get().addOnSuccessListener { data ->
            for (case in data.children){
                val title = case.child("title").value.toString()
                val description = case.child("description").value.toString()
                val numberBankAccount = case.child("numberBankAccount").value.toString()
                val contactNumber = case.child("contactNumber").value.toString()
                cases.add(Case(title, description, numberBankAccount, contactNumber))
            }
            backgroundProgress.visibility = View.GONE
            progressBar.visibility = View.GONE
        }.addOnFailureListener {
            backgroundProgress.visibility = View.GONE
            progressBar.visibility = View.GONE
        }
        return view
    }

}
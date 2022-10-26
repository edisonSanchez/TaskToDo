package com.example.tasktodo.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktodo.R
import com.example.tasktodo.data.showTwoDigits
import java.util.*


class MainFragment : Fragment() {

    private lateinit var backgroundProgress: View
    private lateinit var progressBar: ProgressBar
    private lateinit var buttonAddEvent: Button
    private lateinit var recyclerViewEvents: RecyclerView
    private lateinit var textDate: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        backgroundProgress = view.findViewById(R.id.backgroundProgress)
        progressBar = view.findViewById(R.id.progressBar)
        buttonAddEvent = view.findViewById(R.id.buttonAddEvents)
        recyclerViewEvents = view.findViewById(R.id.recyclerViewEvents)
        textDate = view.findViewById(R.id.textDate)
        buttonAddEvent.setOnClickListener {
            startActivity(Intent(requireContext(), CreateEventActivity::class.java))
        }
        getCurrentDate()
        return view
    }

    private fun getCurrentDate(){
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val currentDate  ="$year-${showTwoDigits(month)}-${showTwoDigits(day)}"
        textDate.text = currentDate
    }


}
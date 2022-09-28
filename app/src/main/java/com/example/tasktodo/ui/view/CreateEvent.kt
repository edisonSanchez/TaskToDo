package com.example.tasktodo.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.tasktodo.R

class CreateEvent : Fragment() {

    private lateinit var backgroundProgress: View
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_create_case, container, false)
        backgroundProgress = view.findViewById(R.id.backgroundProgress)
        progressBar = view.findViewById(R.id.progressBar)
        return view
    }

}
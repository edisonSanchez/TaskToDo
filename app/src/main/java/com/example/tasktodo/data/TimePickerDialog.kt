package com.example.tasktodo.data

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class TimePickerDialog(private val contextActivity: Context, private val initialHour: Int,
                       private val initialMinutes: Int,
                       private val changeDataListener : TimePickerDialog.OnTimeSetListener) : DialogFragment() {

    private lateinit var timePickerDialog: TimePickerDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        timePickerDialog = TimePickerDialog(contextActivity, changeDataListener, initialHour, initialMinutes, true)
        return timePickerDialog
    }
}
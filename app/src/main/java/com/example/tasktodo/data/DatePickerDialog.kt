package com.example.tasktodo.data

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.app.DatePickerDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.tasktodo.R


class DatePickerDialog(private val contextActivity: Context, private var initialYear: Int, private var initialMonth: Int,
                       private val initialDay: Int, private val changeDataListener: DatePickerDialog.OnDateSetListener)
    : DialogFragment() {

    private lateinit var datePickerDialog: DatePickerDialog

    override fun onResume() {
        super.onResume()
        datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(contextActivity, R.color.clear_blue))
        datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(contextActivity, R.color.clear_blue))
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        datePickerDialog = DatePickerDialog(contextActivity, R.style.date_picker_theme, changeDataListener,
            initialYear, initialMonth, initialDay)
        return datePickerDialog
    }
}
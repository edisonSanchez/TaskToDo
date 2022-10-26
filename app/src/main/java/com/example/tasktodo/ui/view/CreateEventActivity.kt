package com.example.tasktodo.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.tasktodo.R
import com.example.tasktodo.data.TimePickerDialog
import com.example.tasktodo.ui.viewmodel.CreateEventViewModel
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class CreateEventActivity : AppCompatActivity() {

    private lateinit var imageEvent: ImageView
    private lateinit var fieldName: TextInputEditText
    private lateinit var fieldHour: TextInputEditText
    private lateinit var checkBoxMonday: CheckBox
    private lateinit var checkBoxTuesday: CheckBox
    private lateinit var checkBoxWednesday: CheckBox
    private lateinit var checkBoxThursday: CheckBox
    private lateinit var checkBoxFriday: CheckBox
    private lateinit var checkBoxSaturday: CheckBox
    private lateinit var checkBoxSunday: CheckBox
    private lateinit var checkBoxAll: CheckBox
    private lateinit var buttonCreateEvent: Button
    private lateinit var backgroundProgress: View
    private lateinit var progressBar: ProgressBar
    private val createEventViewModel: CreateEventViewModel by viewModels()
    private var initialHour = 0
    private var initialMinutes = 0
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_event)
        toolbar = findViewById(R.id.actionBar)
        imageEvent = findViewById(R.id.imageEvent)
        fieldName = findViewById(R.id.fieldNameEvent)
        fieldHour = findViewById(R.id.fieldHour)
        checkBoxMonday = findViewById(R.id.checkboxMonday)
        checkBoxTuesday = findViewById(R.id.checkboxTuesday)
        checkBoxWednesday = findViewById(R.id.checkboxWednesday)
        checkBoxThursday = findViewById(R.id.checkboxThursday)
        checkBoxFriday = findViewById(R.id.checkboxFriday)
        checkBoxSaturday = findViewById(R.id.checkboxSaturday)
        checkBoxSunday = findViewById(R.id.checkboxSunday)
        checkBoxAll = findViewById(R.id.checkboxAll)
        buttonCreateEvent = findViewById(R.id.btnCreateEvent)
        backgroundProgress = findViewById(R.id.backgroundProgress)
        progressBar = findViewById(R.id.progressBar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_arrow_back)
        getCurrentHour()
        imageEvent.setOnClickListener {
            showDialogOptionsImage()
        }
        fieldHour.setOnClickListener {
            val datePicker = TimePickerDialog(this, initialHour, initialMinutes) { _, hour, minutes ->
                initialHour = hour
                initialMinutes = minutes
                val hourSelected = "$initialHour:$initialMinutes"
                fieldHour.setText(hourSelected)
            }
            datePicker.show(supportFragmentManager, "TimePicker")
        }
    }

    private fun getCurrentHour() {
        val calendar = Calendar.getInstance()
        initialHour = calendar.get(Calendar.HOUR_OF_DAY)
        initialMinutes = calendar.get(Calendar.MINUTE)
    }

    private fun showDialogOptionsImage(){
        val alertDialog = AlertDialog.Builder(this)
            .setTitle(getString(R.string.add_image_title))
            .setItems(resources.getStringArray(R.array.options_image)) { _, pos ->
                when(pos){
                    0 -> addImageFromGallery()
                    1 -> deleteImage()
                }
            }.create()
        alertDialog.show()
    }

    private fun addImageFromGallery(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)

    }

    private fun deleteImage(){

    }
}
package com.rockspace.myapplication.UI.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import androidx.databinding.DataBindingUtil
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.rockspace.myapplication.R
import com.rockspace.myapplication.databinding.ActivityManageCommunityPostBinding
import java.util.*

class ManageCommunityPostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManageCommunityPostBinding
    private var eventHour = 0
    private var eventMin = 0
    private var eventDate = MaterialDatePicker.todayInUtcMilliseconds()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_community_post)

        binding.etTime.setOnClickListener { openTimePicker() }
        binding.etDate.setOnClickListener { openDatePicker() }
        binding.btnBack.setOnClickListener { onBackPressed() }

    }

    private fun openTimePicker() {
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(eventHour)
            .setMinute(eventMin)
            .setTitleText("Set Time")
            .build()
        picker.show(supportFragmentManager, "Tag")

        picker.addOnPositiveButtonClickListener {
            eventHour = picker.hour
            eventMin = picker.minute

            val hour = when {
                picker.hour == 0 -> 12
                picker.hour > 12 -> picker.hour - 12
                else -> picker.hour
            }

            val format = if(picker.hour>12) "pm" else "am"

            binding.etTime.setText("$hour:${picker.minute} $format")
        }
    }

    private fun openDatePicker() {
        val picker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .setSelection(eventDate)
            .build()

        picker.show(supportFragmentManager, "Tag")

        picker.addOnPositiveButtonClickListener {
            eventDate = it
            val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            calendar.time = Date(it)

            val dateText = "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH)}/" +
                    "${calendar.get(Calendar.YEAR)}"

            binding.etDate.setText(dateText)
        }
    }
}
package com.rockspace.myapplication.UI.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import androidx.databinding.DataBindingUtil
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.rockspace.myapplication.R
import com.rockspace.myapplication.databinding.ActivityManageCommunityPostBinding

class ManageCommunityPostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManageCommunityPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_community_post)

        binding.etTime.setOnClickListener {
            openTimePicker()
        }
    }

    private fun openTimePicker() {
        val is24Hour = is24HourFormat(this)
        val clockFormat = if (is24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H

        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(clockFormat)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Set Time")
            .build()
        picker.show(supportFragmentManager, "Tag")
    }
}
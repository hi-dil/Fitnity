package com.rockspace.myapplication.UI.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rockspace.myapplication.R
import com.rockspace.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.textview.setOnClickListener {
            val intent = Intent(this, ManageCommunityPostActivity::class.java)
            startActivity(intent)
        }
    }
}
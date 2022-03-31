package com.example.demochatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.feature.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO navigation component로 변경 필요
        LoginActivity.start(this)
    }
}
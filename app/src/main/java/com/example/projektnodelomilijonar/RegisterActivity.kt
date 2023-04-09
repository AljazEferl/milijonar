package com.example.projektnodelomilijonar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projektnodelomilijonar.databinding.ActivityRegisterBinding
import java.sql.*


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}

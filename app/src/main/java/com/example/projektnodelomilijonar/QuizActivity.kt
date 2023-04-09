package com.example.projektnodelomilijonar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projektnodelomilijonar.databinding.ActivityQuizBinding
import com.example.projektnodelomilijonar.databinding.ActivityRegisterBinding

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textVprasanje.text = "Kdo od naštetih ni bil član slovenske košarkarske reprezentance, ko je postala evropski prvak?"
        binding.buttonOdg1.text = "Jaka Blažič"
        binding.buttonOdg2.text = "Aleksej Nikolić"
        binding.buttonOdg3.text = "Vlatko Čančar"
        binding.buttonOdg4.text = "Gregor Hrovat"

    }
}
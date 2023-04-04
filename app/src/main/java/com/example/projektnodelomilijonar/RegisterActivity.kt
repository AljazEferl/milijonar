package com.example.projektnodelomilijonar

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.projektnodelomilijonar.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    private val sharedPreferences by lazy {
        getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val loginText: TextView = binding.twLogin
        loginText.setOnClickListener(){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnRegister.setOnClickListener(){
            //if user is Login it will be Logouted
            val isLoggedIn = sharedPreferences.getBoolean("isLogIn", false)
            if (isLoggedIn) {
                val editor = sharedPreferences.edit()
                editor.putBoolean("isLogIn", false)
                editor.apply()
                finish()
            }
            //get email and password from the user
            permormSignup()
        }
    }
    private fun permormSignup() {
        val email = findViewById<EditText>(R.id.editText_email_register)
        val password = findViewById<EditText>(R.id.editText_password_register)

        if(email.text.isEmpty() || password.text.isEmpty()){
            Toast.makeText(this,"Izpolnite vsa vnosna polja", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()

        auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, let move to the next activity i.e InputActivity
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(baseContext, "Registrirani ste, zdaj se lahko prijavite!",
                        Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Preverjanje ni uspelo!",
                        Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener(){
                Toast.makeText(this,"Zaznana napaka ${it.localizedMessage}",Toast.LENGTH_SHORT)
                    .show()
            }
    }


}
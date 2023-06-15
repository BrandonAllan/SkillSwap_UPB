package com.example.skillswap_upb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var signupEmail: EditText
    lateinit var signupPassword: EditText
    lateinit var signupUsername: EditText
    lateinit var signupButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance();
        signupEmail = findViewById(R.id.InputEmail)
        signupPassword = findViewById(R.id.InputPassword)
        signupUsername = findViewById(R.id.InputUsername)
        signupButton = findViewById(R.id.buttonRegister)

        val signUpText: TextView = findViewById(R.id.alredyHaveAccount)
        signUpText.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
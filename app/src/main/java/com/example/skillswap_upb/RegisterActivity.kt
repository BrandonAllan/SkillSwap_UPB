package com.example.skillswap_upb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    //Firebase initialization
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance();

        //Logic for the register button
        val buttonRegister = findViewById<Button>(R.id.buttonRegister)
        buttonRegister.setOnClickListener {
            val email = findViewById<EditText>(R.id.InputEmail).text.toString().trim()
            val username = findViewById<EditText>(R.id.InputUsername).text.toString().trim()
            val password = findViewById<EditText>(R.id.InputPassword).text.toString().trim()
            val passwordVerification = findViewById<EditText>(R.id.InputPasswordAgain).text.toString().trim()

            if(username.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty() && password == passwordVerification){
                registerUser(email,password)
            } else {
                Toast.makeText(this, "Please fill in all the fields correctly.", Toast.LENGTH_SHORT).show()
            }
        }

        // Going back to LoginActivity if user alredy has an account
        val haveAccountText: TextView = findViewById(R.id.alredyHaveAccount)
        haveAccountText.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    //Register User function
    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT,).show()
                }
            }
    }
}
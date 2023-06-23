package com.example.skillswap_upb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    //Firebase initialization
    private lateinit var auth: FirebaseAuth
    private lateinit var dbref: DatabaseReference

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
                registerUser(username,email,password)
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
    private fun registerUser(name:String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    addUserToDatabase(name,email,auth.currentUser?.uid!!)
                    Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT,).show()
                }
            }
    }

    private fun addUserToDatabase(name: String,email: String, uid: String){
        dbref = FirebaseDatabase.getInstance().getReference()
        dbref.child("user").child(uid).setValue(ItemUser(name,email,uid))
    }
}
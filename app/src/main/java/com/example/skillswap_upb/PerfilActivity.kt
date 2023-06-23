package com.example.skillswap_upb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class PerfilActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val deleteAccountButton = findViewById<Button>(R.id.Deleteperfil)
        deleteAccountButton.setOnClickListener {
            deleteAccount()
            }
        }
    private fun deleteAccount() {
        val user = auth.currentUser
        user?.delete()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Account deletion successful
                    Toast.makeText(this, "Account deleted successfully.", Toast.LENGTH_SHORT).show()
                    // Proceed with any additional cleanup or actions
                } else {
                    // Account deletion failed
                    Toast.makeText(this, "Failed to delete account. Please try again.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}


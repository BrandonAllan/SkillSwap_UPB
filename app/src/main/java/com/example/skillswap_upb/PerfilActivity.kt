package com.example.skillswap_upb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import androidx.fragment.app.Fragment

import androidx.fragment.app.FragmentTransaction
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

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_nav -> {
                    loadActivity(ChatList::class.java)
                    true
                }
                R.id.search_nav -> {
                    loadActivity(MainActivity::class.java)
                    true
                }
                R.id.perfil_nav -> {
                    loadActivity(PerfilActivity::class.java)
                    true
                }
                else -> false
            }
        }
    }

    private fun loadActivity(activityClass: Class<out AppCompatActivity>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
        finish()
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


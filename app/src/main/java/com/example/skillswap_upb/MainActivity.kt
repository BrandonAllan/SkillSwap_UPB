package com.example.skillswap_upb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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


    }

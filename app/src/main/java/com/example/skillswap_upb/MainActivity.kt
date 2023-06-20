package com.example.skillswap_upb

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.proyecto.R.id.historial


class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     val buton=findViewById<Button>(R.id.historial)
        buton.setOnClickListener{
            val lanzar= Intent(this,historalChats::class.java)
            startActivity(lanzar)
        }
    }


}

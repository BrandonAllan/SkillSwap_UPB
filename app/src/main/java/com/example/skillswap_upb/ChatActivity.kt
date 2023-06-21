package com.example.skillswap_upb

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.skillswap_upb.databinding.ActivityChatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class ChatActivity : AppCompatActivity() {

    var binding:ActivityChatBinding? = null
    val adapter:MessageAdapter? = null
    var message:ArrayList<Message>? = null
    var senderRoom:String? = null
    var receiverRoom:String? = null
    var database: FirebaseDatabase? = null
    var storage: FirebaseStorage? = null
    var dialog: ProgressDialog? = null
    var senderUid:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        setSupportActionBar(binding!!.toolbar)
        database = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()
        dialog = ProgressDialog(this@ChatActivity)
        dialog!!.setMessage("Uploading image...")
        dialog!!.setCancelable(false)
        message = ArrayList()
        val name = intent.getStringExtra("name")
        val profile = intent.getStringExtra("image")
        binding!!.profileName.text = name
        Glide.with(this@ChatActivity).load(profile)
            .placeholder(R.drawable.chat_image)
            .into(binding!!.profileImage)
        binding!!.backButton.setOnClickListener{ finish() }

    }
}
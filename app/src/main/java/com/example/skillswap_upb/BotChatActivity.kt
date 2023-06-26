package com.example.skillswap_upb

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skillswap_upb.adapter.BotMessageAdapter
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class BotChatActivity : AppCompatActivity() {

    private lateinit var messageRecyclerView: RecyclerView
    private lateinit var messageBox: EditText
    private lateinit var sendButton: ImageView
    private lateinit var messageAdapter: BotMessageAdapter
    private lateinit var messageList: ArrayList<BotMessage>
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bot_chat)

        messageRecyclerView = findViewById(R.id.messageRecyclerView)
        messageBox = findViewById(R.id.messageBox)
        sendButton = findViewById(R.id.sendButton)
        messageList = ArrayList()
        messageAdapter = BotMessageAdapter(this,messageList)

        messageRecyclerView.layoutManager = LinearLayoutManager(this)
        messageRecyclerView.adapter = messageAdapter

        sendButton.setOnClickListener {
            val question = messageBox.text.toString().trim()
            addToChat(question, "me")
            messageBox.setText("")
            getResponse(question){ response ->
                runOnUiThread{
                    addToChat(response, "bot")
                }
            }
        }

    }

    fun addToChat(message: String, sentBy: String) {
        runOnUiThread {
            messageList.add(BotMessage(message, sentBy))
            messageAdapter.notifyDataSetChanged()
            messageRecyclerView.smoothScrollToPosition(messageAdapter.itemCount)
        }
    }

    fun getResponse(question: String, callback: (String) -> Unit) {
        val apikey = "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"
        val url = "https://api.openai.com/v1/completions"
        val requestBody = """
        {
            "model": "text-davinci-003",
            "prompt": "$question",
            "max_tokens": 500,
            "temperature": 0
        }
    """.trimIndent()

        val request = Request.Builder()
            .url(url)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $apikey")
            .post(requestBody.toRequestBody("application/json".toMediaTypeOrNull()))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("error", "API failed", e)
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                if (body != null) {
                    val json = JSONObject(body)
                    val choicesArray = json.getJSONArray("choices")
                    if (choicesArray.length() > 0) {
                        val choice = choicesArray.getJSONObject(0)
                        val completionText = choice.getString("text")
                        runOnUiThread {
                            addToChat(completionText, "bot")
                        }
                    }
                }
            }

        })
    }


}
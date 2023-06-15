package com.example.datingapp

import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.threetenabp.AndroidThreeTen
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.threeten.bp.Instant

class Chat : AppCompatActivity() {

    private lateinit var  chatAdapter: ChatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        AndroidThreeTen.init(this)

        val matchName  = intent.getStringExtra("MATCH_NAME")
        findViewById<TextView>(R.id.textView12).text = matchName

        val chats = ArrayList<MessageModel>()
        chats.add(MessageModel("Hello", 1234, "22:00"))
        chats.add(MessageModel("Hi, there! Hi, there! Hi, there! Hi, there! Hi, there! Hi, there! Hi, there! Hi, there! Hi, there! Hi, there! Hi, there! Hi, there! Hi, there! ", 1235, "22:10"))
        val chatView : RecyclerView = findViewById(R.id.chat_view)
        chatView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        chatAdapter = ChatAdapter(chats, 1234, this)
        chatView.adapter = chatAdapter
    }
}
package com.example.datingapp

import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.threetenabp.AndroidThreeTen
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.threeten.bp.Instant

class Chat : AppCompatActivity() {

    private lateinit var  chatAdapter: ChatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        setUpBtns()

        // Date time API
        AndroidThreeTen.init(this)

        val matchName  = intent.getStringExtra("MATCH_NAME")
        findViewById<TextView>(R.id.textView12).text = matchName

        val chats = ArrayList<MessageModel>()

        //getChats()

        // Using faux data for representation purposes
        chats.add(MessageModel("Hello", 1234, "22:00"))
        chats.add(MessageModel("Hi, there! Hi, there! Hi, there! Hi, there! Hi, there! Hi, there! Hi, there! Hi, there! Hi, there! Hi, there! Hi, there! Hi, there! Hi, there! ", 1235, "22:10"))
        val chatView : RecyclerView = findViewById(R.id.chat_view)
        chatView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        chatAdapter = ChatAdapter(chats, 1234, this)
        chatView.adapter = chatAdapter
    }

    private fun setUpBtns() {
        val callBtn : Button = findViewById(R.id.call_btn)
        val videoCallBtn : Button = findViewById(R.id.video_call_btn)
        val moreBtn : Button = findViewById(R.id.more_btn)

        callBtn.setOnClickListener {
            // TODO: Implement calling functionality
        }
        videoCallBtn.setOnClickListener {
            // TODO: Implement video-calling functionality
        }
        moreBtn.setOnClickListener {
            // TODO: Implement more options according to requirement
        }

    }

    private fun getChats() {
        // TODO: Implement this function to retrieve user chats from the database/device cache
    }

}
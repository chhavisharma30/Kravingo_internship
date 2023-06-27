package com.example.datingapp

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.threetenabp.AndroidThreeTen
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Chat : AppCompatActivity() {

    private lateinit var  chatAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // Date time API
        AndroidThreeTen.init(this)

        val matchName = intent.getStringExtra("MATCH_NAME")
        val matchID = intent.getStringExtra("MATCH_ID")
        if (matchName != null && matchID != null) {
            findViewById<TextView>(R.id.textView12).text = matchName
            setUpBtns(matchID, matchName)
        }

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

    private fun setUpBtns(matchID: String, matchName: String) {
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
            showOptionsDialog(matchID, matchName)
        }

    }

    private fun getChats() {
        // TODO: Implement this function to retrieve user chats from the database/device cache
    }

    private fun blockMatch(matchID : String) {
        // TODO: Implement this function to block a match
    }

    private fun showOptionsDialog(matchID : String, matchName : String) {
        val optionsDialog =  Dialog(this);

        optionsDialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.options_dialog);
            setUpOptionsBtns(optionsDialog, matchID, matchName)

            window?.apply {
                setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
                setGravity(Gravity.BOTTOM);

                attributes?.windowAnimations = R.style.DialogAnimation;
            }
            show()
        }

    }

    private fun setUpOptionsBtns(optionsDialog : Dialog, matchID: String, matchName: String) {
        val blockBtn : Button = optionsDialog.findViewById(R.id.block_btn)
        val reportBtn : Button = optionsDialog.findViewById(R.id.report_btn)

        blockBtn.setOnClickListener {

            // blockMatch(matchID)  matchName should ideally be switched with matchID
            Toast.makeText(this, "$matchName has been blocked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Inbox::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        reportBtn.setOnClickListener {
            optionsDialog.dismiss()
            Intent(this, ReportMatchActivity::class.java).also {
                it.putExtra("MATCH_ID", matchID)
                startActivity(it)
            }
        }

    }
}
package com.example.datingapp.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.datingapp.presentation.adapters.InboxAdapter
import com.example.datingapp.presentation.adapters.InboxRequestAdapter
import com.example.datingapp.R
import com.example.datingapp.data.local.data_src.Match
import com.example.datingapp.data.local.data_src.Message
import com.example.datingapp.domain.models.InboxMatchDetails
import com.example.datingapp.presentation.ui.listeners.InboxViewListener
import com.example.datingapp.presentation.viewmodels.MatchViewModel
import com.example.datingapp.presentation.viewmodels.MessageViewModel
import com.example.datingapp.presentation.viewmodels.MessageViewModelFactory
import kotlinx.android.synthetic.main.activity_inbox.*

class Inbox : AppCompatActivity(), InboxViewListener {
    private lateinit var inboxAdapter: InboxAdapter
    private lateinit var requestAdapter: InboxRequestAdapter
    private lateinit var matchViewModel: MatchViewModel
    private lateinit var requestViewModel: MatchViewModel
    private lateinit var chatViewModel: MessageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inbox)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView2.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        inboxAdapter = InboxAdapter(this)
        requestAdapter = InboxRequestAdapter()
        recyclerView.adapter = inboxAdapter
        recyclerView2.adapter = requestAdapter

        matchViewModel = ViewModelProvider(this)[MatchViewModel::class.java]
        matchViewModel.getMatches.observe(this, Observer { match ->
            inboxAdapter.setData(match)
        })

        // TODO: Change this implementation when Match Section is created, assuming you can pull requestData from Match Section using API and feed it to MatchRequestViewModel
        requestViewModel = ViewModelProvider(this)[MatchViewModel::class.java]
        requestViewModel.getMatches.observe(this, Observer { request ->
            requestAdapter.setData(request)
        })

        chatViewModel = ViewModelProvider(
            this,
            MessageViewModelFactory(application, "id002")
        )[MessageViewModel::class.java]

    // ------------------------- Test buttons -------------------------------------------------

    //TODO: Remove these buttons when pulling new match data from API becomes possible
//    findViewById<Button>(R.id.test_btn3).setOnClickListener {
//        addMessage2("id002")
//        addToDataBase()
//    }

    //------------------------------------------------------------------------------------------

    }


    // ------------------------- Test functions for database----------------------------------------

    // TODO: Remove these methods when pulling new match data from API becomes possible
//    private fun addToDataBase() {
//        val match1 = Match("id002", "Test User2")
//        val match2 = Match("id003", "Test User3")
//        val match3 = Match("id004", "Test User4")
//        matchViewModel.addMatch(match1)
//        matchViewModel.addMatch(match2)
//        matchViewModel.addMatch(match3)
//    }

//    private fun addMessage2(matchID: String) {
//        val msg = Message(0, matchID, "id0001", "Hey there, what's up?", false)
//        chatViewModel.insertMessage(msg)
//    }

    // ---------------------------------------------------------------------------------------------



    override fun itemClick(matchDetails: InboxMatchDetails) {
        Intent(this, Chat::class.java).also {
            it.putExtra("MATCH_NAME", matchDetails.userName)
            it.putExtra("MATCH_ID", matchDetails.userID)
            startActivity(it)
        }
    }

    override fun itemTouch(itemView: View, event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.hint_color
                    )
                )
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.transparent
                    )
                )
            }
        }
    }



}
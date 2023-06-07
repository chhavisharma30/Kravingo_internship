package com.example.datingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_inbox.*

class Inbox : AppCompatActivity() {
    private lateinit var inboxAdapter: InboxAdapter
    private lateinit var requestAdapter: InboxRequestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inbox)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

//         Assuming you have a list of profile data (Using fake data for now)
//            matches = getMatches()
        val matches = ArrayList<MatchModel>()
        matches.add(MatchModel("User 1", "1234"))
        matches.add(MatchModel("User 2", "6789"))
        matches.add(MatchModel("User 3", "0000"))

        inboxAdapter = InboxAdapter(matches, this)
        recyclerView.adapter = inboxAdapter

        // ** Passing the same list as matches for now however, requestList will have a different getRequests() call and hence a
        // different list, might also need to create a new data model for requests instead of using matchModel ** //
        requestAdapter = InboxRequestAdapter(matches, this)
        recyclerView2.adapter = requestAdapter
    }
}
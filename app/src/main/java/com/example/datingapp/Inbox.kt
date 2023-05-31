package com.example.datingapp

import ProfileAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_inbox.*

class Inbox : AppCompatActivity() {
    private lateinit var profileAdapter: ProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inbox)
        recyclerView2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Assuming you have a list of profile data
        //val profiles: List<ContactsContract.Profile> = getProfiles()

        //profileAdapter = ProfileAdapter(profiles)
        //recyclerView2.adapter = profileAdapter
    }
}
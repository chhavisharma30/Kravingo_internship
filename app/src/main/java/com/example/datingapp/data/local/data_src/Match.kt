package com.example.datingapp.data.local.data_src

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match_data")
data class Match(
    @PrimaryKey val userID: String,
    val userName: String,
    val userProfilePicture: Int? = null // change this to URL when using Firebase
)
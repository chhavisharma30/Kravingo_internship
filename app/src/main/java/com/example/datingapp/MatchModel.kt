package com.example.datingapp

data class MatchModel (
    val userName: String,
    val userID: String,
    val userProfilePicture: Int? = null // change this to URL when using Firebase
)


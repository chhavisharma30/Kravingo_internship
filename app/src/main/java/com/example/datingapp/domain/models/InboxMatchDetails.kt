package com.example.datingapp.domain.models

data class InboxMatchDetails(
    val userID: String,
    val userName : String,
    val userProfilePicture : Int? = null ,
    val messageContent: String,
    val sentTimeStamp: String,
    val isRead : Boolean
)

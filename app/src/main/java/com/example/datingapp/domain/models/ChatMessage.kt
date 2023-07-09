package com.example.datingapp.domain.models

data class ChatMessage(
    val messageID : Long,
    val senderID : String,
    val receiverID : String,
    val messageContent : String,
    val sentTimeStamp : String
)

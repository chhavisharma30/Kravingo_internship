package com.example.datingapp

import android.text.format.Time
import java.time.Instant
import java.util.GregorianCalendar

data class MessageModel(
    val messageContent : String,
    val senderID : Long,
    val timeStamp : String // TODO: Change this to some other unit later, using string only for demonstration purpose
)

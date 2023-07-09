package com.example.datingapp.data.local.data_src

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "messages_table")
data class Message(
    @PrimaryKey(autoGenerate = true) val messageID : Long,
    val senderID : String,
    val receiverID : String,
    val messageContent : String,
    val isRead : Boolean,

    val sentTimeStamp : String = DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now()).toString()
)

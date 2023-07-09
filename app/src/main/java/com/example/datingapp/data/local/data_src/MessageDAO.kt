package com.example.datingapp.data.local.data_src

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.datingapp.domain.models.ChatMessage

@Dao
interface MessageDAO {

    @Insert
    suspend fun insertMessage(message: Message)

    @Query("SELECT isRead FROM messages_table WHERE receiverID = :receiverID OR senderID = :receiverID ORDER BY sentTimeStamp DESC LIMIT 1")
    fun checkIfMessageIsRead(receiverID: String?): LiveData<Boolean>

    @Query("SELECT messageID, senderID, receiverID, messageContent, sentTimeStamp FROM messages_table WHERE receiverID = :rcvID OR senderID = :rcvID ORDER BY sentTimeStamp ASC")
    fun getMessages(rcvID : String?) : LiveData<List<ChatMessage>>

    @Query("DELETE FROM messages_table WHERE senderID = :rcvID OR receiverID = :rcvID")
    suspend fun deleteMessages(rcvID: String?)

    @Query("UPDATE messages_table SET isRead = 1 WHERE receiverID = :rcvID OR senderID = :rcvID")
    suspend fun readMessages(rcvID: String?)

    @Query("UPDATE messages_table SET isRead = 1 WHERE messageID = :msgID")
    suspend fun readMessage(msgID: String?)

}
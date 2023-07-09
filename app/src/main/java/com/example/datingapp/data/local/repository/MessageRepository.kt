package com.example.datingapp.data.local.repository

import androidx.lifecycle.LiveData
import com.example.datingapp.data.local.data_src.MessageDAO
import com.example.datingapp.domain.models.ChatMessage
import com.example.datingapp.data.local.data_src.Message

class MessageRepository(private val messageDAO: MessageDAO) {
    fun getMessages(receiverID: String?) : LiveData<List<ChatMessage>> = messageDAO.getMessages(receiverID)

    suspend fun insertMessage(message: Message) {
        messageDAO.insertMessage(message)
    }

    suspend fun deleteMessages(receiverID: String?) {
        messageDAO.deleteMessages(receiverID)
    }

    suspend fun readMessage(msgID: String?) {
        messageDAO.readMessage(msgID)
    }

    fun checkIfMessageIsRead(receiverID: String?) : LiveData<Boolean> = messageDAO.checkIfMessageIsRead(receiverID)


}
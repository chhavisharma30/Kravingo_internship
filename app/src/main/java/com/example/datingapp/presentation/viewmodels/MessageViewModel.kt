package com.example.datingapp.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.datingapp.data.local.data_src.UserDatabase
import com.example.datingapp.data.local.repository.MessageRepository
import com.example.datingapp.domain.models.ChatMessage
import com.example.datingapp.data.local.data_src.Message
import kotlinx.coroutines.*

class MessageViewModel(application: Application, receiverID: String?) : AndroidViewModel(application) {
    val getMessages : LiveData<List<ChatMessage>>
    val isLastMessageRead : LiveData<Boolean>
    private val messageRepository : MessageRepository

    init {
        val messageDAO = UserDatabase.getDataBase(application).getMessageDAO()
        messageRepository = MessageRepository(messageDAO)
        getMessages = messageRepository.getMessages(receiverID)
        isLastMessageRead = messageRepository.checkIfMessageIsRead(receiverID)
    }

    fun insertMessage(message: Message) {
        viewModelScope.launch(Dispatchers.IO) {
            messageRepository.insertMessage(message)
        }
    }

    fun readMessage(msgID :String?) {
        viewModelScope.launch(Dispatchers.IO) {
            messageRepository.readMessage(msgID)
        }
    }

    fun deleteMessages(receiverID : String?) {
        viewModelScope.launch(Dispatchers.IO) {
            messageRepository.deleteMessages(receiverID)
        }
    }

}

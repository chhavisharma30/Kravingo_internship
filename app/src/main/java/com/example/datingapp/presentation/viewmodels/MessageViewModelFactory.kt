package com.example.datingapp.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MessageViewModelFactory(private val application: Application, private val receiverID: String?): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = MessageViewModel(application, receiverID) as T
}

package com.example.datingapp.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.datingapp.data.local.data_src.UserDatabase
import com.example.datingapp.data.local.repository.MatchRepository
import com.example.datingapp.domain.models.InboxMatchDetails
import com.example.datingapp.data.local.data_src.Match
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MatchViewModel(application: Application) : AndroidViewModel(application) {
    val getMatches: LiveData<List<InboxMatchDetails>>
    private val matchRepository: MatchRepository

    init {
        val matchDAO = UserDatabase.getDataBase(application).getMatchDAO()
        matchRepository = MatchRepository(matchDAO)
        getMatches = matchRepository.getInboxMatches
    }

    fun addMatch(match: Match) {
        viewModelScope.launch(Dispatchers.IO) {
            matchRepository.addMatch(match)
        }
    }

    fun removeMatch(matchID : String) {
        viewModelScope.launch(Dispatchers.IO) {
            matchRepository.removeMatch(matchID)
        }
    }


}
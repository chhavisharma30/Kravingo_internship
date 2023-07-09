package com.example.datingapp.data.local.repository

import androidx.lifecycle.LiveData
import com.example.datingapp.data.local.data_src.MatchDAO
import com.example.datingapp.domain.models.InboxMatchDetails
import com.example.datingapp.data.local.data_src.Match

class MatchRepository(private val matchDAO: MatchDAO) {

    val getInboxMatches : LiveData<List<InboxMatchDetails>> = matchDAO.getInboxMatches()

    suspend fun addMatch(match: Match) {
        matchDAO.addNewMatch(match)
    }

    suspend fun removeMatch(matchID: String) {
        matchDAO.removeMatch(matchID)
    }

}
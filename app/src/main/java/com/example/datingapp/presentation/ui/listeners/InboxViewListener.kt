package com.example.datingapp.presentation.ui.listeners

import android.view.MotionEvent
import android.view.View
import com.example.datingapp.domain.models.InboxMatchDetails

interface InboxViewListener {
    fun itemClick(matchDetails: InboxMatchDetails)
    fun itemTouch(itemView: View, event : MotionEvent)
}
package com.example.datingapp.presentation.ui.viewholders

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.datingapp.R
import com.example.datingapp.domain.models.InboxMatchDetails
import com.example.datingapp.presentation.ui.listeners.InboxViewListener

class InboxViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val matchName: TextView = itemView.findViewById(R.id.nameTextView)
    private val matchProfilePicture: ImageView = itemView.findViewById(R.id.profileImageView)
    private val latestMessage: TextView = itemView.findViewById(R.id.latestMessageTextView)
    private val latestMessageTime: TextView = itemView.findViewById(R.id.timingTextView)
    private val newMessageBubble: TextView = itemView.findViewById(R.id.new_message_bubble)

    @SuppressLint("ClickableViewAccessibility")
    fun bindListeners(listener: InboxViewListener, matchDetails: InboxMatchDetails) {

        itemView.setOnClickListener {
            listener.itemClick(matchDetails)
        }

        itemView.setOnTouchListener { _, event ->
            listener.itemTouch(itemView, event)
            false
        }
    }


    fun bindData(matchDetails: InboxMatchDetails) {
        matchName.text = matchDetails.userName

        if (matchDetails.messageContent == "null") {
            latestMessage.text = "Start chatting with ${matchDetails.userName}"
            latestMessage.setTypeface(null, Typeface.BOLD)
        } else {
            latestMessage.typeface = Typeface.DEFAULT
            latestMessage.text = matchDetails.messageContent
        }
        if (matchDetails.sentTimeStamp != "null") latestMessageTime.text =
            matchDetails.sentTimeStamp.substring(11..15)

        if (matchDetails.isRead) {
            latestMessage.typeface = Typeface.DEFAULT
            newMessageBubble.visibility = View.INVISIBLE
        } else {
            latestMessage.setTypeface(null, Typeface.BOLD)
            newMessageBubble.visibility = View.VISIBLE
        }

        // TODO use glide to set image from URL later
        if (matchDetails.userProfilePicture != null) {
            matchProfilePicture.setImageResource(matchDetails.userProfilePicture)
        }
    }
}
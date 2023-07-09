package com.example.datingapp.presentation.ui.viewholders

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.datingapp.R
import com.example.datingapp.domain.models.ChatMessage

abstract class MessageViewHolder(itemView: View) : ChatViewHolder(itemView) {

    val messageContent : TextView = itemView.findViewById(R.id.message_content)
    val messageTimeStamp : TextView = itemView.findViewById(R.id.message_timestamp)
    val messageLayout : ConstraintLayout = itemView.findViewById(R.id.message_const_layout)
    val messageTextBox: ConstraintLayout = itemView.findViewById(R.id.message_textbox)

    // Invisible views used only for containing data
    val messageID : TextView = itemView.findViewById(R.id.message_id)
    val messageDateStamp : TextView  = itemView.findViewById(R.id.message_datestamp)

    //Method to bind message data to the view
    abstract fun bindData(message: ChatMessage)

    // Method to style a messagebox/ chat bubble according to whether it is a sender bubble or a receiver bubble
    abstract fun setUpMessageBoxStyle()
}
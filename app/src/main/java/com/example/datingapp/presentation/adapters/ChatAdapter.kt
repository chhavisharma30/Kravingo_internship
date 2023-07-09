package com.example.datingapp.presentation.adapters

import ReceiverViewHolder
import SenderViewHolder
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.datingapp.R
import com.example.datingapp.domain.models.ChatMessage
import com.example.datingapp.domain.models.DateSignifier
import com.example.datingapp.presentation.ui.viewholders.ChatViewHolder
import com.example.datingapp.presentation.ui.viewholders.DateSignifierView
import com.example.datingapp.presentation.ui.viewholders.MessageViewHolder
import com.example.datingapp.utils.Constants

class ChatAdapter(
    private val matchID: String?,
    private val context: Context
) :
    RecyclerView.Adapter<ChatViewHolder>() {

    private var messageList = listOf<Any>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {

        when(viewType) {
            Constants.senderView ->  {
                return SenderViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.message_layout, parent, false),
                    context
                )
            }
            Constants.receiverView -> {
                return ReceiverViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.message_layout, parent, false),
                    context
                )
            }
            else -> {
                return DateSignifierView(
                    LayoutInflater.from(parent.context).inflate(R.layout.date_signifier_layout, parent, false)
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(messageList[position] is ChatMessage) {
            if((messageList[position] as ChatMessage).senderID == matchID) Constants.receiverView
            else Constants.senderView
        } else Constants.dateSignifierView
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        if(holder is MessageViewHolder) {
            holder.bindData(messageList[position] as ChatMessage)
            holder.setUpMessageBoxStyle()
        }
        else if(holder is DateSignifierView) {
            holder.bindData(messageList[position] as DateSignifier)
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    fun setData(messageList : MutableList<Any>) {
        this.messageList = messageList
        notifyDataSetChanged()
    }
}
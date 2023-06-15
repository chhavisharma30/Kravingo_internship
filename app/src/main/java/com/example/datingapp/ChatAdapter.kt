package com.example.datingapp

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(
    private val messageList: ArrayList<MessageModel>,
    private val senderID: Long,
    private val context: Context
) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    private val senderView: Int = 0
    private val receiverView: Int = 1

    abstract inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val messageContent : TextView = itemView.findViewById(R.id.message_content)
        val messageTimeStamp : TextView = itemView.findViewById(R.id.message_timestamp)
        val messageLayout : ConstraintLayout = itemView.findViewById(R.id.message_const_layout)
        val messageTextBox: ConstraintLayout = itemView.findViewById(R.id.message_textbox)


        fun dpToPx(dp: Int): Int {
            val scale = Resources.getSystem().displayMetrics.density
            return (dp * scale + 0.5f).toInt()
        }

        //Method to bind message data to the view
        abstract fun bindData(message: MessageModel)

        // Method to style a messagebox/ chat bubble according to whether it is a sender bubble or a receiver bubble
        abstract fun setUpMessageBoxStyle()
    }

    inner class SenderViewHolder(itemView: View) : ViewHolder(itemView) {

        override fun bindData(message: MessageModel) {
            messageContent.text = message.messageContent

            // TODO: Implement proper method to bind date-time data when backend is ready
            messageTimeStamp.text = message.timeStamp.toString()
        }

        override fun setUpMessageBoxStyle() {
            messageLayout.setPadding(dpToPx(50), dpToPx(5), 0,dpToPx(5))
            messageContent.setTextColor(ContextCompat.getColor(context, R.color.white))

            messageTimeStamp.setTextColor(ContextCompat.getColor(context, R.color.white))
            messageTextBox.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.cherry))
            val backgroundDrawable : GradientDrawable = messageTextBox.background as GradientDrawable
            backgroundDrawable.cornerRadii = floatArrayOf(
                dpToPx(10).toFloat() ,dpToPx(10).toFloat(),
                0f, 0f,
                dpToPx(10).toFloat() ,dpToPx(10).toFloat(),
                dpToPx(10).toFloat() ,dpToPx(10).toFloat()
            )
            messageTextBox.background = backgroundDrawable

            val constraintSet = ConstraintSet()
            constraintSet.clone(messageLayout)
            constraintSet.clear(R.id.message_textbox, ConstraintSet.START)
            constraintSet.connect(
                R.id.message_textbox,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END
            )
            constraintSet.connect(
                R.id.message_textbox,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP
            )
            constraintSet.applyTo(messageLayout)
        }

    }

    inner class ReceiverViewHolder(itemView: View) : ViewHolder(itemView) {

        override fun bindData(message: MessageModel) {
            messageContent.text = message.messageContent

            // TODO: Implement proper method to bind date-time data when backend is ready
            messageTimeStamp.text = message.timeStamp.toString()
        }

        override fun setUpMessageBoxStyle() {
            messageContent.setTextColor(ContextCompat.getColor(context, R.color.black))
            messageTimeStamp.setTextColor(ContextCompat.getColor(context, R.color.black))
            messageLayout.setPadding(0,dpToPx(5), dpToPx(50),dpToPx(5))
            messageTextBox.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.hint_color))
            val backgroundDrawable : GradientDrawable = messageTextBox.background as GradientDrawable
            backgroundDrawable.cornerRadii = floatArrayOf(
                0f, 0f,
                dpToPx(10).toFloat() ,dpToPx(10).toFloat(),
                dpToPx(10).toFloat() ,dpToPx(10).toFloat(),
                dpToPx(10).toFloat() ,dpToPx(10).toFloat()
            )
            messageTextBox.background = backgroundDrawable
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == senderView) {
            SenderViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.message_layout, parent, false)
            )
        } else {
            ReceiverViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.message_layout, parent, false)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (messageList[position].senderID == senderID) senderView
        else receiverView
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(messageList[position])
        holder.setUpMessageBoxStyle()
    }

    override fun getItemCount(): Int {
        return messageList.size
    }
}
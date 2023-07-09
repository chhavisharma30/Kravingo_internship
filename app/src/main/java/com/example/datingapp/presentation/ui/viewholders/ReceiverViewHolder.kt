import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.content.ContextCompat
import com.example.datingapp.R
import com.example.datingapp.domain.models.ChatMessage

import com.example.datingapp.presentation.ui.viewholders.MessageViewHolder
import com.example.datingapp.utils.dpToPx

class ReceiverViewHolder(itemView: View, private val context: Context) : MessageViewHolder(itemView) {

    override fun bindData(message: ChatMessage) {
        messageContent.text = message.messageContent
        messageID.text = message.messageID.toString()
        messageTimeStamp.text = message.sentTimeStamp.substring(11..15) // stores time (is Visible)
        messageDateStamp.text = message.sentTimeStamp.substring(0..9) // stores date (is not Visible)
    }

    override fun setUpMessageBoxStyle() {
        messageContent.setTextColor(ContextCompat.getColor(context, R.color.black))
        messageTimeStamp.setTextColor(ContextCompat.getColor(context, R.color.black))
        messageLayout.setPadding(0, dpToPx(context, 5f), dpToPx(context, 50f), dpToPx(context, 5f))
        messageTextBox.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(context,
            R.color.hint_color
        ))
        val backgroundDrawable : GradientDrawable = messageTextBox.background as GradientDrawable
        backgroundDrawable.cornerRadii = floatArrayOf(
            0f, 0f,
            dpToPx(context, 10f).toFloat() , dpToPx(context, 10f).toFloat(),
            dpToPx(context, 10f).toFloat() , dpToPx(context,10f).toFloat(),
            dpToPx(context, 10f).toFloat() , dpToPx(context, 10f).toFloat()
        )
        messageTextBox.background = backgroundDrawable
    }
}
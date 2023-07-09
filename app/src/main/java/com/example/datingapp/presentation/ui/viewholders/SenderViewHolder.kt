import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.example.datingapp.R
import com.example.datingapp.domain.models.ChatMessage
import com.example.datingapp.presentation.ui.viewholders.MessageViewHolder
import com.example.datingapp.utils.dpToPx

class SenderViewHolder(itemView: View, private val context: Context) : MessageViewHolder(itemView) {

    override fun bindData(message: ChatMessage) {
        messageContent.text = message.messageContent
        messageTimeStamp.text = message.sentTimeStamp.substring(11..15) // stores time (is Visible)
        messageID.text = message.messageID.toString()
        messageDateStamp.text = message.sentTimeStamp.substring(0..9) // stores date (is not Visible)
    }

    override fun setUpMessageBoxStyle() {
        messageLayout.setPadding(dpToPx(context, 50f), dpToPx(context, 5f), 0, dpToPx(context, 5f))
        messageContent.setTextColor(ContextCompat.getColor(context, R.color.white))

        messageTimeStamp.setTextColor(ContextCompat.getColor(context, R.color.white))
        messageTextBox.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(context,
            R.color.cherry
        ))
        val backgroundDrawable : GradientDrawable = messageTextBox.background as GradientDrawable
        backgroundDrawable.cornerRadii = floatArrayOf(
            dpToPx(context, 10f).toFloat() , dpToPx(context, 10f).toFloat(),
            0f, 0f,
            dpToPx(context, 10f).toFloat() , dpToPx(context, 10f).toFloat(),
            dpToPx(context, 10f).toFloat() , dpToPx(context, 10f).toFloat()
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
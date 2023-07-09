package com.example.datingapp.presentation.activities

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.datingapp.presentation.adapters.ChatAdapter
import com.example.datingapp.R
import com.example.datingapp.domain.models.ChatMessage
import com.example.datingapp.domain.models.DateSignifier
import com.example.datingapp.data.local.data_src.Message
import com.example.datingapp.presentation.ui.viewholders.MessageViewHolder
import com.example.datingapp.presentation.viewmodels.MatchViewModel
import com.example.datingapp.presentation.viewmodels.MessageViewModel
import com.example.datingapp.presentation.viewmodels.MessageViewModelFactory
import com.example.datingapp.utils.extractDayFromDate


class Chat : AppCompatActivity() {


    private lateinit var  chatAdapter: ChatAdapter
    private lateinit var chatView : RecyclerView
    private lateinit var chatViewModel: MessageViewModel
    private lateinit var matchViewModel: MatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val matchName = intent.getStringExtra("MATCH_NAME")
        val matchID = intent.getStringExtra("MATCH_ID")
        if (matchName != null && matchID != null) {
            findViewById<TextView>(R.id.textView12).text = matchName
            setUpBtns(matchID, matchName)
        }

        val newMessageNotification : TextView = findViewById(R.id.new_msg_notification)
        val floatingDateSignifier : TextView = findViewById(R.id.floating_msg_date_signifier)

        chatView = findViewById(R.id.chat_view)
        chatView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        chatAdapter = ChatAdapter(matchID, application)
        chatView.adapter = chatAdapter

        chatViewModel = ViewModelProvider(this, MessageViewModelFactory(application, matchID))[MessageViewModel::class.java]

        chatView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                if(!recyclerView.canScrollVertically(-1)) floatingDateSignifier.visibility = View.INVISIBLE

                // Floating date signifier will only appear on screen when the user is navigating up in the chatView
                if(dy < 0)  {
                    floatingDateSignifier.visibility = View.VISIBLE
                }
                else {
                    floatingDateSignifier.visibility = View.INVISIBLE
                }

                var firstMessageItem = recyclerView.findViewHolderForAdapterPosition(
                    firstVisibleItemPosition
                )
                if(firstMessageItem is MessageViewHolder) {
                    floatingDateSignifier.text = extractDayFromDate(firstMessageItem.messageDateStamp.text.toString())
                }
                else {
                    // If firstItem in the recyclerView is not a Message but a DateSignifier, then pick the item right next
                    // to it since it is bound to be a Message
                    firstMessageItem = recyclerView.findViewHolderForAdapterPosition(firstVisibleItemPosition +1)
                    floatingDateSignifier.text = extractDayFromDate((firstMessageItem as MessageViewHolder).messageDateStamp.text.toString())
                }

                for (i in firstVisibleItemPosition..lastVisibleItemPosition) {
                    val viewHolder = recyclerView.findViewHolderForAdapterPosition(i)
                    if (viewHolder is MessageViewHolder) {
                        val messageID = viewHolder.messageID.text.toString()
                        chatViewModel.readMessage(messageID)
                    }
                    }
                }
            }
        )

        var openFirstTime = true
        chatViewModel.getMessages.observe(this) { messages ->
            val processedMessages = addDateSignifiers(messages)
            chatAdapter.setData(processedMessages)

            // We only wish to scroll to the end of the Chat recyclerView when the activity is first opened,
            // not when our activity is already open
            if(openFirstTime) {
                newMessageNotification.visibility = View.INVISIBLE
                val itemCount = chatAdapter.itemCount
                chatView.scrollToPosition(itemCount - 1)
                openFirstTime = false
            }
        }

        chatViewModel.isLastMessageRead.observe(this) { status ->
            // Status might be null when the there is no message to be read, i.e. a new match with no messages in chat
            if(status == null) return@observe
            if(status) {
                newMessageNotification.visibility = View.INVISIBLE
            }
            else {
                newMessageNotification.visibility = View.VISIBLE
            }
        }

        // TODO: Change this to RequestViewModel when it is implemented and can pull data from MatchSection/API
        matchViewModel = ViewModelProvider(this)[MatchViewModel::class.java]

        val sendMessageBtn : Button = findViewById(R.id.send_msg_btn)
        val messageTextBox : EditText = findViewById(R.id.send_message_edit_text)

        messageTextBox.addTextChangedListener {
            sendMessageBtn.isEnabled = !it.isNullOrEmpty()
        }
    }

    private fun addDateSignifiers(messages : List<ChatMessage>) : MutableList<Any> {
        val updatedItems =  mutableListOf<Any>()
        if(messages.isEmpty()) return updatedItems
        updatedItems.add(DateSignifier(messages[0].sentTimeStamp.substring(0..9)))
        for(i in 0..messages.size-2) {
            updatedItems.add(messages[i])
            if(messages[i].sentTimeStamp.substring(0..9) != messages[i+1].sentTimeStamp.substring(0..9)) {
                updatedItems.add(DateSignifier(messages[i+1].sentTimeStamp.substring(0..9)))
            }
        }
        updatedItems.add(messages[messages.size-1])
        return updatedItems
    }


    private fun setUpBtns(matchID: String, matchName: String) {
        val callBtn : Button = findViewById(R.id.call_btn)
        val videoCallBtn : Button = findViewById(R.id.video_call_btn)
        val moreBtn : Button = findViewById(R.id.more_btn)
        val sendMessageBtn : Button = findViewById(R.id.send_msg_btn)
        val messageTextBox : EditText = findViewById(R.id.send_message_edit_text)


        // ------------------------- Test Buttons and their implementation -------------------------

        // TODO : Remove this method when receiving messages through FCM becomes possible
//        val rcvMessageButton : Button = findViewById(R.id.test_btn2)
//        rcvMessageButton.setOnClickListener { addMessage2(matchID) }

        // -----------------------------------------------------------------------------------------

        sendMessageBtn.setOnClickListener {
            val messageContent : String = messageTextBox.text.toString()
            messageTextBox.text = null
            sendMessage(matchID, messageContent)
        }
        callBtn.setOnClickListener {
            // TODO: Implement calling functionality
        }
        videoCallBtn.setOnClickListener {
            // TODO: Implement video-calling functionality
        }
        moreBtn.setOnClickListener {
            showOptionsDialog(matchID, matchName)
        }

    }

    // ------------------------- Test functions to add faux data -----------------------------------

    // TODO : Remove this method when receiving messages through FCM becomes possible
//    private fun addMessage2(matchID: String)  {
//        val msg = Message(0, matchID,"id0001", "Hello", false)
//        chatViewModel.insertMessage(msg)
//    }

    // ---------------------------------------------------------------------------------------------

    private fun sendMessage(matchID: String, messageContent : String)  {

        // TODO: Implement a function to pull userID from userDatabase somewhere above in the activity and pass it down to this function and replace "id001" with it
        val message = Message(0, "id0001", matchID, messageContent, true)
        chatViewModel.insertMessage(message)

        // TODO : Add API request to server to send push notification to receiverID with messageContent as payload
    }


    private fun blockMatch(matchID : String) {
        chatViewModel.deleteMessages(matchID)
        matchViewModel.removeMatch(matchID)

        // TODO : Add API request to server to remove current userID from blocked user ID's match database
    }

    private fun showOptionsDialog(matchID : String, matchName : String) {
        val optionsDialog =  Dialog(this)

        optionsDialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.options_dialog)
            setUpOptionsBtns(optionsDialog, matchID, matchName)

            window?.apply {
                setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                setGravity(Gravity.BOTTOM)

                attributes?.windowAnimations = R.style.DialogAnimation
            }
            show()
        }
    }

    private fun setUpOptionsBtns(optionsDialog : Dialog, matchID: String, matchName: String) {
        val blockBtn : Button = optionsDialog.findViewById(R.id.block_btn)
        val reportBtn : Button = optionsDialog.findViewById(R.id.report_btn)

        blockBtn.setOnClickListener {
            blockMatch(matchID)
            Toast.makeText(this, "$matchName has been blocked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Inbox::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        reportBtn.setOnClickListener {
            optionsDialog.dismiss()
            Intent(this, ReportMatchActivity::class.java).also {
                it.putExtra("MATCH_ID", matchID)
                startActivity(it)
            }
        }
    }
}

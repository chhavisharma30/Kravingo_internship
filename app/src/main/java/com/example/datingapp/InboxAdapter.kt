package com.example.datingapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class InboxAdapter(private val matchList : ArrayList<MatchModel>, private val context : Context):
    RecyclerView.Adapter<InboxAdapter.ViewHolder>() {

        inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

            init {

                // Setting onTouchListener to change the background of the itemView when touched to give a feeling of it being clicked
                itemView.setOnTouchListener(fun(view: View, event: MotionEvent): Boolean {
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.hint_color))
                        }
                        MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                            itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent))
                        }
                    }
                    return false
                })

                // Setting onClickListener to open Chat activity when an item in the inbox is pressed
                itemView.setOnClickListener {
                    val context = context
                    val position = adapterPosition
                    val intent = Intent(context, Chat::class.java)
                    val matchName = matchList[position].userName
                    val matchID = matchList[position].userID
                    intent.putExtra("MATCH_NAME", matchName)
                    intent.putExtra("MATCH_ID", matchID)
                    context.startActivity(intent)
                    }
                }

            private val matchName : TextView = itemView.findViewById(R.id.nameTextView)
            private val matchProfilePicture : ImageView = itemView.findViewById(R.id.profileImageView)
            fun bindData(matchDetails : MatchModel) {
                matchName.text = matchDetails.userName
                // TODO use glide to set image from URL later
                if (matchDetails.userProfilePicture != null) {
                    matchProfilePicture.setImageResource(matchDetails.userProfilePicture)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(matchList[position])
    }

    override fun getItemCount(): Int {
        return matchList.size
    }

}
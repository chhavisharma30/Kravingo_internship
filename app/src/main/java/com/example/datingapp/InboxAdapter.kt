package com.example.datingapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class InboxAdapter(private val matchList : ArrayList<MatchModel>, private val context : Context):
    RecyclerView.Adapter<InboxAdapter.ViewHolder>() {

        inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
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
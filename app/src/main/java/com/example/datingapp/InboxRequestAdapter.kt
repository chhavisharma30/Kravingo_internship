package com.example.datingapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InboxRequestAdapter(private val requestList : ArrayList<MatchModel>, private val context : Context) :
    RecyclerView.Adapter<InboxRequestAdapter.ViewHolder>() {

        inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
            private val requestName: TextView = itemView.findViewById(R.id.requestNameTextView)
            private val requestProfilePicture : ImageView = itemView.findViewById(R.id.requestProfileImage)

            fun bindData(requestDetails : MatchModel) {
                requestName.text = requestDetails.userName
                if (requestDetails.userProfilePicture != null) {
                    requestProfilePicture.setImageResource(requestDetails.userProfilePicture)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_profile, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(requestList[position])
    }

    override fun getItemCount(): Int {
        return requestList.size
    }

}
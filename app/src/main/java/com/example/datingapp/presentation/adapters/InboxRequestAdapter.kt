package com.example.datingapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.datingapp.R
import com.example.datingapp.domain.models.InboxMatchDetails

class InboxRequestAdapter() :
    RecyclerView.Adapter<InboxRequestAdapter.ViewHolder>() {

    private var requestList =  listOf<InboxMatchDetails>()

    //TODO : Implement this ViewHolder separately when data can be pulled from Match Section/API
        inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
            private val requestName: TextView = itemView.findViewById(R.id.requestNameTextView)
            private val requestProfilePicture : ImageView = itemView.findViewById(R.id.requestProfileImage)

            fun bindData(requestDetails : InboxMatchDetails) {
                requestName.text = requestDetails.userName

                // TODO: use Glide to set image from URL later
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

    fun setData(requests : List<InboxMatchDetails>) {
        this.requestList = requests
        notifyDataSetChanged()
    }


}
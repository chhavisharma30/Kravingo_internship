package com.example.datingapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.datingapp.R
import com.example.datingapp.domain.models.InboxMatchDetails
import com.example.datingapp.presentation.ui.listeners.InboxViewListener
import com.example.datingapp.presentation.ui.viewholders.InboxViewHolder

class InboxAdapter(private val listener : InboxViewListener):
    RecyclerView.Adapter<InboxViewHolder>() {

    private var matchList = listOf<InboxMatchDetails>()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): InboxViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return InboxViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: InboxViewHolder, position: Int) {
        holder.bindData(matchList[position])
        holder.bindListeners(listener, matchList[position])
    }

    override fun getItemCount(): Int {
        return matchList.size
    }

    fun setData(matches : List<InboxMatchDetails>) {
        this.matchList = matches
        notifyDataSetChanged()
    }


}
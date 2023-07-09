package com.example.datingapp.presentation.ui.viewholders

import android.view.View
import android.widget.TextView
import com.example.datingapp.R
import com.example.datingapp.domain.models.DateSignifier
import com.example.datingapp.utils.extractDayFromDate


class DateSignifierView(itemView: View) : ChatViewHolder(itemView) {

    private val dateSignifier : TextView = itemView.findViewById(R.id.msg_date_signifier)

    fun bindData(date : DateSignifier) {
        dateSignifier.text = extractDayFromDate(date.dateStamp)
    }

}
package com.example.datingapp.data.local.data_src

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.datingapp.domain.models.InboxMatchDetails

@Dao
interface MatchDAO {

    @Upsert
    suspend fun addNewMatch(match: Match)

    @Query("DELETE FROM match_data WHERE userID = :matchID")
    suspend fun removeMatch(matchID: String)

    // This query is to get all matches matches which are available to be chatted with, along with the latest message sent in
    // the conversation, its time stamp and whether it is read or not. The message content and timestamp can be null if there are no
    // messages between the two users.
    @Query(
        """SELECT md.userID, md.userName, md.userProfilePicture, 
       COALESCE(m.messageID, 'null') AS messageID,
       COALESCE(m.senderID, 'null') AS senderID,
       COALESCE(m.receiverID, 'null') AS receiverID,
       COALESCE(m.messageContent, 'null') AS messageContent,
       COALESCE(m.isRead, 0) AS isRead,
       COALESCE(m.sentTimeStamp, 'null') AS sentTimeStamp
        FROM match_data AS md   
        LEFT JOIN (
    SELECT senderID, receiverID, messageID, messageContent, sentTimeStamp, isRead
    FROM messages_table AS m
    WHERE sentTimeStamp = (
        SELECT MAX(sentTimeStamp)
        FROM messages_table
        WHERE (senderID = m.senderID AND receiverID = m.receiverID)
            OR (senderID = m.receiverID AND receiverID = m.senderID)
    )ORDER BY sentTimeStamp DESC) AS m ON (md.userID = m.senderID OR md.userID = m.receiverID);
""")
    fun getInboxMatches(): LiveData<List<InboxMatchDetails>>

}

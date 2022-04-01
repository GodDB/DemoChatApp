package com.example.data.repository

import com.example.data.datasource.MessageDataSource
import com.example.data.datasource.SnapShotState
import com.example.domain.domain.Message
import com.example.domain.domain.Room
import com.example.domain.domain.User
import com.example.domain.repository.ChatRepository
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class ChatRepositoryImpl @Inject constructor(
    private val messageDataSource: MessageDataSource
) : ChatRepository {

    override fun getChatFlow(fromUser: User, toUser: User): Flow<List<Message>> {
        return messageDataSource.getChatFlow(fromUser.id, toUser.id)
            .filterIsInstance<SnapShotState.Success>()
            .map { it.snapshot.toMessageList() }
    }

    override fun getChatListFlow(fromUser: User): Flow<List<Room>> {
        TODO("Not yet implemented")
    }

    override suspend fun sendMessage(
        fromUser: User,
        toUser: User,
        message: String
    ): Boolean {
        return messageDataSource.sendMessage(fromUser.id, toUser.id, message)
    }

    override fun refresh() {
        TODO("Not yet implemented")
    }
}

private fun QuerySnapshot.toMessageList(): List<Message> {
    return this.documents.map {
        it.toMessage()
    }
}

private fun DocumentSnapshot.toMessage(): Message =
    Message(
        fromUser = get("fromUser") as String,
        message = get("message") as String
    )


private fun QuerySnapshot.toRoomList(): List<Room> =
    this.documents.map {
        it.toRoom()
    }


private fun DocumentSnapshot.toRoom(): Room =
    Room(
        members = listOf(),
        latestMessage = "",
        name = ""
    )


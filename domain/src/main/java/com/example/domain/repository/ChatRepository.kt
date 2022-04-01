package com.example.domain.repository

import com.example.domain.domain.Message
import com.example.domain.domain.Room
import com.example.domain.domain.User
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    fun getChatFlow(fromUser: User, toUser: User): Flow<List<Message>>

    fun getChatListFlow(fromUser: User): Flow<List<Room>>

    suspend fun sendMessage(fromUser: User, toUser: User, message: String): Boolean

    fun refresh()

}
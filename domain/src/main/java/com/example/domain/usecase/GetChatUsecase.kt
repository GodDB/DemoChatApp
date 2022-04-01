package com.example.domain.usecase

import com.example.domain.domain.Message
import com.example.domain.domain.User
import com.example.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetChatUsecase {

    operator fun invoke(fromUser: User, toUser: User): Flow<List<Message>>
}

class GetChatUsecaseImpl @Inject constructor(
    private val chatRepository: ChatRepository
) : GetChatUsecase {

    override fun invoke(fromUser : User, toUser: User): Flow<List<Message>> {
        return chatRepository.getChatFlow(fromUser, toUser)
    }


}
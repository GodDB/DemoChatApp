package com.example.domain.usecase

import com.example.domain.domain.Room
import com.example.domain.domain.User
import com.example.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface GetChatListUsecase {
    operator fun invoke(fromUser: User): Flow<List<Room>>
}

class GetChatListUsecaseImpl @Inject constructor(
    private val chatRepository: ChatRepository
) : GetChatListUsecase {

    override fun invoke(fromUser: User): Flow<List<Room>> {
        return chatRepository.getChatListFlow(fromUser)
    }
}
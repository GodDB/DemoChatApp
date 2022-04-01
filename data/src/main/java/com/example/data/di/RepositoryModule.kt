package com.example.data.di

import com.example.data.repository.ChatRepositoryImpl
import com.example.domain.repository.ChatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindChatRepository(chatRepositoryImpl: ChatRepositoryImpl) : ChatRepository
}
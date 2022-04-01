package com.example.feature.di

import com.example.domain.usecase.GetChatListUsecase
import com.example.domain.usecase.GetChatListUsecaseImpl
import com.example.domain.usecase.GetChatUsecase
import com.example.domain.usecase.GetChatUsecaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface UsecaseModule {

    @Binds
    fun bindGetChatListUsecase(getChatListUsecaseImpl: GetChatListUsecaseImpl) : GetChatListUsecase

    @Binds
    fun bindGetChatUsecase(getChatUsecaseImpl: GetChatUsecaseImpl) : GetChatUsecase

}
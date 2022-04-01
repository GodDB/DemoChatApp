package com.example.data.di

import com.example.data.datasource.MessageDataSource
import com.example.data.datasource.MessageDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DatasourceModule {

    @Binds
    fun bindsMessageDataSource(messageDataSourceImpl: MessageDataSourceImpl): MessageDataSource
}
package com.example.domain.repository

interface MessageRepository {

    fun getMessageFlow()

    fun sendMessage()

    fun refresh()

}
package com.example.domain.domain

data class Room(
    val members : List<User>,
    val latestMessage: String,
    val name: String
)
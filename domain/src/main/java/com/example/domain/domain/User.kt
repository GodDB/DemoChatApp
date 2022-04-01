package com.example.domain.domain

import android.net.Uri

data class User(
    val photoUrl: Uri,
    val name: String,
    val id: String
)
package com.example.data.datasource

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

import javax.inject.Inject

internal interface MessageDataSource {

    fun getMessageFlow() : Flow<MessageState>

    suspend fun sendMessage()
}

internal class MessageDataSourceImpl @Inject constructor(
    private val realtimeDatabase : DatabaseReference
): MessageDataSource {

    override fun getMessageFlow(): Flow<MessageState> {
        return callbackFlow<MessageState> {
            val valueEventListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    trySendBlocking(MessageState.Success(snapshot))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySendBlocking(MessageState.Error(error))
                }
            }
            realtimeDatabase.addValueEventListener(valueEventListener)
            awaitClose {
                realtimeDatabase.removeEventListener(valueEventListener)
            }
        }
    }

    override suspend fun sendMessage() {
        TODO("Not yet implemented")
    }
}

internal sealed class MessageState {
    data class Success(val snapshot: DataSnapshot) : MessageState()
    data class Error(val error : DatabaseError) : MessageState()
}


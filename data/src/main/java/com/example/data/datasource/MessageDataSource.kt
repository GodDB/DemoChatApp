package com.example.data.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

internal interface MessageDataSource {

    fun getChatFlow(fromUserID: String, toUserId: String): Flow<SnapShotState>

    fun getChatListFlow(fromUserID: String): Flow<SnapShotState>

    suspend fun sendMessage(fromUserID: String, toUserId: String, message: String): Boolean
}

internal class MessageDataSourceImpl @Inject constructor(): MessageDataSource {

    private val db: FirebaseFirestore = Firebase.firestore

    override fun getChatFlow(fromUserID: String, toUserId: String): Flow<SnapShotState> {
        return callbackFlow<SnapShotState> {
            val chatRoomKey = getChatRoomKey(fromUserID, toUserId)
            val docRef = db.collection(fromUserID).document(chatRoomKey).collection(MESSAGE)
            val listener =
                docRef.addSnapshotListener { snapShot: QuerySnapshot?, e: FirebaseFirestoreException? ->
                    if (e != null) {
                        // handle error
                        trySendBlocking(SnapShotState.Error(e))
                    } else {
                        // handle success
                        snapShot?.let {
                            trySendBlocking(SnapShotState.Success(it))
                        }
                    }
                }

            awaitClose {
                listener.remove()
            }
        }
    }

    override fun getChatListFlow(fromUserID: String): Flow<SnapShotState> {
        return callbackFlow {
            val docRef = db.collection(fromUserID)
            val listener =
                docRef.addSnapshotListener { snapShot: QuerySnapshot?, e: FirebaseFirestoreException? ->
                    if (e != null) {
                        // handle error
                        trySendBlocking(SnapShotState.Error(e))
                    } else {
                        // handle success
                        snapShot?.let {
                            trySendBlocking(SnapShotState.Success(it))
                        }
                    }
                }

            awaitClose {
                listener.remove()
            }
        }
    }

    override suspend fun sendMessage(
        fromUserID: String,
        toUserId: String,
        message: String
    ): Boolean {
        val completeSendMessageToFromUser = sendMessageToUser(fromUserID, toUserId, message)
        val completeSendMessageToToUser = sendMessageToUser(toUserId, fromUserID, message)
        return completeSendMessageToFromUser && completeSendMessageToToUser
    }

    private suspend fun sendMessageToUser(
        fromUserID: String,
        toUserId: String,
        message: String
    ): Boolean {
        return suspendCancellableCoroutine<Boolean> { continuation ->
            db.collection(fromUserID).document(toUserId).collection(MESSAGE)
                .add(message)
                .addOnCompleteListener {
                    continuation.resume(true)
                }
                .addOnFailureListener {
                    continuation.resume(false)
                }
                .addOnCanceledListener {
                    continuation.resume(false)
                }
        }
    }

    private fun getChatRoomKey(vararg userIds: String): String {
        return userIds.sumOf {
            it.hashCode().toLong()
        }.toString()
    }

    companion object {
        private const val MESSAGE = "message"
    }
}

internal sealed class SnapShotState {
    data class Success(val snapshot: QuerySnapshot) : SnapShotState()
    data class Error(val error: FirebaseFirestoreException) : SnapShotState()
}


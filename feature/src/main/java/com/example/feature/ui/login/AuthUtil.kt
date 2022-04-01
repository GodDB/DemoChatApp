package com.example.feature.ui.login

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.example.domain.domain.User
import com.example.feature.BuildConfig
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GetTokenResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object AuthUtil {
    fun createGoogleSignIn(activity: Activity): SignIn {
        return GoogleSignIn(activity)
    }

    fun isSignIn(): Boolean = Firebase.auth.currentUser != null

    @Throws
    fun getUser(): User {
        val firebaseUser: FirebaseUser = checkNotNull(Firebase.auth.currentUser) { "require user sign in " }

        return with(firebaseUser) {
            User(
                photoUrl = photoUrl ?: Uri.EMPTY,
                name = this.displayName ?: this.uid,
                id = this.uid
            )
        }
    }

    @kotlin.jvm.Throws
    suspend fun getIdToken(): String {
        val firebaseUser: FirebaseUser = checkNotNull(Firebase.auth.currentUser) { "require user sign in " }
        return suspendCoroutine { continuation ->
            val listener = OnCompleteListener<GetTokenResult> { result ->
                if (result.isSuccessful) {
                    continuation.resume(result.result.token ?: "")
                } else {
                    continuation.resumeWithException(java.lang.Exception("fail get idToken"))
                }
            }
            firebaseUser.getIdToken(false)
                .addOnCompleteListener(listener)
        }
    }


    fun signOut() {
        Firebase.auth.signOut()
    }


}

interface SignIn {
    val activity: Activity

    fun signIn()

    suspend fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

    companion object {
        const val REQ_CODE = 12345
    }
}


class GoogleSignIn(override val activity: Activity) : SignIn {

    private lateinit var oneTabClient: SignInClient

    override fun signIn() {
        oneTabClient = Identity.getSignInClient(activity)
        oneTabClient.beginSignIn(getSignInRequest())
            .addOnSuccessListener { result ->
                activity.startIntentSenderForResult(result.pendingIntent.intentSender, SignIn.REQ_CODE, null, 0, 0, 0)
            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }

    @kotlin.jvm.Throws
    override suspend fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode != SignIn.REQ_CODE) return

        val credential = oneTabClient.getSignInCredentialFromIntent(data)
        val idToken = credential.googleIdToken ?: throw Exception("sign in error")

        val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)

        suspendCancellableCoroutine<Unit> { continuation ->
            Firebase.auth.signInWithCredential(firebaseCredential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.e("godgod", "complete sign in")
                        continuation.resume(Unit)
                    } else {
                        continuation.resumeWithException(Exception("sign in error"))
                    }
                }
        }
    }

    private fun getSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(BuildConfig.oauth_web_key)
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            ).build()
    }
}
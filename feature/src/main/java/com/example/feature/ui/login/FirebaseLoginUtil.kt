package com.example.feature.ui.login

import android.app.Activity
import android.util.Log
import com.example.feature.BuildConfig
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity

object FirebaseLoginUtil {
    const val REQ_CODE = 12345

    fun signInGoogle(activity : Activity) {
        Identity.getSignInClient(activity).beginSignIn(getGoogleSignInRequest())
            .addOnSuccessListener { result ->
                Log.e("godgod", "success")
               activity.startIntentSenderForResult(result.pendingIntent.intentSender, REQ_CODE, null, 0, 0, 0)
            }
            .addOnFailureListener {
                Log.e("godgod", "fail")
                it.printStackTrace()
            }
    }

    private fun getGoogleSignInRequest() : BeginSignInRequest {
        return BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(BuildConfig.oauth_web_key)
                    .setFilterByAuthorizedAccounts(true)
                    .build()
            ).build()
    }

}
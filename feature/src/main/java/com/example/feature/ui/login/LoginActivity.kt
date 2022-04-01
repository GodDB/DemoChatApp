package com.example.feature.ui.login

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.feature.R
import com.example.feature.base.BaseActivity
import com.example.feature.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.Auth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private lateinit var signIn: SignIn

    override fun setup() {
        initBinding {
            activity = this@LoginActivity
        }
    }

    fun onClickGoogle(v: View) {
        signIn = AuthUtil.createGoogleSignIn(this).also {
            it.signIn()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        lifecycleScope.launch {
            signIn.onActivityResult(requestCode, resultCode, data)
            setResult(RESULT_OK)
            finish()
        }
    }

    companion object : NoParamFactory {
        override fun getIntent(context: Context): Intent =
            Intent(context, LoginActivity::class.java)
    }

}
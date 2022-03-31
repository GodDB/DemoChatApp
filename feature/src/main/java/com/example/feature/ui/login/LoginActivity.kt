package com.example.feature.ui.login

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import com.example.feature.R
import com.example.feature.base.BaseActivity
import com.example.feature.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    override fun setup() {
        initBinding {
            activity = this@LoginActivity
        }
    }

    fun onClickGoogle(v: View) {
        FirebaseLoginUtil.signInGoogle(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            FirebaseLoginUtil.REQ_CODE -> {
                Log.e("godgod", "success")
            }
        }
    }

    companion object : NoParamFactory{
        override fun getIntent(context: Context): Intent =
            Intent(context, LoginActivity::class.java)
    }

}
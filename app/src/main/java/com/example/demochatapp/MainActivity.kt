package com.example.demochatapp

import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.commit
import com.example.demochatapp.databinding.ActivityMainBinding
import com.example.feature.base.BaseActivity
import com.example.feature.ui.chat_list.ChatListFragment
import com.example.feature.ui.login.AuthUtil
import com.example.feature.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val loginActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            showChatList()
        }
    }

    override fun setup() {
        if (AuthUtil.isSignIn()) showChatList()
        else loginActivityLauncher.launch(LoginActivity.getIntent(this))
    }

    private fun showChatList() {
        supportFragmentManager.commit {
            replace(binding.flFragmentContainer.id, ChatListFragment.newInstance())
        }
    }
}
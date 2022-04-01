package com.example.feature.ui.chat_list

import androidx.fragment.app.viewModels
import com.example.feature.R
import com.example.feature.base.BaseFragment
import com.example.feature.databinding.FragmentChatlistBinding
import com.example.feature.util.CommonAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatListFragment : BaseFragment<FragmentChatlistBinding>(R.layout.fragment_chatlist) {

    private val viewModel by viewModels<ChatListViewModel>()

    override fun setup() {
        initBinding {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            fragment = this@ChatListFragment
        }
    }

    override fun observeViewModel() {

    }

    companion object : NoParamFactory {
        override fun newInstance(): BaseFragment<*> {
            return ChatListFragment()
        }

    }

}
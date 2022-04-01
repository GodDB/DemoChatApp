package com.example.feature.ui.chat_list

import com.example.domain.usecase.GetChatListUsecase
import com.example.domain.usecase.GetChatUsecase
import com.example.feature.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
internal class ChatListViewModel @Inject constructor(
    private val getChatListUsecase: GetChatListUsecase
) : BaseViewModel() {

}
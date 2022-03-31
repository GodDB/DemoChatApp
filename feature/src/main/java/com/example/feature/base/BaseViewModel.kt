package com.example.feature.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

internal abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    //단일성 이벤트 처리를 위한 sharedFlow
    private val _sideEffect: MutableSharedFlow<BaseSideEffect> = MutableSharedFlow(extraBufferCapacity = 10)
    val sideEffect: SharedFlow<BaseSideEffect> = _sideEffect.asSharedFlow()

    protected fun setSideEffect(baseSideEffect: BaseSideEffect) {
        _sideEffect.tryEmit(baseSideEffect)
    }

}
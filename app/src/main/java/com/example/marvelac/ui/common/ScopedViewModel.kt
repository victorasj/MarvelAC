package com.example.marvelac.ui.common

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import ui.common.Scope

abstract class ScopedViewModel : ViewModel(), Scope by Scope.Impl() {

    init {
        initScope()
    }

    @CallSuper
    override fun onCleared() {
        cancelScope()
        super.onCleared()
    }
}
package org.sopt.sample.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import org.sopt.sample.ui.auth.EditTextUiState

fun <T> MediatorLiveData<T>.addSources(sources: List<LiveData<EditTextUiState>>, lambda: () -> T) {
    sources.forEach {
        this.addSource(it) { value = lambda() }
    }
}

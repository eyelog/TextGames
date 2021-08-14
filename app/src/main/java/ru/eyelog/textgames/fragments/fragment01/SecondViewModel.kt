package ru.eyelog.textgames.fragments.fragment01

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.OnLifecycleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.eyelog.textgames.fragments.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : BaseViewModel(context), LifecycleObserver {

    val textListSourceLiveData: LiveData<List<String>> get() = _textListSourceLiveData
    private val _textListSourceLiveData = MediatorLiveData<List<String>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onResume() {
        _textListSourceLiveData.postValue(listOf(readFromFile("texts/raven.txt")))
    }
}

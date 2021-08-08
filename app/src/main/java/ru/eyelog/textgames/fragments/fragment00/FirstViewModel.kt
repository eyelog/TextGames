package ru.eyelog.textgames.fragments.fragment00

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.eyelog.textgames.fragments.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : BaseViewModel(context), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        readFromFile("texts/novel.txt")
    }
}

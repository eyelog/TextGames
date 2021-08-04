package ru.eyelog.textgames.fragments.fragment01

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.eyelog.textgames.R
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel(), LifecycleObserver {

    val sampleLiveData: LiveData<String> get() = _sampleLiveData
    private val _sampleLiveData = MediatorLiveData<String>()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        _sampleLiveData.postValue(context.resources.getString(R.string.second_item))
    }
}

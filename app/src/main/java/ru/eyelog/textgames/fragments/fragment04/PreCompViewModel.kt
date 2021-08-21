package ru.eyelog.textgames.fragments.fragment04

import android.content.Context
import androidx.lifecycle.LifecycleObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.eyelog.textgames.fragments.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class PreCompViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : BaseViewModel(context), LifecycleObserver {

}
package ru.eyelog.textgames.fragments.fragment05

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.eyelog.textgames.fragments.base.BaseViewModel
import ru.eyelog.textgames.models.DataTextList
import javax.inject.Inject

@HiltViewModel
class PagerViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : BaseViewModel(context), LifecycleObserver {

    private val textList = mutableListOf<String>()
    private var mainText = ""

    val textListSourceLiveData: LiveData<DataTextList> get() = _textListSourceLiveData
    private val _textListSourceLiveData = MediatorLiveData<DataTextList>()

    private val observer: Observer<String> = Observer<String>(::refreshTextSource)

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onResume() {
        textSourceLiveData.observeForever(observer)
        readFromFile("texts/raven.txt")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onStop(){
        textSourceLiveData.removeObserver(observer)
        textList.clear()
    }

    fun refreshTextSource(textSource: String? = null){
        textSource?.let { mainText = textSource }
        textList.clear()
        _textListSourceLiveData.postValue(DataTextList(listOf(mainText), true))
    }

    fun setCurrentLastIndex(lastCharIndex: Int) {
        val maxTextLength = mainText.length
        var indexCounter = 0
        do {
            if (indexCounter + lastCharIndex <= maxTextLength){
                textList.add(mainText.substring(indexCounter, indexCounter + lastCharIndex))
            }
            indexCounter += lastCharIndex
        } while (indexCounter < maxTextLength)
        _textListSourceLiveData.postValue(DataTextList(textList, false))
    }
}

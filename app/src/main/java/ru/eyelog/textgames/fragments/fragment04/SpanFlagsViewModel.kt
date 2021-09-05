package ru.eyelog.textgames.fragments.fragment04

import android.content.Context
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
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
class SpanFlagsViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : BaseViewModel(context), LifecycleObserver {

    val flagsLiveData: LiveData<List<FlagsPair>> get() = _flagsLiveData
    private val _flagsLiveData = MediatorLiveData<List<FlagsPair>>()

    private var baseString = "Егор+сосиска=Егор"
    private val minTextLength = 17
    private val maxTextLength = 27

    private val currentList = getDataList()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        _flagsLiveData.postValue(currentList)
    }

    private fun getDataList(): MutableList<FlagsPair> {

        val spanComposing = SpannableStringBuilder(baseString).apply {
            setSpan(
                ForegroundColorSpan(Color.RED),
                5,
                11,
                Spanned.SPAN_COMPOSING
            )
        }
        val spanExcExc = SpannableStringBuilder(baseString).apply {
            setSpan(
                ForegroundColorSpan(Color.RED),
                5,
                12,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        val spanExcInc = SpannableStringBuilder(baseString).apply {
            setSpan(
                ForegroundColorSpan(Color.RED),
                5,
                12,
                Spanned.SPAN_EXCLUSIVE_INCLUSIVE
            )
        }
        val spanIncExc = SpannableStringBuilder(baseString).apply {
            setSpan(
                ForegroundColorSpan(Color.RED),
                5,
                12,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
            )
        }
        val spanIncInc = SpannableStringBuilder(baseString).apply {
            setSpan(
                ForegroundColorSpan(Color.RED),
                5,
                12,
                Spanned.SPAN_INCLUSIVE_INCLUSIVE
            )
        }
        val spanIntemdt = SpannableStringBuilder(baseString).apply {
            setSpan(
                ForegroundColorSpan(Color.RED),
                5,
                12,
                Spanned.SPAN_INTERMEDIATE
            )
        }
        val spanMarkMark = SpannableStringBuilder(baseString).apply {
            setSpan(
                ForegroundColorSpan(Color.RED),
                5,
                12,
                Spanned.SPAN_MARK_MARK
            )
        }

        return mutableListOf(
//            FlagsPair("SPAN_COMPOSING", spanComposing),
            FlagsPair("SPAN_EXCLUSIVE_EXCLUSIVE", spanExcExc),
            FlagsPair("SPAN_EXCLUSIVE_INCLUSIVE", spanExcInc),
            FlagsPair("SPAN_INCLUSIVE_EXCLUSIVE", spanIncExc),
            FlagsPair("SPAN_INCLUSIVE_INCLUSIVE", spanIncInc),
//            FlagsPair("SPAN_INTERMEDIATE", spanIntemdt),
//            FlagsPair("SPAN_MARK_MARK", spanMarkMark),
//            FlagsPair("SPAN_MARK_POINT", spanComposing),
//            FlagsPair("SPAN_PARAGRAPH", spanComposing),
//            FlagsPair("SPAN_POINT_MARK", spanComposing),
//            FlagsPair("SPAN_POINT_MARK_MASK", spanComposing),
//            FlagsPair("SPAN_POINT_POINT", spanComposing),
//            FlagsPair("SPAN_PRIORITY", spanComposing),
//            FlagsPair("SPAN_PRIORITY_SHIFT", spanComposing),
//            FlagsPair("SPAN_USER", spanComposing),
//            FlagsPair("SPAN_USER_SHIFT", spanComposing)
        )
    }

    fun togglePlus() {
        currentList.forEach {
            if (it.content.length < maxTextLength){
                it.content.insert(5, "+")
                it.content.insert(it.content.length - 5, "=")
            }
        }
        _flagsLiveData.postValue(currentList)
    }

    fun toggleMinus() {
        currentList.forEach {
            if (it.content.length > minTextLength) {
                it.content.delete(5, 6)
                it.content.delete(it.content.length - 6, it.content.length - 5)
            }
        }
        _flagsLiveData.postValue(currentList)
    }
}
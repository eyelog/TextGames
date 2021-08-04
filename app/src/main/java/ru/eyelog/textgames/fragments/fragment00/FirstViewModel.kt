package ru.eyelog.textgames.fragments.fragment00

import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel(), LifecycleObserver {

    val sampleLiveData: LiveData<String> get() = _sampleLiveData
    private val _sampleLiveData = MediatorLiveData<String>()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        _sampleLiveData.postValue(readFromFile())
    }

    private fun readFromFile(): String {
        var outData = ""
        try {
            val inputStream = context.assets.open("novel.txt")
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            var receiveString: String? = ""
            val stringBuilder = StringBuilder()
            while (bufferedReader.readLine().also { receiveString = it } != null) {
                stringBuilder.append("\n").append(receiveString)
            }
            inputStream.close()
            outData = stringBuilder.toString()
        } catch (e: FileNotFoundException) {
            Log.e("Logcat", "File not found: $e")
        } catch (e: IOException) {
            Log.e("Logcat", "Can not read file: $e")
        }
        return outData
    }
}

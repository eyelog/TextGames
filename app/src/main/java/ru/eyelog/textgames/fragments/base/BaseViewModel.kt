package ru.eyelog.textgames.fragments.base

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import ru.eyelog.textgames.R
import ru.eyelog.textgames.models.DataType
import ru.eyelog.textgames.models.TextFormatModel
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader

abstract class BaseViewModel(private val context: Context) : ViewModel() {

    val colorLiveData: LiveData<Int> get() = _colorLiveData
    private val _colorLiveData = MediatorLiveData<Int>()

    val fontLiveData: LiveData<Int> get() = _fontLiveData
    private val _fontLiveData = MediatorLiveData<Int>()

    val sizeLiveData: LiveData<Float> get() = _sizeLiveData
    private val _sizeLiveData = MediatorLiveData<Float>()

    internal fun setTextFormat(data: TextFormatModel) {
        when (data.dataType) {
            DataType.TEXT_SOURCE -> {
                readFromFile(data.payload)
            }
            DataType.COLOR -> {
                val foundIndex = context.resources.getStringArray(R.array.spinner_colors)
                    .indexOf(
                        context.resources.getStringArray(R.array.spinner_colors).firstOrNull { it == data.payload }
                    )
                _colorLiveData.postValue(context.resources.getIntArray(R.array.colors_list)[foundIndex])
            }
            DataType.FONT -> {
                _fontLiveData.postValue(
                    context.resources.getIdentifier(data.payload, "font", context.packageName)
                )
            }
            DataType.SIZE -> {
                _sizeLiveData.postValue(data.payload.toFloat())
            }
        }
    }

    internal fun readFromFile(fileName: String) : String {
        var outData = ""
        try {
            val inputStream = context.assets.open(fileName)
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
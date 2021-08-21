package ru.eyelog.textgames.exts

import android.util.Log
import android.widget.TextView

fun TextView.getLastVisibleCharacter(): Int {
    val layoutBottom = height + scrollY - totalPaddingTop - totalPaddingBottom
    Log.i("Logcat", "layoutBottom $layoutBottom")
    val bottomLine = layout.getLineForVertical(layoutBottom)
    Log.i("Logcat", "bottomLine $bottomLine")
    Log.i("Logcat", "layout.getLineBottom(bottomLine) ${layout.getLineBottom(bottomLine)}")
    val lastWhollyVisibleLineNumber =
        if (layout.getLineBottom(bottomLine) > layoutBottom) bottomLine - 1
        else bottomLine
    Log.i("Logcat", "layout.getLineVisibleEnd(lastWhollyVisibleLineNumber) ${layout.getLineVisibleEnd(lastWhollyVisibleLineNumber)}")
    return layout.getLineVisibleEnd(lastWhollyVisibleLineNumber)
}
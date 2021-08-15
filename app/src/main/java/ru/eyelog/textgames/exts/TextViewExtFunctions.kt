package ru.eyelog.textgames.exts

import android.widget.TextView

fun TextView.getLastVisibleCharacter(): Int {
    val layoutBottom = height + scrollY - totalPaddingTop - totalPaddingBottom
    val bottomLine = layout.getLineForVertical(layoutBottom)
    val lastWhollyVisibleLineNumber =
        if (layout.getLineBottom(bottomLine) > layoutBottom) bottomLine - 1
        else bottomLine
    return layout.getLineVisibleEnd(lastWhollyVisibleLineNumber)
}
package ru.eyelog.textgames.dialog

import android.R
import android.app.Dialog
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import ru.eyelog.textgames.timehold.context

class ChooseDialog (): AlertDialog(context) {

//    private fun onCreateDialog(id: Int): Dialog? {
//        val adb = Builder(context)
//        adb.setTitle("Custom dialog")
//        // создаем view из dialog.xml
//        view = layoutInflater
//            .inflate(R.layout.dialog, null) as LinearLayout
//        // устанавливаем ее, как содержимое тела диалога
//        adb.setView(view)
//        // находим TexView для отображения кол-ва
//        tvCount = view.findViewById(R.id.tvCount) as TextView
//        return adb.create()
//    }
//
//    protected fun onPrepareDialog(id: Int, dialog: Dialog) {
//        super.onPrepareDialog(id, dialog)
//        if (id == DIALOG) {
//            // Находим TextView для отображения времени и показываем текущее
//            // время
//            val tvTime = dialog.getWindow().findViewById(
//                R.id.tvTime
//            ) as TextView
//            tvTime.setText(sdf.format(Date(System.currentTimeMillis())))
//            // если была нажата кнопка Добавить
//            if (btn === R.id.btnAdd) {
//                // создаем новое TextView, добавляем в диалог, указываем текст
//                val tv = TextView(this)
//                view.addView(
//                    tv, LayoutParams(
//                        LayoutParams.MATCH_PARENT,
//                        LayoutParams.WRAP_CONTENT
//                    )
//                )
//                tv.text = "TextView " + (textViews.size() + 1)
//                // добавляем новое TextView в коллекцию
//                textViews.add(tv)
//                // иначе
//            } else {
//                // если коллекция созданных TextView непуста
//                if (textViews.size() > 0) {
//                    // находим в коллекции последний TextView
//                    val tv: TextView = textViews.get(textViews.size() - 1)
//                    // удаляем из диалога
//                    view.removeView(tv)
//                    // удаляем из коллекции
//                    textViews.remove(tv)
//                }
//            }
//            // обновляем счетчик
//            tvCount.setText("Кол-во TextView = " + textViews.size())
//        }
//    }
}
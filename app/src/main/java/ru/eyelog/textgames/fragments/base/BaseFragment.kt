package ru.eyelog.textgames.fragments.base

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import ru.eyelog.textgames.R
import ru.eyelog.textgames.activity.MainActivity
import ru.eyelog.textgames.fragments.DataMaster
import ru.eyelog.textgames.models.DataType
import ru.eyelog.textgames.models.TextFormatModel

abstract class BaseFragment : Fragment(), DataMaster {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).onPopupMenuListener = {
            showAlertDialog(it)
        }
    }

    private fun showAlertDialog(itemId: Int) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        val customLayout: View = layoutInflater.inflate(R.layout.choose_dialog, null)
        builder.setView(customLayout)
        val dialog: AlertDialog = builder.create()
        dialog.show()
        val tvAlertTitle = customLayout.findViewById<TextView>(R.id.tvTitle)
        val spinner = customLayout.findViewById<Spinner>(R.id.spinner)
        val alertButton = customLayout.findViewById<Button>(R.id.btApply)
        when (itemId) {
            R.id.choose_text -> {
                tvAlertTitle.text = getString(R.string.choose_text)
                spinner.adapter = setSpinnerArray(R.array.spinner_texts)
                alertButton.setOnClickListener {
                    setTextFormatData(
                        TextFormatModel(
                            DataType.TEXT_SOURCE,
                            spinner.selectedItem.toString()
                        )
                    )
                    dialog.cancel()
                }
            }
            R.id.change_color -> {
                tvAlertTitle.text = getString(R.string.change_color)
                spinner.adapter = setSpinnerArray(R.array.spinner_colors)
                alertButton.setOnClickListener {
                    setTextFormatData(
                        TextFormatModel(
                            DataType.COLOR,
                            spinner.selectedItem.toString()
                        )
                    )
                    dialog.cancel()
                }
            }
            R.id.change_font -> {
                tvAlertTitle.text = getString(R.string.change_font)
                spinner.adapter = setSpinnerArray(R.array.spinner_fonts)
                alertButton.setOnClickListener {
                    setTextFormatData(
                        TextFormatModel(
                            DataType.FONT,
                            spinner.selectedItem.toString()
                        )
                    )
                    dialog.cancel()
                }
            }
            R.id.change_size -> {
                tvAlertTitle.text = getString(R.string.change_size)
                spinner.adapter = setSpinnerArray(R.array.spinner_size)
                alertButton.setOnClickListener {
                    setTextFormatData(
                        TextFormatModel(
                            DataType.SIZE,
                            spinner.selectedItem.toString()
                        )
                    )
                    dialog.cancel()
                }
            }
        }
    }

    private fun setSpinnerArray(sourceArray: Int): ArrayAdapter<*> {
        val adapter: ArrayAdapter<*> =
            ArrayAdapter.createFromResource(
                requireContext(),
                sourceArray,
                android.R.layout.simple_spinner_item
            )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        return adapter
    }

    abstract override fun setTextFormatData(data: TextFormatModel)
}
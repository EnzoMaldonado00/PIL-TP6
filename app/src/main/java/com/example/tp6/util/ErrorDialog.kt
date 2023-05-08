package com.example.tp6.util

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tp6.R

object ErrorDialog {

    fun showErrorDialog(activity: AppCompatActivity) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(R.string.error_dialog_title)
        builder.setMessage(R.string.error_dialog_message)

        builder.setNeutralButton(R.string.error_dialog_button) { _, _ ->
        }
        builder.show()
    }
}

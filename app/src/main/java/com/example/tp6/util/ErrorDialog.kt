package com.example.tp6.util

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tp6.R

object ErrorDialog : Exception() {

    fun showErrorDialog(activity: AppCompatActivity, message: Int) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(R.string.error_dialog_title)
        builder.setMessage(message)

        builder.setNeutralButton(R.string.error_dialog_button) { _, _ ->
        }
        builder.show()
    }
}

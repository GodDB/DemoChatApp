package com.example.feature.util

import android.app.AlertDialog
import android.content.Context
import androidx.annotation.StringRes

internal object DialogUtil {

    fun showDialog(
        context: Context,
        @StringRes msg: Int,
        positiveClick: Pair<Int, () -> Unit>? = null,
        negativeClick: Pair<Int, () -> Unit>? = null
    ) {
        AlertDialog.Builder(context)
            .setTitle(null)
            .setMessage(msg)
            .apply {
                positiveClick?.let {
                    setPositiveButton(it.first) { dialog, which ->
                        it.second.invoke()
                        dialog.dismiss()
                    }
                }
                negativeClick?.let {
                    setNegativeButton(it.first) { dialog, which ->
                        it.second.invoke()
                        dialog.dismiss()
                    }
                }
            }.show()
    }

}
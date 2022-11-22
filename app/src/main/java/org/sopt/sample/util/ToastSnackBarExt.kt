package org.sopt.sample

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun View.defaultSnackbar(stringId: Int) {
    Snackbar.make(this, stringId, Snackbar.LENGTH_SHORT).show()
}

fun View.shortSnackbar(stringId: Int) {
    Snackbar.make(this, stringId, Snackbar.LENGTH_SHORT).apply {
        anchorView = this@shortSnackbar
    }.show()
}

fun View.LongSnackbar(stringId: Int) {
    Snackbar.make(this, stringId, Snackbar.LENGTH_LONG).apply {
        anchorView = this@LongSnackbar
    }.show()
}

fun Context.shortToast(stringId: Int) {
    Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show()
}

fun Context.shortToastString(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.longToast(stringId: Int) {
    Toast.makeText(this, stringId, Toast.LENGTH_LONG).show()
}

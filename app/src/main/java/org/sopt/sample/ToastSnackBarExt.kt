package org.sopt.sample

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun View.shortSnackbar(stringId: Int): Unit {
    Snackbar.make(this, stringId, Snackbar.LENGTH_SHORT).show()
}

fun View.LongSnackbar(stringId: Int): Unit {
    Snackbar.make(this, stringId, Snackbar.LENGTH_LONG).show()
}

fun Context.shortToast(message : String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.longToast(message : String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
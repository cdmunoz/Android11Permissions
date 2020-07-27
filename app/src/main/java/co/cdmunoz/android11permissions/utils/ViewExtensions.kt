package co.cdmunoz.android11permissions.utils

import android.view.View
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

fun View.snackBar(message: String, duration: Int = BaseTransientBottomBar.LENGTH_SHORT) {
    Snackbar.make(this, message, duration).show()
}
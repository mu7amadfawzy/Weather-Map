package com.weather.map.utils.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import com.weather.map.R

fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duration).show()

inline fun Activity.alertDialog(body: AlertDialog.Builder.() -> AlertDialog.Builder): AlertDialog {
    return AlertDialog.Builder(this)
        .body()
        .show()
}
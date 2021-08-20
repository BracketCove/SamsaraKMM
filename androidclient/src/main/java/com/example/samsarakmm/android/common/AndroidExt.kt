package com.example.samsarakmm.android.common

import android.app.Activity
import android.widget.Toast

internal fun Activity.showToast(message: String) =
    Toast.makeText(
        this,
        message,
        Toast.LENGTH_LONG
    ).show()

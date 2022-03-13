package com.example.calculator11.extensions

import android.content.Context
import android.widget.Toast
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.google.android.material.appbar.AppBarLayout

//Toast extension
fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

//Insets extension
fun AppBarLayout.addToolbarInset() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { _, insets ->
        updatePadding(
            top = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
        )
        WindowInsetsCompat.Builder(insets)
            .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.NONE)
            .build()
    }
}


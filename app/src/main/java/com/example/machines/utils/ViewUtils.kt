package com.example.machines.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.machines.databinding.HeaderBinding

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.toast(resourceId: Int) {
    Toast.makeText(this, resourceId, Toast.LENGTH_SHORT).show()
}


fun View.click(block: () -> Unit) {
    this.setOnClickListener {
        block()
    }
}

fun HeaderBinding.drawScreenHeader(
    screenTitle: String,
    fragment: Fragment
) {
    tvScreenName.text = screenTitle
    ivGoBack.click {
        fragment.findNavController().navigateUp()
    }
}
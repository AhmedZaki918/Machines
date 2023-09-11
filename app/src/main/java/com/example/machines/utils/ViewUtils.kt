package com.example.machines.utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.machines.R
import com.example.machines.data.local.Constants.HOURS
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


fun Context.validateHoursOrMinutes(
    editText: EditText,
    time: String
) {
    editText.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (!s.isNullOrEmpty()) {
                // Hours case
                if (time == HOURS && s.toString().toInt() > 23) {
                    editText.text.clear()
                    toast(getString(R.string.invalid_hour_format))
                } else {
                    // Minutes case
                    if (s.toString().toInt() > 59) {
                        editText.text.clear()
                        toast(getString(R.string.invalid_minute_format))
                    }
                }
            }
        }

        override fun afterTextChanged(p0: Editable?) {}
    })
}
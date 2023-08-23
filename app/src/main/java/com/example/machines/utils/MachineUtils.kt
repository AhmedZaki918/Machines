package com.example.machines.utils

import android.content.Context
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.switchmaterial.SwitchMaterial

object MachineUtils {

     fun Context.changeThumbTint(color: Int, view: SwitchMaterial) {
        view.thumbTintList = AppCompatResources
            .getColorStateList(this, color)
    }
}
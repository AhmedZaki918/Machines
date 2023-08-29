package com.example.machines.utils

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import com.example.machines.databinding.HeaderBinding
import com.example.machines.ui.Time
import com.google.android.material.switchmaterial.SwitchMaterial

object MachineUtils {

    fun Context.changeThumbTint(
        color: Int,
        view: SwitchMaterial
    ) {
        view.thumbTintList = AppCompatResources
            .getColorStateList(this, color)
    }


    fun updateNotRunningHours(
        runningMinutes: Int,
        binding: HeaderBinding
    ): Time {
        return if (runningMinutes != 0) {
            val diffInMinutes = 1440 - runningMinutes
            val time = convertMinutesToTime(diffInMinutes.toLong())
            binding.apply {
                tvRhHoursDiff.text = time.hours
                tvRhMinutesDiff.text = formatTime(time.minutes)
            }
            time
        } else Time("", "")
    }
}
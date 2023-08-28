package com.example.machines.utils

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import com.example.machines.data.model.MachineMain
import com.example.machines.databinding.HeaderBinding
import com.google.android.material.switchmaterial.SwitchMaterial

object MachineUtils {

    fun Context.changeThumbTint(
        color: Int,
        view: SwitchMaterial
    ) {
        view.thumbTintList = AppCompatResources
            .getColorStateList(this, color)
    }


    fun updateRhTotal(
        items: List<MachineMain>,
        binding: HeaderBinding
    ) {
        var totalMinutes = 0
        for (x in items.indices) {
            totalMinutes += convertTimeToMinutes(items[x].rh)
        }
        val totalRh = convertMinutesToTime(totalMinutes.toLong())
        binding.apply {
            tvRhHours.text = totalRh.hours
            tvRhMinutes.text = formatTime(totalRh.minutes)
        }
        updateNotRunningHours(totalMinutes, binding)
    }


    private fun updateNotRunningHours(
        runningMinutes: Int,
        binding: HeaderBinding
    ) {
        if (runningMinutes != 0) {
            val diffInMinutes = 1440 - runningMinutes
            val time = convertMinutesToTime(diffInMinutes.toLong())
            binding.apply {
                tvRhHoursDiff.text = time.hours
                tvRhMinutesDiff.text = formatTime(time.minutes)
            }
        }
    }
}
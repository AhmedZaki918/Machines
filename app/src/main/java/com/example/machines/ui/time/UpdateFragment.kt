package com.example.machines.ui.time

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.machines.R
import com.example.machines.data.local.Constants.COLUMN
import com.example.machines.data.local.Constants.RUNNING
import com.example.machines.data.local.Constants.SEVEN_AM
import com.example.machines.data.local.Constants.clayCrusherMachine
import com.example.machines.data.local.Constants.machine
import com.example.machines.data.local.Constants.machineType
import com.example.machines.data.local.Type
import com.example.machines.data.model.ClayCrusherMachine
import com.example.machines.data.model.LimestoneMachine
import com.example.machines.databinding.FragmentUpdateBinding
import com.example.machines.ui.claycrusher.ClayCrusherViewModel
import com.example.machines.ui.limestone.LimestoneViewModel
import com.example.machines.utils.MachineUtils.changeThumbTint
import com.example.machines.utils.click
import com.example.machines.utils.differentInTwoTimes
import com.example.machines.utils.drawScreenHeader
import com.example.machines.utils.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private lateinit var viewModel: LimestoneViewModel
    private lateinit var clayCrusherViewModel: ClayCrusherViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        binding.header.drawScreenHeader(getString(R.string.stop_time), this)
        viewModel = ViewModelProvider(this)[LimestoneViewModel::class.java]
        clayCrusherViewModel = ViewModelProvider(this)[ClayCrusherViewModel::class.java]

        setClickListeners()
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setClickListeners() {
        binding.apply {
            header.apply {
                tvRhTotalLabel.visibility = INVISIBLE
                tvRhHours.visibility = INVISIBLE
                tvRhMinutes.visibility = INVISIBLE
                tvSeparator.visibility = INVISIBLE
                tvRhDiffLabel.visibility = INVISIBLE
                tvRhHoursDiff.visibility = INVISIBLE
                tvRhMinutesDiff.visibility = INVISIBLE
                tvSeparatorDiff.visibility = INVISIBLE
            }
            switchShow.click {
                if (switchShow.isChecked) requireContext().changeThumbTint(R.color.blue, switchShow)
                else requireContext().changeThumbTint(R.color.offWhite, switchShow)
            }
            btnUpdate.click {
                saveTime()
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveTime() {
        val hours = binding.etHours.text.toString().trim()
        val minutes = binding.etMinutes.text.toString().trim()
        val runningStatus = binding.switchShow.isChecked

        if (isTimeEmpty() && !runningStatus) {
            requireContext().toast(R.string.one_field_is_required)
        } else if (!isTimeEmpty() && runningStatus) {
            requireContext().toast(R.string.one_field_is_required)
        } else {
            val stopTime = hours + COLUMN + minutes
            if (runningStatus) updateItem(RUNNING)
            else updateItem(stopTime)
        }
    }


    private fun isTimeEmpty(): Boolean {
        val hours = binding.etHours.text.toString().trim()
        val minutes = binding.etMinutes.text.toString().trim()
        return (hours.isEmpty() || minutes.isEmpty())
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateItem(time: String) {
        val reason = binding.etReasonEndTime.text.toString().trim()
        var endTime = time


        if (endTime == RUNNING) endTime = SEVEN_AM

        if (machineType == Type.LIMESTONE.value) {
            updateLimestone(time, reason, endTime)
        } else if (machineType == Type.CLAY_CRUSHER.value) {
            updateClayCrusher(time, reason, endTime)
        }
        findNavController().navigateUp()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateLimestone(
        time: String,
        reason: String,
        endTime: String
    ) {
        var startTime = machine.startTime
        if (startTime == RUNNING) startTime = SEVEN_AM


        viewModel.updateLimestone(
            LimestoneMachine(
                machine.id,
                startTime,
                time,
                reason,
                differentInTwoTimes(startTime, endTime)
            )
        )
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateClayCrusher(
        time: String,
        reason: String,
        endTime: String
    ) {
        var startTime = clayCrusherMachine.startTime
        if (startTime == RUNNING) startTime = SEVEN_AM

        clayCrusherViewModel.updateClayCrusher(
            ClayCrusherMachine(
                clayCrusherMachine.id,
                startTime,
                time,
                reason,
                differentInTwoTimes(startTime, endTime)
            )
        )
    }
}
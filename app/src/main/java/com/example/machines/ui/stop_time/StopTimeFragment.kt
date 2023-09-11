package com.example.machines.ui.stop_time

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
import com.example.machines.data.local.Constants.HOURS
import com.example.machines.data.local.Constants.MINUTES
import com.example.machines.data.local.Constants.RUNNING
import com.example.machines.data.local.Constants.SEVEN_AM
import com.example.machines.data.local.Constants.cementMill_1
import com.example.machines.data.local.Constants.cementMill_2
import com.example.machines.data.local.Constants.clayCrusher
import com.example.machines.data.local.Constants.kiln
import com.example.machines.data.local.Constants.limestone
import com.example.machines.data.local.Constants.machineType
import com.example.machines.data.local.Constants.rawMill
import com.example.machines.data.local.Type
import com.example.machines.data.model.CementMillMachine1
import com.example.machines.data.model.CementMillMachine2
import com.example.machines.data.model.ClayCrusherMachine
import com.example.machines.data.model.KilnMachine
import com.example.machines.data.model.LimestoneMachine
import com.example.machines.data.model.RawMillMachine
import com.example.machines.databinding.FragmentStopTimeBinding
import com.example.machines.utils.MachineUtils.changeThumbTint
import com.example.machines.utils.click
import com.example.machines.utils.differentInTwoTimes
import com.example.machines.utils.drawScreenHeader
import com.example.machines.utils.toast
import com.example.machines.utils.validateHoursOrMinutes
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StopTimeFragment : Fragment() {

    private lateinit var binding: FragmentStopTimeBinding
    private lateinit var viewModel: StopTimeViewModel
    private var reason: String = ""
    private var endTime: String = ""


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStopTimeBinding.inflate(inflater, container, false)

        binding.header.drawScreenHeader(getString(R.string.stop_time), this)
        viewModel = ViewModelProvider(this)[StopTimeViewModel::class.java]
        setClickListeners()

        requireContext().apply {
            validateHoursOrMinutes(binding.etHours, HOURS)
            validateHoursOrMinutes(binding.etMinutes, MINUTES)
        }
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
        reason = binding.etReasonEndTime.text.toString().trim()
        endTime = time
        if (endTime == RUNNING) endTime = SEVEN_AM

        when (machineType) {
            Type.LIMESTONE.value -> {
                viewModel.updateLimestone(
                    LimestoneMachine(
                        limestone.id, limestone.startTime, time, reason,
                        differentInTwoTimes(updateStopTime(limestone.startTime), endTime)
                    )
                )
            }

            Type.CLAY_CRUSHER.value -> {
                viewModel.updateClayCrusher(
                    ClayCrusherMachine(
                        clayCrusher.id, clayCrusher.startTime, time, reason,
                        differentInTwoTimes(updateStopTime(clayCrusher.startTime), endTime)
                    )
                )
            }

            Type.RAW_MILL.value -> {
                viewModel.updateRawMill(
                    RawMillMachine(
                        rawMill.id, rawMill.startTime, time, reason,
                        differentInTwoTimes(updateStopTime(rawMill.startTime), endTime)
                    )
                )
            }

            Type.KILN.value -> {
                viewModel.updateKiln(
                    KilnMachine(
                        kiln.id, kiln.startTime, time, reason,
                        differentInTwoTimes(updateStopTime(kiln.startTime), endTime)
                    )
                )
            }

            Type.CEMENT_MILL_ONE.value -> {
                viewModel.updateCementMillOne(
                    CementMillMachine1(
                        cementMill_1.id, cementMill_1.startTime, time, reason,
                        differentInTwoTimes(updateStopTime(cementMill_1.startTime), endTime)
                    )
                )
            }

            Type.CEMENT_MILL_TWO.value -> {
                viewModel.updateCementMillTwo(
                    CementMillMachine2(
                        cementMill_2.id, cementMill_2.startTime, time, reason,
                        differentInTwoTimes(updateStopTime(cementMill_2.startTime), endTime)
                    )
                )
            }
        }
        findNavController().navigateUp()
    }

    private fun updateStopTime(startTime: String): String {
        return if (startTime == RUNNING) SEVEN_AM else startTime
    }
}
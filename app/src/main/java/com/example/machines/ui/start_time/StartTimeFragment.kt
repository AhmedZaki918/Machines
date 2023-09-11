package com.example.machines.ui.start_time

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
import com.example.machines.data.local.Constants.DEFAULT_VALUE
import com.example.machines.data.local.Constants.EMPTY
import com.example.machines.data.local.Constants.HOURS
import com.example.machines.data.local.Constants.MINUTES
import com.example.machines.data.local.Constants.RUNNING
import com.example.machines.data.local.Constants.R_H_RESET
import com.example.machines.data.local.Constants.machineType
import com.example.machines.data.local.Type
import com.example.machines.data.model.CementMillMachine1
import com.example.machines.data.model.CementMillMachine2
import com.example.machines.data.model.ClayCrusherMachine
import com.example.machines.data.model.KilnMachine
import com.example.machines.data.model.LimestoneMachine
import com.example.machines.data.model.RawMillMachine
import com.example.machines.databinding.FragmentStartTimeBinding
import com.example.machines.utils.MachineUtils.changeThumbTint
import com.example.machines.utils.click
import com.example.machines.utils.drawScreenHeader
import com.example.machines.utils.toast
import com.example.machines.utils.validateHoursOrMinutes
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StartTimeFragment : Fragment() {

    private lateinit var binding: FragmentStartTimeBinding
    private lateinit var viewModel: StartTimeViewModel


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartTimeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[StartTimeViewModel::class.java]
        binding.header.drawScreenHeader(getString(R.string.start_time), this)
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
            btnAdd.click { saveTime() }
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
            val startTime = hours + COLUMN + minutes
            if (runningStatus) addStartTime(RUNNING)
            else addStartTime(startTime)
        }
    }


    private fun isTimeEmpty(): Boolean {
        val hours = binding.etHours.text.toString().trim()
        val minutes = binding.etMinutes.text.toString().trim()
        return (hours.isEmpty() || minutes.isEmpty())
    }


    private fun addStartTime(startTime: String) {
        when (machineType) {
            Type.LIMESTONE.value -> {
                viewModel.addLimestone(
                    LimestoneMachine(0, startTime, DEFAULT_VALUE, EMPTY, R_H_RESET)
                )
            }

            Type.CLAY_CRUSHER.value -> {
                viewModel.addClayCrusher(
                    ClayCrusherMachine(0, startTime, DEFAULT_VALUE, EMPTY, R_H_RESET)
                )
            }

            Type.RAW_MILL.value -> {
                viewModel.addRawMill(
                    RawMillMachine(0, startTime, DEFAULT_VALUE, EMPTY, R_H_RESET)
                )
            }

            Type.KILN.value -> {
                viewModel.addKiln(
                    KilnMachine(0, startTime, DEFAULT_VALUE, EMPTY, R_H_RESET)
                )
            }

            Type.CEMENT_MILL_ONE.value -> {
                viewModel.addCementMillOne(
                    CementMillMachine1(0, startTime, DEFAULT_VALUE, EMPTY, R_H_RESET)
                )
            }

            Type.CEMENT_MILL_TWO.value -> {
                viewModel.addCementMillTwo(
                    CementMillMachine2(0, startTime, DEFAULT_VALUE, EMPTY, R_H_RESET)
                )
            }
        }
        findNavController().navigateUp()
    }
}
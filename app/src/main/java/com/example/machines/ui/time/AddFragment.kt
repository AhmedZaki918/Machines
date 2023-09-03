package com.example.machines.ui.time

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.machines.R
import com.example.machines.data.local.Constants.COLUMN
import com.example.machines.data.local.Constants.DEFAULT_VALUE
import com.example.machines.data.local.Constants.EMPTY
import com.example.machines.data.local.Constants.RUNNING
import com.example.machines.data.local.Constants.R_H_RESET
import com.example.machines.data.local.Constants.machineType
import com.example.machines.data.local.Type
import com.example.machines.data.model.ClayCrusherMachine
import com.example.machines.data.model.LimestoneMachine
import com.example.machines.data.model.RawMillMachine
import com.example.machines.databinding.FragmentAddBinding
import com.example.machines.ui.claycrusher.ClayCrusherViewModel
import com.example.machines.ui.limestone.LimestoneViewModel
import com.example.machines.ui.raw_mill.RawMillViewModel
import com.example.machines.utils.MachineUtils.changeThumbTint
import com.example.machines.utils.click
import com.example.machines.utils.drawScreenHeader
import com.example.machines.utils.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding

    private lateinit var limestoneViewModel: LimestoneViewModel
    private lateinit var clayCrusherViewModel: ClayCrusherViewModel
    private lateinit var rawMillViewModel: RawMillViewModel


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)

        limestoneViewModel = ViewModelProvider(this)[LimestoneViewModel::class.java]
        clayCrusherViewModel = ViewModelProvider(this)[ClayCrusherViewModel::class.java]
        rawMillViewModel = ViewModelProvider(this)[RawMillViewModel::class.java]


        binding.header.drawScreenHeader(getString(R.string.start_time), this)
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
            if (runningStatus) addOneItem(RUNNING)
            else addOneItem(startTime)
        }
    }


    private fun isTimeEmpty(): Boolean {
        val hours = binding.etHours.text.toString().trim()
        val minutes = binding.etMinutes.text.toString().trim()
        return (hours.isEmpty() || minutes.isEmpty())
    }


    private fun addOneItem(startTime: String) {
        when (machineType) {
            Type.LIMESTONE.value -> performAddItem(startTime, limestoneViewModel)
            Type.CLAY_CRUSHER.value -> performAddItem(startTime, clayCrusherViewModel)
            Type.RAW_MILL.value -> performAddItem(startTime, rawMillViewModel)
        }
        findNavController().navigateUp()
    }


    private fun performAddItem(
        startTime: String,
        viewModel: ViewModel
    ) {
        when (viewModel) {
            limestoneViewModel -> {
                limestoneViewModel.addLimestone(
                    LimestoneMachine(0, startTime, DEFAULT_VALUE, EMPTY, R_H_RESET)
                )
            }

            clayCrusherViewModel -> {
                clayCrusherViewModel.addClayCrusher(
                    ClayCrusherMachine(0, startTime, DEFAULT_VALUE, EMPTY, R_H_RESET)
                )
            }

            rawMillViewModel -> {
                rawMillViewModel.addRawMill(
                    RawMillMachine(0, startTime, DEFAULT_VALUE, EMPTY, R_H_RESET)
                )
            }
        }
    }
}
package com.example.machines.ui.limestone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.machines.R
import com.example.machines.data.local.Constants.COLUMN
import com.example.machines.data.local.Constants.DEFAULT_HOUR
import com.example.machines.data.local.Constants.DEFAULT_VALUE
import com.example.machines.data.local.Constants.EMPTY
import com.example.machines.data.local.Constants.LIMESTONE_STATUS_KEY
import com.example.machines.data.local.Constants.MINUTES_RESET
import com.example.machines.data.local.Constants.RH_LIMESTONE_KEY
import com.example.machines.data.local.Constants.machine
import com.example.machines.data.local.Constants.machineType
import com.example.machines.data.local.RunningStatus
import com.example.machines.data.local.Type
import com.example.machines.data.model.LimestoneMachine
import com.example.machines.databinding.FragmentLimestoneBinding
import com.example.machines.databinding.HeaderBinding
import com.example.machines.ui.Time
import com.example.machines.ui.adapter.LimestoneAdapter
import com.example.machines.utils.MachineUtils.updateNotRunningHours
import com.example.machines.utils.OnItemClick
import com.example.machines.utils.UserPreferences
import com.example.machines.utils.click
import com.example.machines.utils.convertMinutesToTime
import com.example.machines.utils.convertTimeToMinutes
import com.example.machines.utils.drawScreenHeader
import com.example.machines.utils.formatTime
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LimestoneFragment : Fragment(), OnItemClick {

    private lateinit var binding: FragmentLimestoneBinding
    private lateinit var viewModel: LimestoneViewModel
    private var rhTotal: Time? = null
    private lateinit var userPreferences: UserPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLimestoneBinding.inflate(inflater, container, false)

        binding.header.drawScreenHeader(getString(R.string.limestone_crusher), this)
        viewModel = ViewModelProvider(this)[LimestoneViewModel::class.java]
        userPreferences = UserPreferences(requireContext())

        viewModel.getAllLimestoneItems().observe(viewLifecycleOwner) { items ->
            switchVisibility(items)
        }
        setClickListeners()
        return binding.root
    }


    override fun <T> onClicked(model: T) {
        model as LimestoneMachine
        // Save item in Constants Object class
        model.apply {
            machine = LimestoneMachine(id, startTime, stopTime, reason, rh)
        }
        // Save machine name to determine which one to update before navigate
        machineType = Type.LIMESTONE.value
        findNavController()
            .navigate(R.id.action_limestoneFragment_to_updateFragment)
    }


    override fun <T> onDeleted(model: T) {
        viewModel.deleteLimestone(model as LimestoneMachine)
    }


    private fun updateUi(items: List<LimestoneMachine>) {
        binding.rvMachineDetails.adapter = LimestoneAdapter(items, this)
        rhTotal = updateRhTotal(items, binding.header)
        updateRunningStatusForMachine(items)

        // Save total rh for limestone
        val rh = rhTotal!!.hours + COLUMN + formatTime(rhTotal!!.minutes)
        userPreferences.saveData(RH_LIMESTONE_KEY, rh)
    }

    private fun setClickListeners() {
        // Save machine name to determine which one to add before navigate
        machineType = Type.LIMESTONE.value
        binding.fab.click {
            findNavController().navigate(R.id.action_limestoneFragment_to_addFragment)
        }
    }


    private fun updateRhTotal(
        items: List<LimestoneMachine>,
        binding: HeaderBinding
    ): Time {
        var totalMinutes = 0
        for (x in items.indices) {
            totalMinutes += convertTimeToMinutes(items[x].rh)
        }
        val totalRh = convertMinutesToTime(totalMinutes.toLong())
        binding.apply {
            tvRhHours.text = totalRh.hours
            tvRhMinutes.text = formatTime(totalRh.minutes)
        }
        return updateNotRunningHours(totalMinutes, binding)
    }


    private fun switchVisibility(items: List<LimestoneMachine>) {
        binding.apply {
            if (items.isEmpty()) {
                updateRunningStatusForMachine(items)
                rvMachineDetails.visibility = INVISIBLE
                tvNoData.visibility = VISIBLE
                ivNoData.visibility = VISIBLE
                tvStart.visibility = INVISIBLE
                tvEnd.visibility = INVISIBLE
                tvRh.visibility = INVISIBLE
                header.tvRhHoursDiff.text = DEFAULT_HOUR
                header.tvRhMinutesDiff.text = MINUTES_RESET
            } else {
                rvMachineDetails.visibility = VISIBLE
                tvStart.visibility = VISIBLE
                tvEnd.visibility = VISIBLE
                tvRh.visibility = VISIBLE
                tvNoData.visibility = INVISIBLE
                ivNoData.visibility = INVISIBLE
                updateUi(items)
            }
        }
    }


    private fun updateRunningStatusForMachine(items: List<LimestoneMachine>) {
        if (items.isEmpty()) {
            userPreferences.saveData(
                LIMESTONE_STATUS_KEY,
                RunningStatus.NO_START.value
            )
        } else {
            if (items[0].startTime != EMPTY && items[0].stopTime == DEFAULT_VALUE) {
                userPreferences.saveData(
                    LIMESTONE_STATUS_KEY,
                    RunningStatus.NO_STOP.value
                )
            } else {
                userPreferences.saveData(
                    LIMESTONE_STATUS_KEY,
                    RunningStatus.NORMAL.value
                )
            }
        }
    }
}
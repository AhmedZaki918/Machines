package com.example.machines.ui.raw_mill

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
import com.example.machines.data.local.Constants
import com.example.machines.data.local.Constants.COLUMN
import com.example.machines.data.local.Constants.DEFAULT_HOUR
import com.example.machines.data.local.Constants.DEFAULT_VALUE
import com.example.machines.data.local.Constants.EMPTY
import com.example.machines.data.local.Constants.MINUTES_RESET
import com.example.machines.data.local.Constants.RAW_MILL_STATUS_KEY
import com.example.machines.data.local.Constants.machineType
import com.example.machines.data.local.Constants.rawMill
import com.example.machines.data.local.RunningStatus
import com.example.machines.data.local.Type
import com.example.machines.data.model.RawMillMachine
import com.example.machines.databinding.FragmentRawMillBinding
import com.example.machines.databinding.HeaderBinding
import com.example.machines.ui.Time
import com.example.machines.ui.adapter.RawMillAdapter
import com.example.machines.utils.MachineUtils
import com.example.machines.utils.OnItemClick
import com.example.machines.utils.UserPreferences
import com.example.machines.utils.click
import com.example.machines.utils.convertMinutesToTime
import com.example.machines.utils.convertTimeToMinutes
import com.example.machines.utils.drawScreenHeader
import com.example.machines.utils.formatTime
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RawMillFragment : Fragment(),OnItemClick {

    private lateinit var binding: FragmentRawMillBinding
    private lateinit var viewModel: RawMillViewModel
    private lateinit var userPreferences : UserPreferences
    private var rhTotal: Time? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRawMillBinding.inflate(inflater, container, false)

        binding.header.drawScreenHeader(getString(R.string.raw_mill),this)
        viewModel = ViewModelProvider(this)[RawMillViewModel::class.java]
        userPreferences = UserPreferences(requireContext())

        viewModel.getAllRawMillItems().observe(viewLifecycleOwner) { items ->
            switchVisibility(items)
        }
        setClickListeners()
        return binding.root
    }


    override fun <T> onClicked(model: T) {
        model as RawMillMachine
        // Save item in Constants Object class
        model.apply {
            rawMill = RawMillMachine(id, startTime, stopTime, reason, rh)
        }
        // Save machine name to determine which one to update before navigate
        machineType = Type.RAW_MILL.value
        findNavController()
            .navigate(R.id.action_rawMillFragment_to_updateFragment)
    }

    override fun <T> onDeleted(model: T) {
        viewModel.deleteRawMill(model as RawMillMachine)
    }


    private fun updateUi(items: List<RawMillMachine>) {
        binding.rvRawMill.adapter = RawMillAdapter(items, this)
        rhTotal = updateRhTotal(items, binding.header)
        updateRunningStatusForMachine(items)

        // Save total rh for raw mill
        val rh = rhTotal!!.hours + COLUMN + formatTime(rhTotal!!.minutes)
        userPreferences.saveData(Constants.RH_RAW_MILL_KEY, rh)
    }


    private fun setClickListeners() {
        // Save machine name to determine which one to add before navigate
        machineType = Type.RAW_MILL.value
        binding.fab.click {
            findNavController().navigate(R.id.action_rawMillFragment_to_addFragment)
        }
    }


    private fun updateRhTotal(
        items: List<RawMillMachine>,
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
        return MachineUtils.updateNotRunningHours(totalMinutes, binding)
    }


    private fun switchVisibility(items: List<RawMillMachine>) {
        binding.apply {
            if (items.isEmpty()) {
                updateRunningStatusForMachine(items)
                rvRawMill.visibility = INVISIBLE
                tvNoData.visibility = VISIBLE
                ivNoData.visibility = VISIBLE
                tvStart.visibility = INVISIBLE
                tvEnd.visibility = INVISIBLE
                tvRh.visibility = INVISIBLE
                header.tvRhHoursDiff.text = DEFAULT_HOUR
                header.tvRhMinutesDiff.text = MINUTES_RESET
            } else {
                rvRawMill.visibility = VISIBLE
                tvStart.visibility = VISIBLE
                tvEnd.visibility = VISIBLE
                tvRh.visibility = VISIBLE
                tvNoData.visibility = INVISIBLE
                ivNoData.visibility = INVISIBLE
                updateUi(items)
            }
        }
    }


    private fun updateRunningStatusForMachine(items: List<RawMillMachine>) {
        if (items.isEmpty()) {
            userPreferences.saveData(
                RAW_MILL_STATUS_KEY,
                RunningStatus.NO_START.value
            )
        } else {
            if (items[0].startTime != EMPTY && items[0].stopTime == DEFAULT_VALUE) {
                userPreferences.saveData(
                    RAW_MILL_STATUS_KEY,
                    RunningStatus.NO_STOP.value
                )
            } else {
                userPreferences.saveData(
                    RAW_MILL_STATUS_KEY,
                    RunningStatus.NORMAL.value
                )
            }
        }
    }
}
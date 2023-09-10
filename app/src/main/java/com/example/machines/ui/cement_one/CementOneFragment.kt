package com.example.machines.ui.cement_one

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
import com.example.machines.data.local.Constants.CEMENT_MILL_1_STATUS_KEY
import com.example.machines.data.local.Constants.COLUMN
import com.example.machines.data.local.Constants.DEFAULT_HOUR
import com.example.machines.data.local.Constants.DEFAULT_VALUE
import com.example.machines.data.local.Constants.EMPTY
import com.example.machines.data.local.Constants.MINUTES_RESET
import com.example.machines.data.local.Constants.RH_CEMENT_MILL_1_KEY
import com.example.machines.data.local.Constants.cementMill_1
import com.example.machines.data.local.Constants.machineType
import com.example.machines.data.local.RunningStatus
import com.example.machines.data.local.Type
import com.example.machines.data.model.CementMillMachine1
import com.example.machines.databinding.FragmentCementOneBinding
import com.example.machines.databinding.HeaderBinding
import com.example.machines.ui.Time
import com.example.machines.ui.adapter.CementMillOneAdapter
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
class CementOneFragment : Fragment(),OnItemClick {

    private lateinit var binding: FragmentCementOneBinding
    private lateinit var viewModel: CementOneViewModel
    private lateinit var userPreferences: UserPreferences
    private var rhTotal: Time? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCementOneBinding.inflate(inflater, container, false)

        binding.header.drawScreenHeader(getString(R.string.cement_mill_1), this)
        viewModel = ViewModelProvider(this)[CementOneViewModel::class.java]
        userPreferences = UserPreferences(requireContext())

        viewModel.getAllCementMillOneItems().observe(viewLifecycleOwner) { items ->
            switchVisibility(items)
        }
        setClickListeners()
        return binding.root
    }


    override fun <T> onClicked(model: T) {
        model as CementMillMachine1
        // Save item in Constants Object class
        model.apply {
            cementMill_1 = CementMillMachine1(id, startTime, stopTime, reason, rh)
        }
        // Save machine name to determine which one to update before navigate
        machineType = Type.CEMENT_MILL_ONE.value
        findNavController()
            .navigate(R.id.action_cementMillOneFragment_to_updateFragment)
    }

    override fun <T> onDeleted(model: T) {
        viewModel.deleteCementMillOne(model as CementMillMachine1)
    }


    private fun updateUi(items: List<CementMillMachine1>) {
        binding.rvCementMill1.adapter = CementMillOneAdapter(items, this,false)
        rhTotal = updateRhTotal(items, binding.header)
        updateRunningStatusForMachine(items)

        // Save total rh for Cement Mill one
        val rh = rhTotal!!.hours + COLUMN + formatTime(rhTotal!!.minutes)
        userPreferences.saveData(RH_CEMENT_MILL_1_KEY, rh)
    }


    private fun setClickListeners() {
        // Save machine name to determine which one to add before navigate
        machineType = Type.CEMENT_MILL_ONE.value
        binding.fab.click {
            findNavController().navigate(R.id.action_cementMillOneFragment_to_addFragment)
        }
    }


    private fun updateRhTotal(
        items: List<CementMillMachine1>,
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


    private fun switchVisibility(items: List<CementMillMachine1>) {
        binding.apply {
            if (items.isEmpty()) {
                updateRunningStatusForMachine(items)
                rvCementMill1.visibility = INVISIBLE
                tvNoData.visibility = VISIBLE
                ivNoData.visibility = VISIBLE
                tvStart.visibility = INVISIBLE
                tvEnd.visibility = INVISIBLE
                tvRh.visibility = INVISIBLE
                header.tvRhHoursDiff.text = DEFAULT_HOUR
                header.tvRhMinutesDiff.text = MINUTES_RESET
            } else {
                rvCementMill1.visibility = VISIBLE
                tvStart.visibility = VISIBLE
                tvEnd.visibility = VISIBLE
                tvRh.visibility = VISIBLE
                tvNoData.visibility = INVISIBLE
                ivNoData.visibility = INVISIBLE
                updateUi(items)
            }
        }
    }


    private fun updateRunningStatusForMachine(items: List<CementMillMachine1>) {
        if (items.isEmpty()) {
            userPreferences.saveData(
                CEMENT_MILL_1_STATUS_KEY,
                RunningStatus.NO_START.value
            )
        } else {
            if (items[0].startTime != EMPTY && items[0].stopTime == DEFAULT_VALUE) {
                userPreferences.saveData(
                    CEMENT_MILL_1_STATUS_KEY,
                    RunningStatus.NO_STOP.value
                )
            } else {
                userPreferences.saveData(
                    CEMENT_MILL_1_STATUS_KEY,
                    RunningStatus.NORMAL.value
                )
            }
        }
    }
}
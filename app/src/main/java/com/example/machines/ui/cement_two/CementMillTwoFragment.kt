package com.example.machines.ui.cement_two

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
import com.example.machines.data.local.Constants.CEMENT_MILL_2_STATUS_KEY
import com.example.machines.data.local.Constants.COLUMN
import com.example.machines.data.local.Constants.DEFAULT_HOUR
import com.example.machines.data.local.Constants.DEFAULT_VALUE
import com.example.machines.data.local.Constants.EMPTY
import com.example.machines.data.local.Constants.MINUTES_RESET
import com.example.machines.data.local.Constants.RH_CEMENT_MILL_2_KEY
import com.example.machines.data.local.Constants.cementMill_2
import com.example.machines.data.local.Constants.machineType
import com.example.machines.data.local.RunningStatus
import com.example.machines.data.local.Type
import com.example.machines.data.model.CementMillMachine2
import com.example.machines.databinding.FragmentCementMillTwoBinding
import com.example.machines.databinding.HeaderBinding
import com.example.machines.ui.Time
import com.example.machines.ui.adapter.CementMillTwoAdapter
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
class CementMillTwoFragment : Fragment(), OnItemClick {

    private lateinit var binding: FragmentCementMillTwoBinding
    private lateinit var viewModel: CementTwoViewModel
    private lateinit var userPreferences: UserPreferences
    private var rhTotal: Time? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCementMillTwoBinding.inflate(inflater, container, false)

        binding.header.drawScreenHeader(getString(R.string.cement_mill_2), this)
        viewModel = ViewModelProvider(this)[CementTwoViewModel::class.java]
        userPreferences = UserPreferences(requireContext())


        viewModel.getAllCementTwoItems().observe(viewLifecycleOwner) { items ->
            switchVisibility(items)
        }
        setClickListeners()
        return binding.root
    }


    override fun <T> onClicked(model: T) {
        model as CementMillMachine2
        // Save item in Constants Object class
        model.apply {
            cementMill_2 = CementMillMachine2(id, startTime, stopTime, reason, rh)
        }
        // Save machine name to determine which one to update before navigate
        machineType = Type.CEMENT_MILL_TWO.value
        findNavController()
            .navigate(R.id.action_cementMillTwoFragment_to_updateFragment)
    }

    override fun <T> onDeleted(model: T) {
        viewModel.deleteCementTwo(model as CementMillMachine2)
    }


    private fun updateUi(items: List<CementMillMachine2>) {
        binding.rvCementMill2.adapter = CementMillTwoAdapter(items, this, false)
        rhTotal = updateRhTotal(items, binding.header)
        updateRunningStatusForMachine(items)

        // Save total rh for Cement Mill Two
        val rh = rhTotal!!.hours + COLUMN + formatTime(rhTotal!!.minutes)
        userPreferences.saveData(RH_CEMENT_MILL_2_KEY, rh)
    }


    private fun updateRhTotal(
        items: List<CementMillMachine2>,
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


    private fun switchVisibility(items: List<CementMillMachine2>) {
        binding.apply {
            if (items.isEmpty()) {
                updateRunningStatusForMachine(items)
                rvCementMill2.visibility = INVISIBLE
                tvNoData.visibility = VISIBLE
                ivNoData.visibility = VISIBLE
                tvStart.visibility = INVISIBLE
                tvEnd.visibility = INVISIBLE
                tvRh.visibility = INVISIBLE
                header.tvRhHoursDiff.text = DEFAULT_HOUR
                header.tvRhMinutesDiff.text = MINUTES_RESET
            } else {
                rvCementMill2.visibility = VISIBLE
                tvStart.visibility = VISIBLE
                tvEnd.visibility = VISIBLE
                tvRh.visibility = VISIBLE
                tvNoData.visibility = INVISIBLE
                ivNoData.visibility = INVISIBLE
                updateUi(items)
            }
        }
    }


    private fun setClickListeners() {
        // Save machine name to determine which one to add before navigate
        machineType = Type.CEMENT_MILL_TWO.value
        binding.fab.click {
            findNavController().navigate(R.id.action_cementMillTwoFragment_to_addFragment)
        }
    }


    private fun updateRunningStatusForMachine(items: List<CementMillMachine2>) {
        if (items.isEmpty()) {
            userPreferences.saveData(
                CEMENT_MILL_2_STATUS_KEY,
                RunningStatus.NO_START.value
            )
        } else {
            if (items[0].startTime != EMPTY && items[0].stopTime == DEFAULT_VALUE) {
                userPreferences.saveData(
                    CEMENT_MILL_2_STATUS_KEY,
                    RunningStatus.NO_STOP.value
                )
            } else {
                userPreferences.saveData(
                    CEMENT_MILL_2_STATUS_KEY,
                    RunningStatus.NORMAL.value
                )
            }
        }
    }
}
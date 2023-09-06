package com.example.machines.ui.kiln

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
import com.example.machines.data.local.Constants.DEFAULT_VALUE
import com.example.machines.data.local.Constants.EMPTY
import com.example.machines.data.local.Constants.KILN_STATUS_KEY
import com.example.machines.data.local.Constants.RH_KILN_KEY
import com.example.machines.data.local.Constants.kiln
import com.example.machines.data.local.Constants.machineType
import com.example.machines.data.local.RunningStatus
import com.example.machines.data.local.Type
import com.example.machines.data.model.KilnMachine
import com.example.machines.databinding.FragmentKilnBinding
import com.example.machines.databinding.HeaderBinding
import com.example.machines.ui.Time
import com.example.machines.ui.adapter.KilnAdapter
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
class KilnFragment : Fragment(), OnItemClick {

    private lateinit var binding: FragmentKilnBinding
    private lateinit var viewModel: KilnViewModel
    private lateinit var userPreferences: UserPreferences
    private var rhTotal: Time? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentKilnBinding.inflate(inflater, container, false)

        binding.header.drawScreenHeader(getString(R.string.kiln), this)
        viewModel = ViewModelProvider(this)[KilnViewModel::class.java]
        userPreferences = UserPreferences(requireContext())

        viewModel.getAllKilnItems().observe(viewLifecycleOwner) { items ->
            switchVisibility(items)
        }

        setClickListeners()
        return binding.root
    }


    override fun <T> onClicked(model: T) {
        model as KilnMachine
        // Save item in Constants Object class
        model.apply {
            kiln = KilnMachine(id, startTime, stopTime, reason, rh)
        }
        // Save machine name to determine which one to update before navigate
        machineType = Type.KILN.value
        findNavController()
            .navigate(R.id.action_kilnFragment_to_updateFragment)
    }

    override fun <T> onDeleted(model: T) {
        viewModel.deleteKiln(model as KilnMachine)
    }


    private fun updateUi(items: List<KilnMachine>) {
        binding.rvKiln.adapter = KilnAdapter(items, this,false)
        rhTotal = updateRhTotal(items, binding.header)
        updateRunningStatusForMachine(items)

        // Save total rh for kiln
        val rh = rhTotal!!.hours + COLUMN + formatTime(rhTotal!!.minutes)
        userPreferences.saveData(RH_KILN_KEY, rh)
    }


    private fun switchVisibility(items: List<KilnMachine>) {
        binding.apply {
            if (items.isEmpty()) {
                updateRunningStatusForMachine(items)
                rvKiln.visibility = INVISIBLE
                tvNoData.visibility = VISIBLE
                ivNoData.visibility = VISIBLE
                tvStart.visibility = INVISIBLE
                tvEnd.visibility = INVISIBLE
                tvRh.visibility = INVISIBLE
                header.tvRhHoursDiff.text = Constants.DEFAULT_HOUR
                header.tvRhMinutesDiff.text = Constants.MINUTES_RESET
            } else {
                rvKiln.visibility = VISIBLE
                tvStart.visibility = VISIBLE
                tvEnd.visibility = VISIBLE
                tvRh.visibility = VISIBLE
                tvNoData.visibility = INVISIBLE
                ivNoData.visibility = INVISIBLE
                updateUi(items)
            }
        }
    }


    private fun updateRhTotal(
        items: List<KilnMachine>,
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


    private fun setClickListeners() {
        // Save machine name to determine which one to add before navigate
        machineType = Type.KILN.value
        binding.fab.click {
            findNavController().navigate(R.id.action_kilnFragment_to_addFragment)
        }
    }


    private fun updateRunningStatusForMachine(items: List<KilnMachine>) {
        if (items.isEmpty()) {
            userPreferences.saveData(
                KILN_STATUS_KEY,
                RunningStatus.NO_START.value
            )
        } else {
            if (items[0].startTime != EMPTY && items[0].stopTime == DEFAULT_VALUE) {
                userPreferences.saveData(
                    KILN_STATUS_KEY,
                    RunningStatus.NO_STOP.value
                )
            } else {
                userPreferences.saveData(
                    KILN_STATUS_KEY,
                    RunningStatus.NORMAL.value
                )
            }
        }
    }
}
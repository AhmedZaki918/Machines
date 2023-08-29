package com.example.machines.ui.claycrusher

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.machines.R
import com.example.machines.data.local.Constants.COLUMN
import com.example.machines.data.local.Constants.DEFAULT_HOUR
import com.example.machines.data.local.Constants.MINUTES_RESET
import com.example.machines.data.local.Constants.RH_CLAY_CRUSHER
import com.example.machines.data.local.Constants.clayCrusherMachine
import com.example.machines.data.local.Constants.machineType
import com.example.machines.data.local.Type
import com.example.machines.data.model.ClayCrusherMachine
import com.example.machines.databinding.FragmentClayCrusherBinding
import com.example.machines.databinding.HeaderBinding
import com.example.machines.ui.Time
import com.example.machines.ui.adapter.ClayCrusherAdapter
import com.example.machines.utils.MachineUtils
import com.example.machines.utils.OnItemClick
import com.example.machines.utils.click
import com.example.machines.utils.convertMinutesToTime
import com.example.machines.utils.convertTimeToMinutes
import com.example.machines.utils.drawScreenHeader
import com.example.machines.utils.formatTime
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ClayCrusherFragment : Fragment(), OnItemClick {

    private lateinit var binding: FragmentClayCrusherBinding
    private lateinit var viewModel: ClayCrusherViewModel
    private var rhTotal: Time? = null


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClayCrusherBinding.inflate(inflater, container, false)

        binding.header.drawScreenHeader(getString(R.string.clay_crusher), this)
        viewModel = ViewModelProvider(this)[ClayCrusherViewModel::class.java]
        viewModel.getAllClayCrusherItems().observe(viewLifecycleOwner) { items ->
            switchVisibility(items)
            updateUi(items)
        }
        setClickListeners()
        return binding.root
    }


    private fun updateUi(items: List<ClayCrusherMachine>) {
        binding.rvClayCrusher.adapter = ClayCrusherAdapter(items, this)
        rhTotal = updateRhTotal(items, binding.header)
        // Save total rh for limestone
        RH_CLAY_CRUSHER = rhTotal!!.hours + COLUMN + formatTime(rhTotal!!.minutes)
    }


    private fun setClickListeners() {
        // Save machine name to determine which one to add before navigate
        machineType = Type.CLAY_CRUSHER.value
        binding.fab.click {
            findNavController().navigate(R.id.action_clayCrusherFragment_to_addFragment)
        }
    }


    private fun updateRhTotal(
        items: List<ClayCrusherMachine>,
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


    private fun switchVisibility(items: List<ClayCrusherMachine>) {
        binding.apply {
            if (items.isEmpty()) {
                rvClayCrusher.visibility = INVISIBLE
                tvNoData.visibility = VISIBLE
                ivNoData.visibility = VISIBLE
                tvStart.visibility = INVISIBLE
                tvEnd.visibility = INVISIBLE
                tvRh.visibility = INVISIBLE
                header.tvRhHoursDiff.text = DEFAULT_HOUR
                header.tvRhMinutesDiff.text = MINUTES_RESET
            } else {
                rvClayCrusher.visibility = VISIBLE
                tvStart.visibility = VISIBLE
                tvEnd.visibility = VISIBLE
                tvRh.visibility = VISIBLE
                tvNoData.visibility = INVISIBLE
                ivNoData.visibility = INVISIBLE
            }
        }
    }

    override fun <T> onClicked(model: T) {
        model as ClayCrusherMachine
        // Save item in Constants Object class
        model.apply {
            clayCrusherMachine = ClayCrusherMachine(id, startTime, stopTime, reason, rh)
        }
        // Save machine name to determine which one to update before navigate
        machineType = Type.CLAY_CRUSHER.value
        findNavController()
            .navigate(R.id.action_clayCrusherFragment_to_updateFragment)
    }

    override fun <T> onDeleted(model: T) {
        viewModel.deleteClayCrusher(model as ClayCrusherMachine)
    }
}
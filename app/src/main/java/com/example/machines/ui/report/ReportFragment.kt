package com.example.machines.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.machines.R
import com.example.machines.data.local.Constants.CLAY_CRUSHER_STATUS_KEY
import com.example.machines.data.local.Constants.DEFAULT_VALUE
import com.example.machines.data.local.Constants.EMPTY
import com.example.machines.data.local.Constants.KILN_STATUS_KEY
import com.example.machines.data.local.Constants.LIMESTONE_STATUS_KEY
import com.example.machines.data.local.Constants.RAW_MILL_STATUS_KEY
import com.example.machines.data.local.Constants.RH_CLAY_CRUSHER_KEY
import com.example.machines.data.local.Constants.RH_KILN_KEY
import com.example.machines.data.local.Constants.RH_LIMESTONE_KEY
import com.example.machines.data.local.Constants.RH_RAW_MILL_KEY
import com.example.machines.data.local.RunningStatus
import com.example.machines.data.model.ClayCrusherMachine
import com.example.machines.data.model.KilnMachine
import com.example.machines.data.model.LimestoneMachine
import com.example.machines.data.model.RawMillMachine
import com.example.machines.databinding.FragmentReportBinding
import com.example.machines.databinding.HeaderFullReportBinding
import com.example.machines.ui.adapter.ClayCrusherAdapter
import com.example.machines.ui.adapter.KilnAdapter
import com.example.machines.ui.adapter.LimestoneAdapter
import com.example.machines.ui.adapter.RawMillAdapter
import com.example.machines.utils.UserPreferences
import com.example.machines.utils.drawScreenHeader
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ReportFragment : Fragment() {

    private lateinit var binding: FragmentReportBinding
    private lateinit var viewModel: ReportViewModel
    private lateinit var userPreferences: UserPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReportBinding.inflate(inflater, container, false)
        binding.header.drawScreenHeader(getString(R.string.full_report), this)

        viewModel = ViewModelProvider(this)[ReportViewModel::class.java]
        userPreferences = UserPreferences(requireContext())

        updateLimestone()
        updateClayCrusher()
        updateRawMill()
        updateKiln()

        updateMachinesNames()
        switchVisibility()
        return binding.root
    }


    private fun updateMachinesNames() {
        binding.apply {
            headerLimestone.tvMachineName.text = LimestoneMachine.machineName()
            headerClayCrusher.tvMachineName.text = ClayCrusherMachine.machineName()
            headerRawMill.tvMachineName.text = RawMillMachine.machineName()
            headerKiln.tvMachineName.text = KilnMachine.machineName()
        }
    }


    private fun switchVisibility() {
        binding.header.apply {
            tvRhTotalLabel.visibility = INVISIBLE
            tvRhHours.visibility = INVISIBLE
            tvRhMinutes.visibility = INVISIBLE
            tvSeparator.visibility = INVISIBLE
            tvRhDiffLabel.visibility = INVISIBLE
            tvRhHoursDiff.visibility = INVISIBLE
            tvRhMinutesDiff.visibility = INVISIBLE
            tvSeparatorDiff.visibility = INVISIBLE
        }
    }


    private fun updateLimestone() {
        viewModel.getAllLimestoneReport().observe(viewLifecycleOwner) {
            binding.apply {
                if (it.isEmpty() || it[0].startTime != EMPTY && it[0].stopTime == DEFAULT_VALUE) {

                    var runningStatusValue = userPreferences.retrieveData(LIMESTONE_STATUS_KEY)
                    if (runningStatusValue == EMPTY) {
                        runningStatusValue = RunningStatus.NO_START.value
                    }
                    updateRunningStatus(
                        headerLimestone, rvMachineLimestone, tvRhLimestone,
                        tvNoLimestone, runningStatusValue
                    )
                } else {
                    rvMachineLimestone.adapter = LimestoneAdapter(it, null, true)
                    tvRhLimestone.text = userPreferences.retrieveData(RH_LIMESTONE_KEY)
                }
            }
        }
    }


    private fun updateClayCrusher() {
        viewModel.getAllClayCrusherItems().observe(viewLifecycleOwner) {
            binding.apply {
                if (it.isEmpty() || it[0].startTime != EMPTY && it[0].stopTime == DEFAULT_VALUE) {

                    var runningStatusValue = userPreferences.retrieveData(CLAY_CRUSHER_STATUS_KEY)
                    if (runningStatusValue == EMPTY) {
                        runningStatusValue = RunningStatus.NO_START.value
                    }
                    updateRunningStatus(
                        headerClayCrusher, rvMachineClayCrusher, tvRhClayCrusher,
                        tvNoClay, runningStatusValue
                    )
                } else {
                    rvMachineClayCrusher.adapter = ClayCrusherAdapter(it, null, true)
                    tvRhClayCrusher.text = userPreferences.retrieveData(RH_CLAY_CRUSHER_KEY)
                }
            }
        }
    }


    private fun updateRawMill() {
        viewModel.getAllRawMillItems().observe(viewLifecycleOwner) {
            binding.apply {
                if (it.isEmpty() || it[0].startTime != EMPTY && it[0].stopTime == DEFAULT_VALUE) {

                    var runningStatusValue = userPreferences.retrieveData(RAW_MILL_STATUS_KEY)
                    if (runningStatusValue == EMPTY) {
                        runningStatusValue = RunningStatus.NO_START.value
                    }
                    updateRunningStatus(
                        headerRawMill, rvMachineRawMill, tvRhRawMill,
                        tvNoRawMill, runningStatusValue
                    )
                } else {
                    rvMachineRawMill.adapter = RawMillAdapter(it, null, true)
                    tvRhRawMill.text = userPreferences.retrieveData(RH_RAW_MILL_KEY)
                }
            }
        }
    }


    private fun updateKiln() {
        viewModel.getAllKilnItems().observe(viewLifecycleOwner) {
            binding.apply {
                if (it.isEmpty() || it[0].startTime != EMPTY && it[0].stopTime == DEFAULT_VALUE) {

                    var runningStatusValue = userPreferences.retrieveData(KILN_STATUS_KEY)
                    if (runningStatusValue == EMPTY) {
                        runningStatusValue = RunningStatus.NO_START.value
                    }
                    updateRunningStatus(
                        headerKiln, rvMachineKiln, tvRhKiln,
                        tvNoKiln, runningStatusValue
                    )
                } else {
                    rvMachineKiln.adapter = KilnAdapter(it, null, true)
                    tvRhKiln.text = userPreferences.retrieveData(RH_KILN_KEY)
                }
            }
        }
    }


    private fun updateRunningStatus(
        header: HeaderFullReportBinding, recyclerView: RecyclerView,
        tvRhValue: TextView, tvRunningStatus: TextView,
        runningStatusValue: String?
    ) {
        tvRhValue.visibility = GONE
        recyclerView.visibility = INVISIBLE

        header.apply {
            tvStart.visibility = GONE
            tvStop.visibility = GONE
            tvReason.visibility = GONE
            tvRh.visibility = GONE
        }
        val params = recyclerView.layoutParams
        params.height = 50
        recyclerView.layoutParams = params
        tvRunningStatus.visibility = VISIBLE
        tvRunningStatus.text = runningStatusValue
    }
}

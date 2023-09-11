package com.example.machines.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.machines.R
import com.example.machines.data.local.Constants.CEMENT_MILL_1_STATUS_KEY
import com.example.machines.data.local.Constants.CEMENT_MILL_2_STATUS_KEY
import com.example.machines.data.local.Constants.CEMENT_MILL_3_STATUS_KEY
import com.example.machines.data.local.Constants.CLAY_CRUSHER_STATUS_KEY
import com.example.machines.data.local.Constants.DEFAULT_VALUE
import com.example.machines.data.local.Constants.EMPTY
import com.example.machines.data.local.Constants.KILN_STATUS_KEY
import com.example.machines.data.local.Constants.LIMESTONE_STATUS_KEY
import com.example.machines.data.local.Constants.RAW_MILL_STATUS_KEY
import com.example.machines.data.local.Constants.RH_CEMENT_MILL_1_KEY
import com.example.machines.data.local.Constants.RH_CEMENT_MILL_2_KEY
import com.example.machines.data.local.Constants.RH_CEMENT_MILL_3_KEY
import com.example.machines.data.local.Constants.RH_CLAY_CRUSHER_KEY
import com.example.machines.data.local.Constants.RH_KILN_KEY
import com.example.machines.data.local.Constants.RH_LIMESTONE_KEY
import com.example.machines.data.local.Constants.RH_RAW_MILL_KEY
import com.example.machines.data.local.RunningStatus
import com.example.machines.data.model.CementMillMachine1
import com.example.machines.data.model.CementMillMachine2
import com.example.machines.data.model.CementMillMachine3
import com.example.machines.data.model.ClayCrusherMachine
import com.example.machines.data.model.KilnMachine
import com.example.machines.data.model.LimestoneMachine
import com.example.machines.data.model.RawMillMachine
import com.example.machines.databinding.FragmentReportBinding
import com.example.machines.databinding.ListItemMachineReportBinding
import com.example.machines.ui.adapter.CementMillOneAdapter
import com.example.machines.ui.adapter.CementMillThreeAdapter
import com.example.machines.ui.adapter.CementMillTwoAdapter
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

        updateUi()
        switchVisibility()
        return binding.root
    }

    private fun updateUi() {
        updateLimestone()
        updateClayCrusher()
        updateRawMill()
        updateKiln()
        updateCementMillOne()
        updateCementMillTwo()
        updateCementMillThree()
        updateMachinesNames()
    }


    private fun updateMachinesNames() {
        binding.apply {
            headerLimestone.tvMachineName.text = LimestoneMachine.machineName()
            headerClayCrusher.tvMachineName.text = ClayCrusherMachine.machineName()
            headerRawMill.tvMachineName.text = RawMillMachine.machineName()
            headerKiln.tvMachineName.text = KilnMachine.machineName()
            headerCementMill1.tvMachineName.text = CementMillMachine1.machineName()
            headerCementMill2.tvMachineName.text = CementMillMachine2.machineName()
            headerCementMill3.tvMachineName.text = CementMillMachine3.machineName()
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
                    updateRunningStatus(headerLimestone, runningStatusValue)
                } else {
                    headerLimestone.apply {
                        rvMachine.adapter = LimestoneAdapter(it, null, true)
                        tvRhValue.text = userPreferences.retrieveData(RH_LIMESTONE_KEY)
                    }
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
                    updateRunningStatus(headerClayCrusher, runningStatusValue)
                } else {
                    headerClayCrusher.apply {
                        rvMachine.adapter = ClayCrusherAdapter(it, null, true)
                        tvRhValue.text = userPreferences.retrieveData(RH_CLAY_CRUSHER_KEY)
                    }
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
                    updateRunningStatus(headerRawMill, runningStatusValue)
                } else {
                    headerRawMill.apply {
                        rvMachine.adapter = RawMillAdapter(it, null, true)
                        tvRhValue.text = userPreferences.retrieveData(RH_RAW_MILL_KEY)
                    }
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
                    updateRunningStatus(headerKiln, runningStatusValue)
                } else {
                    headerKiln.apply {
                        rvMachine.adapter = KilnAdapter(it, null, true)
                        tvRhValue.text = userPreferences.retrieveData(RH_KILN_KEY)
                    }
                }
            }
        }
    }


    private fun updateCementMillOne() {
        viewModel.getAllCementMillOne().observe(viewLifecycleOwner) {
            binding.apply {
                if (it.isEmpty() || it[0].startTime != EMPTY && it[0].stopTime == DEFAULT_VALUE) {
                    var runningStatusValue = userPreferences.retrieveData(CEMENT_MILL_1_STATUS_KEY)
                    if (runningStatusValue == EMPTY) {
                        runningStatusValue = RunningStatus.NO_START.value
                    }
                    updateRunningStatus(headerCementMill1, runningStatusValue)
                } else {
                    headerCementMill1.apply {
                        rvMachine.adapter = CementMillOneAdapter(it, null, true)
                        tvRhValue.text = userPreferences.retrieveData(RH_CEMENT_MILL_1_KEY)
                    }
                }
            }
        }
    }


    private fun updateCementMillTwo() {
        viewModel.getAllCementMillTwo().observe(viewLifecycleOwner) {
            binding.apply {
                if (it.isEmpty() || it[0].startTime != EMPTY && it[0].stopTime == DEFAULT_VALUE) {
                    var runningStatusValue = userPreferences.retrieveData(CEMENT_MILL_2_STATUS_KEY)
                    if (runningStatusValue == EMPTY) {
                        runningStatusValue = RunningStatus.NO_START.value
                    }
                    updateRunningStatus(headerCementMill2, runningStatusValue)
                } else {
                    headerCementMill2.apply {
                        rvMachine.adapter = CementMillTwoAdapter(it, null, true)
                        tvRhValue.text = userPreferences.retrieveData(RH_CEMENT_MILL_2_KEY)
                    }
                }
            }
        }
    }


    private fun updateCementMillThree() {
        viewModel.getAllCementMillThree().observe(viewLifecycleOwner) {
            binding.apply {
                if (it.isEmpty() || it[0].startTime != EMPTY && it[0].stopTime == DEFAULT_VALUE) {
                    var runningStatusValue = userPreferences.retrieveData(CEMENT_MILL_3_STATUS_KEY)
                    if (runningStatusValue == EMPTY) {
                        runningStatusValue = RunningStatus.NO_START.value
                    }
                    updateRunningStatus(headerCementMill3, runningStatusValue)
                } else {
                    headerCementMill3.apply {
                        rvMachine.adapter = CementMillThreeAdapter(it, null, true)
                        tvRhValue.text = userPreferences.retrieveData(RH_CEMENT_MILL_3_KEY)
                    }
                }
            }
        }
    }


    private fun updateRunningStatus(
        header: ListItemMachineReportBinding,
        runningStatusValue: String?
    ) {
        header.apply {
            tvRhValue.visibility = GONE
            rvMachine.visibility = INVISIBLE
            tvStart.visibility = GONE
            tvStop.visibility = GONE
            tvRh.visibility = GONE

            val params = header.rvMachine.layoutParams
            params.height = 50
            rvMachine.layoutParams = params
            tvNoData.visibility = VISIBLE
            tvNoData.text = runningStatusValue
        }
    }
}

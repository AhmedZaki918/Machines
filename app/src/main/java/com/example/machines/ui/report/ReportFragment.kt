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
import com.example.machines.data.local.Constants.LIMESTONE_STATUS_KEY
import com.example.machines.data.local.Constants.RH_CLAY_CRUSHER_KEY
import com.example.machines.data.local.Constants.RH_LIMESTONE_KEY
import com.example.machines.data.model.ClayCrusherMachine
import com.example.machines.data.model.LimestoneMachine
import com.example.machines.databinding.FragmentReportBinding
import com.example.machines.databinding.HeaderFullReportBinding
import com.example.machines.ui.adapter.ClayCrusherReportAdapter
import com.example.machines.ui.adapter.LimestoneReportAdapter
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

        updateMachinesNames()
        switchVisibility()
        return binding.root
    }


    private fun updateMachinesNames() {
        binding.apply {
            headerLimestone.tvMachineName.text = LimestoneMachine.machineName()
            headerClayCrusher.tvMachineName.text = ClayCrusherMachine.machineName()
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
                    updateRunningStatus(
                        headerLimestone,
                        rvMachineLimestone,
                        tvRhLimestone,
                        tvNoLimestone,
                        userPreferences.retrieveData(LIMESTONE_STATUS_KEY)
                    )
                } else {
                    rvMachineLimestone.adapter = LimestoneReportAdapter(it)
                    tvRhLimestone.text = userPreferences.retrieveData(RH_LIMESTONE_KEY)
                }
            }
        }
    }


    private fun updateClayCrusher() {
        viewModel.getAllClayCrusherItems().observe(viewLifecycleOwner) {
            binding.apply {
                if (it.isEmpty() || it[0].startTime != EMPTY && it[0].stopTime == DEFAULT_VALUE) {
                    updateRunningStatus(
                        headerClayCrusher,
                        rvMachineClayCrusher,
                        tvRhClayCrusher,
                        tvNoClay,
                        userPreferences.retrieveData(CLAY_CRUSHER_STATUS_KEY)
                    )
                } else {
                    rvMachineClayCrusher.adapter = ClayCrusherReportAdapter(it)
                    tvRhClayCrusher.text = userPreferences.retrieveData(RH_CLAY_CRUSHER_KEY)
                }
            }
        }
    }


    private fun updateRunningStatus(
        header: HeaderFullReportBinding,
        recyclerView: RecyclerView,
        tvRhValue: TextView,
        tvRunningStatus: TextView,
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

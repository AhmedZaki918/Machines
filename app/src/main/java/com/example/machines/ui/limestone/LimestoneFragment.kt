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
import com.example.machines.data.local.Constants.DEFAULT_HOUR
import com.example.machines.data.local.Constants.MINUTES_RESET
import com.example.machines.data.local.Constants.machine
import com.example.machines.data.local.MachineDao
import com.example.machines.data.model.MachineMain
import com.example.machines.databinding.FragmentLimestoneBinding
import com.example.machines.ui.adapter.IndividualMachineAdapter
import com.example.machines.utils.MachineUtils.updateRhTotal
import com.example.machines.utils.OnItemClick
import com.example.machines.utils.click
import com.example.machines.utils.drawScreenHeader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LimestoneFragment : Fragment(), OnItemClick {

    private lateinit var binding: FragmentLimestoneBinding
    private lateinit var viewModel: LimestoneViewModel

    @Inject
    lateinit var dao: MachineDao


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLimestoneBinding.inflate(inflater, container, false)
        binding.header.drawScreenHeader(getString(R.string.limestone_crusher), this)

        viewModel = ViewModelProvider(this)[LimestoneViewModel::class.java]
        viewModel.getAllLimestoneItems().observe(viewLifecycleOwner) { items ->
            switchVisibility(items)
            binding.rvMachineDetails.adapter = IndividualMachineAdapter(items, dao, this)
            updateRhTotal(items, binding.header)
        }
        setClickListeners()
        return binding.root
    }


    override fun onClicked(model: MachineMain) {
        // Save item in Constants Object class
        model.apply {
            machine = MachineMain(
                id,
                startTime,
                stopTime,
                reason,
                rh,
                rhTotal
            )
        }
        findNavController()
            .navigate(R.id.action_limestoneFragment_to_updateFragment)
    }


    private fun setClickListeners() {
        binding.apply {
            fab.click {
                findNavController().navigate(R.id.action_limestoneFragment_to_addFragment)
            }
        }
    }


    private fun switchVisibility(items: List<MachineMain>) {
        binding.apply {
            if (items.isEmpty()) {
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
            }
        }
    }
}
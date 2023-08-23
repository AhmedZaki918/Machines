package com.example.machines.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.machines.R
import com.example.machines.databinding.FragmentLimestoneBinding
import com.example.machines.ui.adapter.IndividualMachineAdapter
import com.example.machines.utils.Constants.machineMains
import com.example.machines.utils.click
import com.example.machines.utils.drawScreenHeader
import com.example.machines.utils.fakeData


class LimestoneFragment : Fragment() {

    private lateinit var binding: FragmentLimestoneBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLimestoneBinding.inflate(inflater, container, false)
        binding.header.drawScreenHeader(getString(R.string.limestone_crusher), this)

        fakeData()
        setClickListeners()
        binding.rvMachineDetails.adapter = IndividualMachineAdapter(machineMains)
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        machineMains.clear()
    }


    private fun setClickListeners() {
        binding.apply {
            fab.click {
                findNavController().navigate(R.id.action_limestoneFragment_to_addFragment)
            }
        }
    }
}
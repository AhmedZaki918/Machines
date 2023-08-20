package com.example.machines.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.machines.data.model.MachineMain
import com.example.machines.data.model.RH
import com.example.machines.data.model.Reason
import com.example.machines.data.model.RunningTime
import com.example.machines.databinding.FragmentClayCrusherBinding
import com.example.machines.ui.adapter.MachinesAdapter
import com.example.machines.utils.Constants.machineMains
import com.example.machines.utils.drawScreenHeader


class ClayCrusherFragment : Fragment() {

    private lateinit var binding: FragmentClayCrusherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClayCrusherBinding.inflate(inflater, container, false)

        binding.header.drawScreenHeader("Clay Crusher", this)
        binding.rvMachines.adapter = MachinesAdapter(machineMains)

        return binding.root
    }
}
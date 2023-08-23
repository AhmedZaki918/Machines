package com.example.machines.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.machines.databinding.FragmentClayCrusherBinding
import com.example.machines.ui.adapter.IndividualMachineAdapter
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
        return binding.root
    }
}
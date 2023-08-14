package com.example.machines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.machines.databinding.FragmentClayCrusherBinding


class ClayCrusherFragment : Fragment() {

    private lateinit var binding: FragmentClayCrusherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClayCrusherBinding.inflate(inflater, container, false)
        return binding.root
    }
}
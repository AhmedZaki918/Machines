package com.example.machines.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.machines.R
import com.example.machines.databinding.FragmentHomeBinding
import com.example.machines.utils.click
import com.example.machines.utils.drawScreenHeader
import com.example.machines.utils.fakeData


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        binding.header.apply {
            ivGoBack.visibility = INVISIBLE
            tvRhTotalLabel.visibility = INVISIBLE
            tvRhTotal.visibility = INVISIBLE
        }

        binding.apply {
            header.drawScreenHeader("Home", this@HomeFragment)
            cvLimestoneCrusher.click {
                findNavController().navigate(R.id.action_homeFragment_to_limestoneFragment)
            }
            cvClayCrusher.click {
                findNavController().navigate(R.id.action_homeFragment_to_clayCrusherFragment)
            }
        }

        return binding.root
    }
}
package com.example.machines.ui.main

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.machines.R
import com.example.machines.databinding.FragmentHomeBinding
import com.example.machines.utils.click
import com.example.machines.utils.drawScreenHeader
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)



        binding.header.apply {
            ivGoBack.visibility = INVISIBLE
            tvRhTotalLabel.visibility = INVISIBLE
            tvRhHours.visibility = INVISIBLE
            tvRhMinutes.visibility = INVISIBLE
            tvSeparator.visibility = INVISIBLE
            tvRhDiffLabel.visibility = INVISIBLE
            tvRhHoursDiff.visibility = INVISIBLE
            tvRhMinutesDiff.visibility = INVISIBLE
            tvSeparatorDiff.visibility = INVISIBLE
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
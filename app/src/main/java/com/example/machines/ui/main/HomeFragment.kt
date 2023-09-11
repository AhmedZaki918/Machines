package com.example.machines.ui.main

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.machines.R
import com.example.machines.data.local.Constants.CEMENT_MILL_1_STATUS_KEY
import com.example.machines.data.local.Constants.CEMENT_MILL_2_STATUS_KEY
import com.example.machines.data.local.Constants.CEMENT_MILL_3_STATUS_KEY
import com.example.machines.data.local.Constants.CLAY_CRUSHER_STATUS_KEY
import com.example.machines.data.local.Constants.KILN_STATUS_KEY
import com.example.machines.data.local.Constants.LIMESTONE_STATUS_KEY
import com.example.machines.data.local.Constants.RAW_MILL_STATUS_KEY
import com.example.machines.data.local.RunningStatus
import com.example.machines.databinding.FragmentHomeBinding
import com.example.machines.utils.UserPreferences
import com.example.machines.utils.click
import com.example.machines.utils.drawScreenHeader
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var userPreferences: UserPreferences

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        userPreferences = UserPreferences(requireContext())
        binding.header.drawScreenHeader(getString(R.string.home), this@HomeFragment)

        machinesRunningStatus()
        switchVisibility()
        setClickListeners()
        return binding.root
    }


    private fun setClickListeners() {
        binding.apply {
            cvLimestoneCrusher.click {
                findNavController().navigate(R.id.action_homeFragment_to_limestoneFragment)
            }
            cvClayCrusher.click {
                findNavController().navigate(R.id.action_homeFragment_to_clayCrusherFragment)
            }
            cvRawMill.click {
                findNavController().navigate(R.id.action_homeFragment_to_rawMillFragment)
            }
            cvKiln.click {
                findNavController().navigate(R.id.action_homeFragment_to_kilnFragment)
            }
            cvCementMill1.click {
                findNavController().navigate(R.id.action_homeFragment_to_cementMillOneFragment)
            }
            cvCementMill2.click {
                findNavController().navigate(R.id.action_homeFragment_to_cementMillTwoFragment)
            }
            cvCementMill3.click {
                findNavController().navigate(R.id.action_homeFragment_to_cementMillThreeFragment)
            }
            cvAllMachines.click {
                findNavController().navigate(R.id.action_homeFragment_to_reportFragment)
            }
        }
    }


    private fun machinesRunningStatus() {
        userPreferences.apply {
            updateRunningStatus(
                retrieveData(LIMESTONE_STATUS_KEY),
                binding.ivLimestoneStatus
            )
            updateRunningStatus(
                retrieveData(CLAY_CRUSHER_STATUS_KEY),
                binding.ivClayStatus
            )
            updateRunningStatus(
                retrieveData(RAW_MILL_STATUS_KEY),
                binding.ivRawMillStatus
            )
            updateRunningStatus(
                retrieveData(KILN_STATUS_KEY),
                binding.ivKilnStatus
            )
            updateRunningStatus(
                retrieveData(CEMENT_MILL_1_STATUS_KEY),
                binding.ivCement1Status
            )
            updateRunningStatus(
                retrieveData(CEMENT_MILL_2_STATUS_KEY),
                binding.ivCement2Status
            )
            updateRunningStatus(
                retrieveData(CEMENT_MILL_3_STATUS_KEY),
                binding.ivCement3Status
            )
        }
    }


    private fun switchVisibility() {
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
    }


    private fun updateRunningStatus(
        currentStatus: String?,
        ivMachineStatus: ImageView
    ) {
        when (currentStatus) {
            RunningStatus.NORMAL.value -> changeImageViewColor(ivMachineStatus, R.color.green)
            RunningStatus.NO_STOP.value -> changeImageViewColor(ivMachineStatus, R.color.yellow)
            else -> changeImageViewColor(ivMachineStatus, R.color.red)
        }
    }

    private fun changeImageViewColor(
        ivMachineStatus: ImageView,
        color: Int
    ) {
        ivMachineStatus.imageTintList = AppCompatResources
            .getColorStateList(requireContext(), color)
    }
}
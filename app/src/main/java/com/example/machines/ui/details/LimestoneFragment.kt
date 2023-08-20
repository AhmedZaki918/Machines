package com.example.machines.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.machines.R
import com.example.machines.databinding.FragmentLimestoneBinding
import com.example.machines.utils.click
import com.example.machines.utils.drawScreenHeader


class LimestoneFragment : Fragment() {

    private lateinit var binding: FragmentLimestoneBinding
    private var counter = 2
    private var startTime = ""
    private var updateMode = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLimestoneBinding.inflate(inflater, container, false)
        binding.header.drawScreenHeader(getString(R.string.limestone_crusher), this)
        setClickListeners()
        return binding.root
    }


    private fun setClickListeners() {
        binding.apply {
            //updateItem(details)
            fab.click {
                findNavController().navigate(R.id.action_limestoneFragment_to_addFragment)
            }
        }
    }


//    private fun updateItem(layout: DetailsBinding, updateMode: Boolean = false) {
//        layout.apply {
//            var plusCounter = 1
//
//            ivAddTimeAuto.click {
//                if (plusCounter == 1) {
//                    addStartTime(layout,updateMode)
//                    plusCounter++
//                } else {
//                    plusCounter = 1
//                    addStopTime(layout,updateMode)
//                }
//            }
//            ivClear.click {
//                plusCounter = 1
//                clear(layout)
//            }
//        }
//    }


//    private fun addStartTime(
//        layout: DetailsBinding,
//        updateMode: Boolean
//    ) {
//        // Update ui
//        startTime = currentTime()
//        layout.tvStartTime.text = startTime
//        val reasonInput = layout.etReason.text.toString().trim()
//
//        if (updateMode) {
//            // update database
//        } else {
//            // Add new item to database
//            val runningTime = RunningTime(startTime, "00:00")
//            val reason = Reason(reasonInput)
//            val machine = MachineMain(1,getString(R.string.limestone_crusher), runningTime, reason)
//            machineMains.add(machine)
//        }
//    }


//    private fun addStopTime(
//        layout: DetailsBinding,
//        updateMode: Boolean
//    ) {
//        layout.tvEndTime.text = currentTime()
//        val different = differenceBetweenTime(startTime, currentTime())
//        layout.tvRh.text = different.hours + ":" + different.minutes + ":" + different.seconds
//
//        // Update existing item to database
//
//    }


//    private fun clear(layout: DetailsBinding) {
//        layout.apply {
//            tvStartTime.text = RESET
//            tvEndTime.text = RESET
//            tvRh.text = R_H_RESET
//        }
//    }
}
package com.example.machines.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.machines.databinding.DetailsBinding
import com.example.machines.databinding.FragmentLimestoneBinding
import com.example.machines.utils.Constants.RESET
import com.example.machines.utils.Constants.R_H_RESET
import com.example.machines.utils.click
import com.example.machines.utils.currentTime
import com.example.machines.utils.differenceBetweenTime
import com.example.machines.utils.drawScreenHeader


class LimestoneFragment : Fragment() {

    private lateinit var binding: FragmentLimestoneBinding
    private var counter = 2
    private var startTime = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLimestoneBinding.inflate(inflater, container, false)
        binding.header.drawScreenHeader("Limestone Crusher", this)
        setClickListeners()
        return binding.root
    }


    private fun setClickListeners() {
        binding.apply {
            updateItem(details)
            updateItem(details2)
            updateItem(details3)
            updateItem(details4)
            updateItem(details5)
            updateItem(details6)
            updateItem(details7)
            updateItem(details8)
            fab.click { addNewItem() }
        }
    }


    private fun updateItem(layout: DetailsBinding) {
        layout.apply {
            var plusCounter = 1

            ivAddTimeAuto.click {
                if (plusCounter == 1) {
                    startTime = currentTime()

                    tvStartTime.text = "------->"
                    plusCounter++
                } else {
                    plusCounter = 1
                    tvEndTime.text = currentTime()

                    val different = differenceBetweenTime(startTime, currentTime())
                    tvRh.text = different.hours + ":" + different.minutes + ":" + different.seconds
                }
            }

            ivClear.click {
                plusCounter = 1
                tvStartTime.text = RESET
                tvEndTime.text = RESET
                tvRh.text = R_H_RESET
            }
        }
    }


    private fun readSavedItemsViaAdd(counter: Int) {
        binding.apply {
            when (counter) {
                3 -> {
                    details3.root.visibility = VISIBLE
                }

                4 -> {
                    details3.root.visibility = VISIBLE
                    details4.root.visibility = VISIBLE
                }

                5 -> {
                    details3.root.visibility = VISIBLE
                    details4.root.visibility = VISIBLE
                    details5.root.visibility = VISIBLE
                }
            }
        }
    }

    private fun addNewItem() {
        binding.apply {
            when (counter) {
                2 -> {
                    counter++
                    details3.root.visibility = VISIBLE
                }

                3 -> {
                    counter++
                    details4.root.visibility = VISIBLE
                }

                4 -> {
                    counter++
                    details5.root.visibility = VISIBLE
                }

                5 -> {
                    counter++
                    details6.root.visibility = VISIBLE
                }

                6 -> {
                    counter++
                    details7.root.visibility = VISIBLE
                }

                7 -> {
                    counter++
                    details8.root.visibility = VISIBLE
                }
            }
        }
    }
}
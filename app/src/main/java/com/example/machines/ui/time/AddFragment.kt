package com.example.machines.ui.time

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.machines.R
import com.example.machines.data.local.Constants.DEFAULT_VALUE
import com.example.machines.data.local.Constants.EMPTY
import com.example.machines.data.local.Constants.RUNNING
import com.example.machines.data.local.Constants.R_H_RESET
import com.example.machines.data.model.MachineMain
import com.example.machines.databinding.FragmentAddBinding
import com.example.machines.ui.limestone.LimestoneViewModel
import com.example.machines.utils.MachineUtils.changeThumbTint
import com.example.machines.utils.click
import com.example.machines.utils.currentTime
import com.example.machines.utils.drawScreenHeader
import com.example.machines.utils.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var viewModel: LimestoneViewModel
    private var counter = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[LimestoneViewModel::class.java]
        binding.header.drawScreenHeader(getString(R.string.start_time), this)
        setClickListeners()
        return binding.root
    }


    private fun setClickListeners() {
        binding.apply {
            header.apply {
                tvRhTotalLabel.visibility = View.INVISIBLE
                tvRhTotal.visibility = View.INVISIBLE
            }
            btnSet.click { setTime() }
            switchShow.click {
                if (switchShow.isChecked) requireContext().changeThumbTint(R.color.blue, switchShow)
                else requireContext().changeThumbTint(R.color.offWhite, switchShow)
            }
            btnAdd.click { saveTime() }
        }
    }


    private fun setTime() {
        binding.apply {
            if (counter == 1) {
                tvStartTime.text = currentTime()
                btnSet.text = getString(R.string.undo)
                counter++
            } else {
                tvStartTime.text = DEFAULT_VALUE
                btnSet.text = getString(R.string.set)
                counter--
            }
        }
    }

    private fun saveTime() {
        val startTime = binding.tvStartTime.text
        val runningStatus = binding.switchShow.isChecked

        if (startTime == DEFAULT_VALUE && !runningStatus) {
            requireContext().toast(getString(R.string.one_field_is_required))
        } else {
            if (runningStatus) addOneItem(RUNNING)
            else addOneItem(startTime.toString())
        }
    }


    private fun addOneItem(startTime: String) {
        viewModel.addLimestone(
            MachineMain(
                0,
                startTime,
                DEFAULT_VALUE,
                EMPTY,
                R_H_RESET,
                DEFAULT_VALUE
            )
        )
        findNavController().navigateUp()
    }
}
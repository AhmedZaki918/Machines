package com.example.machines.ui.time

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.machines.R
import com.example.machines.databinding.FragmentUpdateBinding
import com.example.machines.utils.Constants
import com.example.machines.utils.MachineUtils.changeThumbTint
import com.example.machines.utils.click
import com.example.machines.utils.currentTime
import com.example.machines.utils.drawScreenHeader


class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private var counter = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        binding.header.drawScreenHeader(getString(R.string.stop_time), this)
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
        }
    }


    private fun setTime() {
        binding.apply {
            if (counter == 1) {
                tvStopTime.text = currentTime()
                btnSet.text = getString(R.string.undo)
                counter++
            } else {
                tvStopTime.text = Constants.RESET
                btnSet.text = getString(R.string.set)
                counter--
            }
        }
    }
}
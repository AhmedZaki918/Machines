package com.example.machines.ui.time

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.example.machines.R
import com.example.machines.databinding.FragmentAddBinding
import com.example.machines.utils.Constants.RESET
import com.example.machines.utils.click
import com.example.machines.utils.currentTime
import com.example.machines.utils.drawScreenHeader


class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private var counter = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)

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
            btnSet.click { setTime()}
            switchShow.click {
                if (switchShow.isChecked) changeThumbTint(R.color.blue)
                else changeThumbTint(R.color.offWhite)
            }
        }
    }

    private fun changeThumbTint(color: Int) {
        binding.switchShow.thumbTintList = AppCompatResources
            .getColorStateList(requireContext(), color)
    }

    private fun setTime() {
        binding.apply {
            if (counter == 1){
                tvStartTime.text = currentTime()
                btnSet.text = getString(R.string.undo)
                counter++
            } else {
                tvStartTime.text = RESET
                btnSet.text = getString(R.string.set)
                counter--
            }
        }
    }
}
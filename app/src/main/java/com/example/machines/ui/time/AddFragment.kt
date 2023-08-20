package com.example.machines.ui.time

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.machines.R
import com.example.machines.databinding.FragmentAddBinding
import com.example.machines.utils.drawScreenHeader


class AddFragment : Fragment() {


    private lateinit var binding: FragmentAddBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        binding.header.drawScreenHeader(getString(R.string.add), this)

        binding.header.apply {
            tvRhTotalLabel.visibility = View.INVISIBLE
            tvRhTotal.visibility = View.INVISIBLE
        }

        return binding.root
    }
}
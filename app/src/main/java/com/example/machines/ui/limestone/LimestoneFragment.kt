package com.example.machines.ui.limestone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.room.Dao
import androidx.room.Insert
import com.example.machines.R
import com.example.machines.data.local.MachineDao
import com.example.machines.databinding.FragmentLimestoneBinding
import com.example.machines.ui.adapter.IndividualMachineAdapter
import com.example.machines.utils.click
import com.example.machines.utils.drawScreenHeader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LimestoneFragment : Fragment() {

    private lateinit var binding: FragmentLimestoneBinding
    private lateinit var viewModel: LimestoneViewModel

    @Inject
    lateinit var dao: MachineDao


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLimestoneBinding.inflate(inflater, container, false)
        binding.header.drawScreenHeader(getString(R.string.limestone_crusher), this)

        viewModel = ViewModelProvider(this)[LimestoneViewModel::class.java]
        viewModel.getAllLimestoneItems().observe(viewLifecycleOwner) { items ->

            binding.apply {
                if (items.isEmpty()){
                    rvMachineDetails.visibility = INVISIBLE
                    tvNoData.visibility = VISIBLE
                    ivNoData.visibility = VISIBLE
                    tvStart.visibility = INVISIBLE
                    tvEnd.visibility = INVISIBLE
                    tvRh.visibility = INVISIBLE
                } else {
                    rvMachineDetails.visibility = VISIBLE
                    tvStart.visibility = VISIBLE
                    tvEnd.visibility = VISIBLE
                    tvRh.visibility = VISIBLE
                    tvNoData.visibility = INVISIBLE
                    ivNoData.visibility = INVISIBLE
                }
            }


            binding.rvMachineDetails.adapter = IndividualMachineAdapter(items,dao)
        }

        setClickListeners()
        return binding.root
    }


    private fun setClickListeners() {
        binding.apply {
            fab.click {
                findNavController().navigate(R.id.action_limestoneFragment_to_addFragment)
            }
        }
    }
}
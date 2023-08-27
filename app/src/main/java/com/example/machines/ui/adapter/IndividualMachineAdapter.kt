package com.example.machines.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.machines.R
import com.example.machines.data.local.Constants.machine
import com.example.machines.data.local.MachineDao
import com.example.machines.data.model.MachineMain
import com.example.machines.databinding.DetailsBinding
import com.example.machines.utils.click
import com.example.machines.utils.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IndividualMachineAdapter(
    private val data: List<MachineMain>,
    val dao: MachineDao
) :
    RecyclerView.Adapter<IndividualMachineAdapter.MachinesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MachinesViewHolder {
        return MachinesViewHolder(
            DetailsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MachinesViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class MachinesViewHolder(
        private val binding: DetailsBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: MachineMain) {
            binding.apply {
                tvStartTime.text = currentItem.startTime
                tvEndTime.text = currentItem.stopTime
                tvRh.text = currentItem.rh
                tvReason.text = currentItem.reason

                ivAddStopTime.click {
                    // Save item in Constants Object class
                    currentItem.apply {
                        machine = MachineMain(
                            id,
                            startTime,
                            stopTime,
                            reason,
                            rh,
                            rhTotal
                        )
                    }
                    ivAddStopTime.findNavController()
                        .navigate(R.id.action_limestoneFragment_to_updateFragment)
                }

                ivClear.click {
                    CoroutineScope(Dispatchers.IO).launch {
                        dao.deleteItem(currentItem)
                    }
                }
            }
        }
    }
}
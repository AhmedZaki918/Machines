package com.example.machines.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.machines.R
import com.example.machines.data.local.MachineDao
import com.example.machines.data.model.MachineMain
import com.example.machines.databinding.DetailsBinding
import com.example.machines.utils.OnItemClick
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IndividualMachineAdapter(
    private val data: List<MachineMain>,
    val dao: MachineDao,
    val onItemClick: OnItemClick
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
        private val binding: DetailsBinding
    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.cvMachine.setOnClickListener(this)
            binding.ivClear.setOnClickListener(this)
        }

        private var machine: MachineMain? = null

        fun bind(currentItem: MachineMain) {
            machine = currentItem
            binding.apply {
                tvStartTime.text = currentItem.startTime
                tvEndTime.text = currentItem.stopTime
                tvRh.text = currentItem.rh
                tvReason.text = currentItem.reason
            }
        }

        override fun onClick(item: View) {
            if (item.id == R.id.iv_clear) {
                CoroutineScope(Dispatchers.IO).launch {
                    dao.deleteItem(machine!!)
                }
            } else if (item.id == R.id.cv_machine) {
                onItemClick.onClicked(machine!!)
            }
        }
    }
}
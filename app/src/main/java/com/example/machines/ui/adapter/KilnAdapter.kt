package com.example.machines.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.machines.R
import com.example.machines.data.model.KilnMachine
import com.example.machines.databinding.DetailsBinding
import com.example.machines.utils.OnItemClick

class KilnAdapter(
    val data: List<KilnMachine>,
    val onItemClick: OnItemClick
) :
    RecyclerView.Adapter<KilnAdapter.KilnViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KilnViewHolder {
        return KilnViewHolder(
            DetailsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: KilnViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class KilnViewHolder(
        val binding: DetailsBinding,
    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.cvMachine.setOnClickListener(this)
            binding.ivClear.setOnClickListener(this)
        }

        var machine: KilnMachine? = null

        fun bind(currentItem: KilnMachine) {
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
                onItemClick.onDeleted(machine)
            } else if (item.id == R.id.cv_machine) {
                onItemClick.onClicked(machine)
            }
        }
    }
}
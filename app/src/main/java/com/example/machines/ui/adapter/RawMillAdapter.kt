package com.example.machines.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.machines.R
import com.example.machines.data.model.RawMillMachine
import com.example.machines.databinding.DetailsBinding
import com.example.machines.utils.OnItemClick

class RawMillAdapter(
    val data: List<RawMillMachine>,
    val onItemClick: OnItemClick
) :
    RecyclerView.Adapter<RawMillAdapter.RawMillViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RawMillViewHolder {
        return RawMillViewHolder(
            DetailsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RawMillViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class RawMillViewHolder(
        val binding: DetailsBinding,
    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.cvMachine.setOnClickListener(this)
            binding.ivClear.setOnClickListener(this)
        }

        var machine: RawMillMachine? = null

        fun bind(currentItem: RawMillMachine) {
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
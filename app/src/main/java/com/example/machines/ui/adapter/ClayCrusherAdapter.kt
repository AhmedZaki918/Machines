package com.example.machines.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.machines.R
import com.example.machines.data.model.ClayCrusherMachine
import com.example.machines.databinding.DetailsBinding
import com.example.machines.utils.OnItemClick

class ClayCrusherAdapter(
    private val data: List<ClayCrusherMachine>,
    val onItemClick: OnItemClick
) :
    RecyclerView.Adapter<ClayCrusherAdapter.ClayCrusherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClayCrusherViewHolder {
        return ClayCrusherViewHolder(
            DetailsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ClayCrusherViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class ClayCrusherViewHolder(
        private val binding: DetailsBinding
    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.cvMachine.setOnClickListener(this)
            binding.ivClear.setOnClickListener(this)
        }

        private var machine: ClayCrusherMachine? = null

        fun bind(currentItem: ClayCrusherMachine) {
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
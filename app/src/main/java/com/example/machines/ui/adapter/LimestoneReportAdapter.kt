package com.example.machines.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.machines.data.model.LimestoneMachine
import com.example.machines.databinding.ListItemReportBinding

class LimestoneReportAdapter(
    val data: List<LimestoneMachine>,
) :
    RecyclerView.Adapter<LimestoneReportAdapter.LimestoneReportViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LimestoneReportViewHolder {
        return LimestoneReportViewHolder(
            ListItemReportBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: LimestoneReportViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class LimestoneReportViewHolder(
        val binding: ListItemReportBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: LimestoneMachine) {
            binding.apply {
                tvStartTimeReport.text = currentItem.startTime
                tvEndTimeReport.text = currentItem.stopTime
                tvReasonReport.text = currentItem.reason
                tvRhReport.text = currentItem.rh
            }
        }
    }
}
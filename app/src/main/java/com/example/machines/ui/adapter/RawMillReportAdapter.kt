package com.example.machines.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.machines.data.model.RawMillMachine
import com.example.machines.databinding.ListItemReportBinding

class RawMillReportAdapter(
    val data: List<RawMillMachine>,
) :
    RecyclerView.Adapter<RawMillReportAdapter.RawMillReportViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RawMillReportViewHolder {
        return RawMillReportViewHolder(
            ListItemReportBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RawMillReportViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class RawMillReportViewHolder(
        val binding: ListItemReportBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: RawMillMachine) {
            binding.apply {
                tvStartTimeReport.text = currentItem.startTime
                tvEndTimeReport.text = currentItem.stopTime
                tvReasonReport.text = currentItem.reason
                tvRhReport.text = currentItem.rh
            }
        }
    }
}
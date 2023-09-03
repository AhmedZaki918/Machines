package com.example.machines.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.machines.data.model.ClayCrusherMachine
import com.example.machines.databinding.ListItemReportBinding

class ClayCrusherReportAdapter(
    val data: List<ClayCrusherMachine>,
) :
    RecyclerView.Adapter<ClayCrusherReportAdapter.ClayCrusherReportViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClayCrusherReportViewHolder {
        return ClayCrusherReportViewHolder(
            ListItemReportBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ClayCrusherReportViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class ClayCrusherReportViewHolder(
        val binding: ListItemReportBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: ClayCrusherMachine) {
            binding.apply {
                tvStartTimeReport.text = currentItem.startTime
                tvEndTimeReport.text = currentItem.stopTime
                tvReasonReport.text = currentItem.reason
                tvRhReport.text = currentItem.rh
            }
        }
    }
}
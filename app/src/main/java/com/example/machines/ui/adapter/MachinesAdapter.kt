package com.example.machines.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.machines.data.model.MachineMain
import com.example.machines.databinding.ListItemFullReportBinding

class MachinesAdapter(
    private val data: List<MachineMain>,
) :
    RecyclerView.Adapter<MachinesAdapter.MachinesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MachinesViewHolder {
        return MachinesViewHolder(
            ListItemFullReportBinding.inflate(
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
        private val binding: ListItemFullReportBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: MachineMain) {
            binding.apply {
                tvMachineName.text = currentItem.machineName
                itemOne.tvStartTimeReport.text = currentItem.runningTime.startTime1
                itemOne.tvEndTimeReport.text = currentItem.runningTime.stopTime1
                itemOne.tvReasonReport.text = currentItem.reason?.reason1
                tvRhFullReport.text = currentItem.rhTotal

                itemTwo.tvStartTimeReport.text = currentItem.runningTime.startTime2
                itemTwo.tvEndTimeReport.text = currentItem.runningTime.stopTime2
                itemTwo.tvReasonReport.text = currentItem.reason?.reason2

                itemThree.tvStartTimeReport.text = currentItem.runningTime.startTime3
                itemThree.tvEndTimeReport.text = currentItem.runningTime.stopTime3
                itemThree.tvReasonReport.text = currentItem.reason?.reason3

                itemFour.tvStartTimeReport.text = currentItem.runningTime.startTime4
                itemFour.tvEndTimeReport.text = currentItem.runningTime.stopTime4
                itemFour.tvReasonReport.text = currentItem.reason?.reason4

                itemFive.tvStartTimeReport.text = currentItem.runningTime.startTime5
                itemFive.tvEndTimeReport.text = currentItem.runningTime.stopTime5
                itemFive.tvReasonReport.text = currentItem.reason?.reason5
            }
        }
    }
}
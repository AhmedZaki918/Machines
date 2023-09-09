package com.example.machines.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.machines.R
import com.example.machines.data.model.CementMillMachine1
import com.example.machines.databinding.DetailsBinding
import com.example.machines.databinding.ListItemReportBinding
import com.example.machines.utils.OnItemClick

class CementMillOneAdapter(
    val data: List<CementMillMachine1>,
    val onItemClick: OnItemClick? = null,
    private val isReportData: Boolean
):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if (isReportData) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            CementOneViewHolder(
                DetailsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        } else {
            CementOneReportViewHolder(
                ListItemReportBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        }
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isReportData) {
            val cementOneReport = holder as CementOneReportViewHolder
            cementOneReport.bind(data[position])
        } else {
            val cementOne = holder as CementOneViewHolder
            cementOne.bind(data[position])
        }
    }


    inner class CementOneViewHolder(
        val binding: DetailsBinding,
    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        var machine: CementMillMachine1? = null

        init {
            binding.cvMachine.setOnClickListener(this)
            binding.ivClear.setOnClickListener(this)
        }

        fun bind(currentItem: CementMillMachine1) {
            machine = currentItem
            binding.apply {
                tvStartTime.text = currentItem.startTime
                tvEndTime.text = currentItem.stopTime
                tvRh.text = currentItem.rh
                tvReason.text = currentItem.reason
            }
        }

        override fun onClick(item: View) {
            if (item.id == R.id.iv_clear) onItemClick!!.onDeleted(machine)
            else if (item.id == R.id.cv_machine) onItemClick!!.onClicked(machine)
        }
    }

    inner class CementOneReportViewHolder(val binding: ListItemReportBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: CementMillMachine1) {
            binding.apply {
                tvStartTimeReport.text = currentItem.startTime
                tvEndTimeReport.text = currentItem.stopTime
                tvReasonReport.text = currentItem.reason
                tvRhReport.text = currentItem.rh
            }
        }
    }
}
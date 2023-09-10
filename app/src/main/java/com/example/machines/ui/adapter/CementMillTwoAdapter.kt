package com.example.machines.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.machines.R
import com.example.machines.data.model.CementMillMachine2
import com.example.machines.databinding.DetailsBinding
import com.example.machines.databinding.ListItemReportBinding
import com.example.machines.utils.OnItemClick

class CementMillTwoAdapter(
    val data: List<CementMillMachine2>,
    val onItemClick: OnItemClick? = null,
    private val isReportData: Boolean
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun getItemViewType(position: Int): Int {
        return if (isReportData) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            CementTwoViewHolder(
                DetailsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        } else {
            CementTwoReportViewHolder(
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
            val cementTwoReport = holder as CementTwoReportViewHolder
            cementTwoReport.bind(data[position])
        } else {
            val cementTwo = holder as CementTwoViewHolder
            cementTwo.bind(data[position])
        }
    }


    inner class CementTwoViewHolder(
        val binding: DetailsBinding,
    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        var machine: CementMillMachine2? = null

        init {
            binding.cvMachine.setOnClickListener(this)
            binding.ivClear.setOnClickListener(this)
        }

        fun bind(currentItem: CementMillMachine2) {
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


    inner class CementTwoReportViewHolder(val binding: ListItemReportBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: CementMillMachine2) {
            binding.apply {
                tvStartTimeReport.text = currentItem.startTime
                tvEndTimeReport.text = currentItem.stopTime
                tvReasonReport.text = currentItem.reason
                tvRhReport.text = currentItem.rh
            }
        }
    }
}
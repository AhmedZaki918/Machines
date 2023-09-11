package com.example.machines.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.machines.R
import com.example.machines.data.model.CementMillMachine3
import com.example.machines.databinding.DetailsBinding
import com.example.machines.databinding.ListItemReportBinding
import com.example.machines.utils.OnItemClick

class CementMillThreeAdapter(
    val data: List<CementMillMachine3>,
    val onItemClick: OnItemClick? = null,
    private val isReportData: Boolean
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun getItemViewType(position: Int): Int {
        return if (isReportData) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            CementThreeViewHolder(
                DetailsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        } else {
            CementThreeReportViewHolder(
                ListItemReportBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isReportData) {
            val cementThreeReport = holder as CementThreeReportViewHolder
            cementThreeReport.bind(data[position])
        } else {
            val cementThree = holder as CementThreeViewHolder
            cementThree.bind(data[position])
        }
    }


    override fun getItemCount() = data.size


    inner class CementThreeViewHolder(
        val binding: DetailsBinding,
    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        var machine: CementMillMachine3? = null

        init {
            binding.cvMachine.setOnClickListener(this)
            binding.ivClear.setOnClickListener(this)
        }

        fun bind(currentItem: CementMillMachine3) {
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


    inner class CementThreeReportViewHolder(val binding: ListItemReportBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: CementMillMachine3) {
            binding.apply {
                tvStartTimeReport.text = currentItem.startTime
                tvEndTimeReport.text = currentItem.stopTime
                tvReasonReport.text = currentItem.reason
                tvRhReport.text = currentItem.rh
            }
        }
    }
}
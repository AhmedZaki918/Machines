package com.example.machines.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.machines.R
import com.example.machines.data.model.ClayCrusherMachine
import com.example.machines.databinding.DetailsBinding
import com.example.machines.databinding.ListItemReportBinding
import com.example.machines.utils.OnItemClick

class ClayCrusherAdapter(
    private val data: List<ClayCrusherMachine>,
    val onItemClick: OnItemClick? = null,
    private val isReportData: Boolean
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun getItemViewType(position: Int): Int {
        return if (isReportData) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            ClayCrusherViewHolder(
                DetailsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        } else {
            ClayCrusherReportViewHolder(
                ListItemReportBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isReportData) {
            val clayCrusherReport = holder as ClayCrusherReportViewHolder
            clayCrusherReport.bind(data[position])
        } else {
            val clayCrusher = holder as ClayCrusherViewHolder
            clayCrusher.bind(data[position])
        }
    }

    override fun getItemCount() = data.size


    inner class ClayCrusherViewHolder(private val binding: DetailsBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private var machine: ClayCrusherMachine? = null

        init {
            binding.cvMachine.setOnClickListener(this)
            binding.ivClear.setOnClickListener(this)
        }

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
            if (item.id == R.id.iv_clear) onItemClick!!.onDeleted(machine)
            else if (item.id == R.id.cv_machine) onItemClick!!.onClicked(machine)
        }
    }


    inner class ClayCrusherReportViewHolder(val binding: ListItemReportBinding) :
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
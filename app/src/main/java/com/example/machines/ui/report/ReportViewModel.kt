package com.example.machines.ui.report

import androidx.lifecycle.ViewModel
import com.example.machines.data.repository.ReportRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val repo: ReportRepo
) : ViewModel() {

    fun getAllLimestoneReport() = repo.getLimestoneReport()
    fun getAllClayCrusherItems() = repo.getClayCrusherReport()
    fun getAllRawMillItems() = repo.getRawMillReport()
}
package com.example.machines.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.machines.data.model.MachineMain
import com.example.machines.databinding.HeaderBinding
import com.example.machines.ui.DifferentInTime
import com.example.machines.utils.Constants.TIME_FORMAT
import com.example.machines.utils.Constants.machineMains
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.abs

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.toast(resourceId: Int) {
    Toast.makeText(this, resourceId, Toast.LENGTH_SHORT).show()
}


fun View.click(block: () -> Unit) {
    this.setOnClickListener {
        block()
    }
}

fun HeaderBinding.drawScreenHeader(
    screenTitle: String,
    fragment: Fragment
) {
    tvScreenName.text = screenTitle
    ivGoBack.click {
        fragment.findNavController().navigateUp()
    }
}



fun differenceBetweenTime(time1: String, time2: String): DifferentInTime {
    val simpleDateFormat = SimpleDateFormat(TIME_FORMAT, Locale.ENGLISH)
    val date1 = simpleDateFormat.parse(time1)
    val date2 = simpleDateFormat.parse(time2)
    val differenceInMilliSeconds = abs(date2!!.time - date1!!.time)

    val hours = (differenceInMilliSeconds / (60 * 60 * 1000) % 24)
    val minutes = differenceInMilliSeconds / (60 * 1000) % 60
    val seconds = differenceInMilliSeconds / 1000 % 60

    return DifferentInTime(hours.toString(), minutes.toString(), seconds.toString())
}


fun currentTime(): String {
    return SimpleDateFormat(TIME_FORMAT, Locale.ENGLISH).format(Date())
}


fun fakeData(){
    machineMains.add(
        MachineMain(1,"","10:00","14:30",false,"","4:30")
    )
    machineMains.add(
        MachineMain(2,"","15:00","17:00",false,"over heat","2:00")
    )
    machineMains.add(
        MachineMain(3,"","----->","20:30",true,"","5:30")
    )
    machineMains.add(
        MachineMain(4,"","21:00","23:00",false,"","2:00")
    )
    machineMains.add(
        MachineMain(5,"","16:00","17:00",false,"broken in machine","3:20")
    )
}
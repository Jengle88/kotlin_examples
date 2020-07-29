package com.example.worldtime

import android.app.TimePickerDialog
import android.os.Bundle
import android.app.Dialog
import android.content.DialogInterface
import android.widget.TextView
import java.util.Calendar
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class TimePickerFragment : DialogFragment(),
        TimePickerDialog.OnTimeSetListener {
    private lateinit var calendar: Calendar
    internal var hournow = 0
    internal var minutenow = 0
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        calendar = Calendar.getInstance()
        var hour = calendar.get(Calendar.HOUR_OF_DAY)
        var minute = calendar.get(Calendar.MINUTE)
        return TimePickerDialog(
            activity,
            this,
            hour,
            minute,
            true
        )
    }
    fun rightTime(hourOfDay: Int, minute: Int) :String
    {
        val hourset = (hourOfDay + 24) % 24
        return "${if(hourset < 10) "0"+hourset.toString() else hourset} : ${if(minute < 10) "0"+minute.toString() else minute}"
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        val tv:TextView = activity?.findViewById(R.id.user_time) as TextView
        hournow = hourOfDay
        minutenow = minute
        tv.text = "Hour : Minute\n${rightTime((hourOfDay + 24) % 24, minute)}"
    }

    override fun onCancel(dialog: DialogInterface) {
        //Toast.makeText(activity,"Picker Canceled",Toast.LENGTH_SHORT).show()
        super.onCancel(dialog)
    }

}
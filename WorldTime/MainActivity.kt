package com.example.worldtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val newFragm = TimePickerFragment()
        user_time.setOnClickListener {
            newFragm.show(supportFragmentManager,"Time Picker")
        }
        selectzone.setSelection(4)
        showtime.setOnClickListener {
            //Toast.makeText(applicationContext,"${selectzone.selectedItem}",Toast.LENGTH_SHORT).show()
            var user_zone:String = selectzone.selectedItem.toString().replaceFirst(':','.')
            mosc_time.text = newFragm.rightTime(newFragm.hournow + (3 - user_zone.toDouble().toInt()),newFragm.minutenow)
            par_time.text  = newFragm.rightTime(newFragm.hournow + (2 - user_zone.toDouble().toInt()),newFragm.minutenow)
            dub_time.text  = newFragm.rightTime(newFragm.hournow + (4 - user_zone.toDouble().toInt()),newFragm.minutenow)
            lon_time.text  = newFragm.rightTime(newFragm.hournow + (1 - user_zone.toDouble().toInt()),newFragm.minutenow)
        }
    }
}
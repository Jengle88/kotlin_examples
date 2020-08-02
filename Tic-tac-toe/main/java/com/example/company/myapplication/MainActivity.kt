package com.example.company.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.*

class MainActivity : AppCompatActivity(){

    var cnt0 = 0
    var cntX = 0
    private fun checkWin()
    {
        if(abs(cnt0 - cntX) > 1)
            status.text = "Invalid"
        else
        {
            when
            {
                spinner11.selectedItem == spinner12.selectedItem && spinner12.selectedItem == spinner13.selectedItem && spinner11.selectedItem != ""-> status.text = "${spinner11.selectedItem} won"
                spinner11.selectedItem == spinner21.selectedItem && spinner21.selectedItem == spinner31.selectedItem && spinner11.selectedItem != ""-> status.text = "${spinner11.selectedItem} won"
                spinner11.selectedItem == spinner22.selectedItem && spinner22.selectedItem == spinner33.selectedItem && spinner11.selectedItem != ""-> status.text = "${spinner11.selectedItem} won"
                spinner13.selectedItem == spinner23.selectedItem && spinner23.selectedItem == spinner33.selectedItem && spinner13.selectedItem != ""-> status.text = "${spinner13.selectedItem} won"
                spinner31.selectedItem == spinner32.selectedItem && spinner32.selectedItem == spinner33.selectedItem && spinner31.selectedItem != ""-> status.text = "${spinner31.selectedItem} won"
                spinner31.selectedItem == spinner22.selectedItem && spinner22.selectedItem == spinner13.selectedItem && spinner31.selectedItem != ""-> status.text = "${spinner31.selectedItem} won"
                spinner12.selectedItem == spinner22.selectedItem && spinner22.selectedItem == spinner32.selectedItem && spinner12.selectedItem != ""-> status.text = "${spinner12.selectedItem} won"
                spinner21.selectedItem == spinner22.selectedItem && spinner22.selectedItem == spinner23.selectedItem && spinner21.selectedItem != ""-> status.text = "${spinner21.selectedItem} won"
                cntX + cnt0 == 9 -> status.text = "Draw"
                else -> status.text = ""
            }
        }
    }
    val mnoj : Array<CharArray> = arrayOf(charArrayOf(' ',' ',' '),charArrayOf(' ',' ',' '), charArrayOf(' ',' ',' '))
    var cntAct = 0
    var allow = false
    private fun edit(spin : Spinner, i :Int, j:Int)
    {
        when(spin.selectedItem)
        {
            "" -> {if(mnoj[i][j] == 'X') cntX-- else if(mnoj[i][j] == '0') cnt0--; mnoj[i][j] = ' '}
            "0" ->
            {
                when(mnoj[i][j])
                {
                    'X' -> {cntX--; cnt0++}
                    ' ' -> cnt0++
                }
                mnoj[i][j] = '0'
            }
            "X" ->
            {
                when(mnoj[i][j])
                {
                    '0' -> {cnt0--;cntX++}
                    ' ' -> cntX++
                }
                mnoj[i][j] = 'X'
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinner11.onItemSelectedListener = object : OnItemSelectedListener
        {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                edit(spinner11,0,0)
                checkWin()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        spinner12.onItemSelectedListener = object : OnItemSelectedListener
        {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                edit(spinner12,0,1)
                checkWin()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        spinner13.onItemSelectedListener = object : OnItemSelectedListener
        {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                edit(spinner13,0,2)
                checkWin()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        spinner21.onItemSelectedListener = object : OnItemSelectedListener
        {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                edit(spinner21,1,0)
                checkWin()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        spinner22.onItemSelectedListener = object : OnItemSelectedListener
        {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                edit(spinner22,1,1)
                checkWin()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        spinner23.onItemSelectedListener = object : OnItemSelectedListener
        {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                edit(spinner23,1,2)
                checkWin()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        spinner31.onItemSelectedListener = object : OnItemSelectedListener
        {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                edit(spinner31,2,0)
                checkWin()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        spinner32.onItemSelectedListener = object : OnItemSelectedListener
        {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                edit(spinner32,2,1)
                checkWin()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        spinner33.onItemSelectedListener = object : OnItemSelectedListener
        {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                edit(spinner33,2,2)
                checkWin()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }
}

package com.example.company.myapplication

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import kotlin.math.sqrt
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private fun isNum(str: CharSequence): Boolean {
        try {
            str.toString().toDouble()
        } catch (NumberFormatException: Exception) {
            return false
        }
        return str.last() != '.'
    }
    fun EditText.isEmpty(): Boolean {
        return this.text.isEmpty()
    }
    private fun calcRoots()
    {
        val a: Double = if (aValue.isEmpty()) 1.0 else aValue.text.toString().toDouble()
        val b: Double = bValue.text.toString().toDouble()
        val c: Double = cValue.text.toString().toDouble()
        when {
            a == 0.0 && b == 0.0 && c == 0.0 -> {isSolutionExist.text = "Any number"; x1Value.setText(""); x2Value.setText("")}
            a == 0.0 && b == 0.0 && c != 0.0 -> {isSolutionExist.text = "No real roots"; x1Value.setText(""); x2Value.setText("")}
            a == 0.0 && b != 0.0 -> { isSolutionExist.text = "One root"; x1Value.setText("${if(-c / b == -0.0) 0.0 else -c / b}"); x2Value.setText("")}
            a != 0.0 -> {
                val D = b * b - 4 * a * c
                when {
                    D < 0 -> { isSolutionExist.text = "No real roots"; x1Value.setText(""); x2Value.setText("")}
                    D == 0.0 -> {
                        isSolutionExist.text = "One root"; x1Value.setText("${if(-b / (2 * a) == -0.0) 0.0 else -b / (2 * a)}"); x2Value.setText("${if(-b / (2 * a) == -0.0) 0.0 else -b / (2 * a)}")
                    }
                    D > 0 -> {
                        isSolutionExist.text = "Two roots"; x1Value.setText("${(-b + sqrt(D)) / (2 * a)}"); x2Value.setText("${(-b - sqrt(D)) / (2 * a)}")
                    }
                }
            }
        }
        Log.d("VALUE","TW = ${isSolutionExist.text}")

    }
    var pre_x2 = false
    private fun calcCoef()
    {
        val x1 = x1Value.text.toString().toDouble()
        pre_x2 = true
        val x2 = if(x2Value.isEmpty()) x1Value.text.toString().toDouble() else x2Value.text.toString().toDouble()
        aValue.setText("1.0")
        bValue.setText("${-(x1+x2)}")
        cValue.setText("${x1*x2}")
        x2Value.setText(x2.toString())
        pre_x2 = false
        if(x1 == x2)
            isSolutionExist.text = "One roots"
        else
            isSolutionExist.text = "Two roots"
        Log.d("VALUE","TW = ${isSolutionExist.text}")
    }

    var abc = false
    var xx = false
    var editMode = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        aValue.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                Log.d("VALUE","A = $p0")
                if(editMode) return
                if(!xx && (aValue.isEmpty() || isNum(aValue.text)) && isNum(bValue.text) && isNum(cValue.text))
                {
                    abc = true
                     calcRoots()
                    abc = false
                }
                else if(!xx)
                    isSolutionExist.text = "error"
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        bValue.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                Log.d("VALUE","B = $p0")
                if(editMode) return
                if(!xx && (aValue.isEmpty() || isNum(aValue.text)) && isNum(bValue.text) && isNum(cValue.text))
                {
                    abc = true
                    calcRoots()
                    abc = false
                }
                else if(!xx)
                    isSolutionExist.text = "error"

            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        cValue.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                Log.d("VALUE","C = $p0")
                if(editMode) return
                if(!xx && (aValue.isEmpty() || isNum(aValue.text)) && isNum(bValue.text) && isNum(cValue.text))
                {
                    abc = true
                    calcRoots()
                    abc = false
                }
                else if(!xx)
                    isSolutionExist.text = "error"
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        x1Value.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                Log.d("VALUE","X1 = $p0")
                if(editMode) return
                if(!abc && isNum(x1Value.text) && (isNum(x2Value.text) || x2Value.isEmpty()))
                {
                    xx = true
                    calcCoef()
                    xx = false
                }
                else if(!abc)
                    isSolutionExist.text = "error"
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        x2Value.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                Log.d("VALUE","X2 = $p0")
                if(pre_x2)
                    return
                if(editMode) return
                if(!abc && isNum(x1Value.text) && (isNum(x2Value.text) || x2Value.isEmpty()))
                {
                    xx = true
                    calcCoef()
                    xx = false
                }
                else if(!abc)
                    isSolutionExist.text = "error"
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        editorbut.setOnClickListener {
            editMode = !editMode
            if(editMode)
                Toast.makeText(applicationContext,"Edit mode ON",Toast.LENGTH_SHORT).show()
            else {
                Toast.makeText(applicationContext, "Edit mode OFF", Toast.LENGTH_SHORT).show()
                when
                {
                    !aValue.isEmpty()  -> aValue.setText(aValue.text.toString() + "")
                    !bValue.isEmpty()  -> bValue.setText(bValue.text.toString() + "")
                    !cValue.isEmpty()  -> cValue.setText(cValue.text.toString() + "")
                    !x1Value.isEmpty() -> x1Value.setText(x1Value.text.toString() + "")
                    !x2Value.isEmpty() -> x2Value.setText(x2Value.text.toString() + "")
                }
            }
        }
        editorbut.setOnLongClickListener {
            Toast.makeText(applicationContext,"Stop computation and start the edit mode. Tap again to change the mode.",Toast.LENGTH_LONG).show()
            return@setOnLongClickListener true
        }
        Log.d("VALUE","NEXT...................................")
    }

}

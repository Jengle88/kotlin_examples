package com.example.calculator

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigInteger
import kotlin.math.abs
import kotlin.math.log10


class MainActivity : AppCompatActivity() {

    private fun Context.copyToClipboard(): Boolean {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("fromAns",ans.text)
        clipboard.setPrimaryClip(clip)
        return true
    }
    fun ret() : Boolean
    {
        return true
    }


    private fun checkType() : Boolean
    {
        var good1 = true
        var good2 = true
        for (i in arg1.text.indices)
        {
            if(!arg1.text[i].isDigit() && (i != 0 || arg1.text[i] != '-'&& arg1.text[i] != '+' && i == 0))
                good1 = false
        }
        for (i in arg2.text.indices)
        {
            if(!arg2.text[i].isDigit() && (i != 0 || arg2.text[i] != '-' && arg2.text[i] != '+' && i == 0))
                good2 = false
        }
        return arg1.text.isNotEmpty() && arg2.text.isNotEmpty() && good1 && good2
    }
    private fun checkDivByZero(): Boolean
    {
        return arg2.text.toString().toBigInteger() == 0.toBigInteger()
    }
    private fun checkSize(res: BigInteger) :Boolean
    {
        return log10(abs(res.toDouble())) <= 26
    }


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add.setOnClickListener{
            if(checkType())
            {
                val res :BigInteger = arg1.text.toString().toBigInteger() + arg2.text.toString().toBigInteger()
                if(checkSize(res))
                    ans.text = "$res"
                else
                    ans.text = "Very big answer"
            }
            else
                ans.text = "Input Error"
        }
        subtr.setOnClickListener {
            if(checkType())
            {
                val res :BigInteger = arg1.text.toString().toBigInteger() - arg2.text.toString().toBigInteger()
                if(checkSize(res))
                    ans.text = "$res"
                else
                    ans.text = "Very big answer"
            }
            else
                ans.text = "Input Error"
        }
        mul.setOnClickListener {
            if(checkType())
            {
                val res :BigInteger = arg1.text.toString().toBigInteger() * arg2.text.toString().toBigInteger()
                if(checkSize(res))
                    ans.text = "$res"
                else
                    ans.text = "Very big answer"
            }
            else
                ans.text = "Input Error"
        }
        div.setOnClickListener {
            if(checkType())
            {
                if(checkDivByZero())
                    ans.text = "Divide by zero"
                else
                {
                    val res: BigInteger = arg1.text.toString().toBigInteger() / arg2.text.toString().toBigInteger()
                    if(checkSize(res))
                        ans.text = "$res"
                    else
                        ans.text = "Very big answer"
                }
            }
            else
                ans.text = "Input Error"
        }
        ans.setOnLongClickListener {
            copyToClipboard()
            Toast.makeText(applicationContext, "Answer have been copied!", Toast.LENGTH_SHORT).show()
            ret()

        }
    }
}
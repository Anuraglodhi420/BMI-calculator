package com.example.bmicalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weight = findViewById<EditText>(R.id.edittext_weight)
        val height=findViewById<EditText>(R.id.edittext_height)
        val button=findViewById<Button>(R.id.btn)

        button.setOnClickListener {
            val w = weight.text.toString()
            val h=height.text.toString()
            if(validate(w,h)) {
                val bmi = w.toFloat() / ((h.toFloat() / 100) * (h.toFloat() / 100))
                val res = String.format("%.2f", bmi).toFloat()
                displayResult(res)
            }
        }
    }

    private  fun validate(weight:String?,height:String?):Boolean{
        return  when{
            weight.isNullOrEmpty() ->{
                Toast.makeText(this,"Weight is empty",Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() ->{
                Toast.makeText(this,"Height is empty",Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun displayResult(res:Float){
        val bmivalue = findViewById<TextView>(R.id.textView_result)
        val bmiinfo=findViewById<TextView>(R.id.textview2_result)
        val bmimoreinfo=findViewById<TextView>(R.id.textview3_result)

        bmivalue.text=res.toString()
        bmimoreinfo.text="(Normal range is from 18.5 to 24.9)"

        var resulttext=""
        var color=0

        when{
            res<18.50 ->{
                resulttext="Underweight"
               color= R.color.under
            }
            res in 18.50..24.99 ->{
                resulttext="Healthy"
                color=R.color.normal
            }
            res in 25.00..29.99 ->{
                resulttext="Overweight"
                color=R.color.over
            }
            res >29.99 ->{
                resulttext="Obese"
                color=R.color.obese
            }
        }
    bmiinfo.setTextColor(ContextCompat.getColor(this,color))
        bmiinfo.text=resulttext

    }
}
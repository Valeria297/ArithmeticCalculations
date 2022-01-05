package com.example.calculator11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.calculator11.result
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doSmth()
    }

    private fun doSmth () {
        bt0.setOnClickListener { setTextFields("0") }
        bt1.setOnClickListener { setTextFields("1") }
        bt2.setOnClickListener { setTextFields("2") }
        bt3.setOnClickListener { setTextFields("3") }
        bt4.setOnClickListener { setTextFields("4") }
        bt5.setOnClickListener { setTextFields("5") }
        bt6.setOnClickListener { setTextFields("6") }
        bt7.setOnClickListener { setTextFields("7") }
        bt8.setOnClickListener { setTextFields("8") }
        bt9.setOnClickListener { setTextFields("9") }
        minus_bt.setOnClickListener { setTextFields("-") }
        plus_bt.setOnClickListener { setTextFields("+") }
        multiply_bt.setOnClickListener { setTextFields("*") }
        div_bt.setOnClickListener { setTextFields("/") }
        bracket1_bt.setOnClickListener { setTextFields("(") }
        bracket2_bt.setOnClickListener { setTextFields(")") }
        point_bt.setOnClickListener { setTextFields(".") }

        all_clear_bt.setOnClickListener {
            expression.text = ""
            result.text = ""
        }

        back_bt.setOnClickListener {
            val str = expression.text.toString()
            if (str.isNotEmpty())
                expression.text = str.substring(0, str.length - 1)
            result.text = ""
        }

        equal_bt.setOnClickListener {
            try {
                val parsAndCalc = ParsingAndCalculating()
                val line = expression.text.toString()

                if (!parsAndCalc.checkString(line)) {
                    Snackbar.make(
                        root_layout,
                        "You have entered incorrect data",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }

                if (parsAndCalc.calculate(line) == 0.0) {
                    Snackbar.make(
                        root_layout,
                        "You can't divide by zero",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }

                result.text = parsAndCalc.calculate(line).toString()
            } catch (e: Exception) {
                Log.d("Error", "message: ${e.message}")
            }
        }
    }

    private fun setTextFields(str: String) {
        if (result.text.isNotEmpty()) {
            expression.text = result.text
            result.text = ""
        }
        expression.append(str)
    }

}
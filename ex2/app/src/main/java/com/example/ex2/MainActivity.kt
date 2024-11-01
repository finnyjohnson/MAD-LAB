package com.example.ex2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var number1EditText: EditText
    private lateinit var number2EditText: EditText
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        number1EditText = findViewById(R.id.number1EditText)
        number2EditText = findViewById(R.id.number2EditText)
        resultTextView = findViewById(R.id.resultTextView)

        val buttons = listOf(
            findViewById<Button>(R.id.addButton),
            findViewById<Button>(R.id.subtractButton),
            findViewById<Button>(R.id.multiplyButton),
            findViewById<Button>(R.id.divideButton)
        )

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener { performOperation(getOperatorForIndex(index)) }
        }
    }

    private fun getOperatorForIndex(index: Int): Char {
        return when (index) {
            0 -> '+'
            1 -> '-'
            2 -> '*'
            3 -> '/'
            else -> throw IllegalArgumentException("Invalid button index")
        }
    }

    private fun performOperation(operator: Char) {
        val num1 = number1EditText.text.toString().toDoubleOrNull()
        val num2 = number2EditText.text.toString().toDoubleOrNull()

        if (num1 != null && num2 != null) {
            val result = when (operator) {
                '+' -> num1 + num2
                '-' -> num1 - num2
                '*' -> num1 * num2
                '/' -> if (num2 != 0.0) num1 / num2 else {
                    resultTextView.text = "Error: Division by zero"
                    return
                }
                else -> Double.NaN
            }

            resultTextView.text = if (!result.isNaN()) "Result: $result" else "Error"
        } else {
            resultTextView.text = "Invalid input"
        }
    }
}
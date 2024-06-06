package com.example.mathforkidproject

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var questionText: TextView
    private lateinit var answerInput: EditText
    private lateinit var resultText: TextView
    private lateinit var startButton: Button
    private lateinit var submitButton: Button
    private lateinit var tryAgainButton: Button
    private lateinit var operationSpinner: Spinner

    private var num1: Int = 0
    private var num2: Int = 0
    private var selectedOperation: String = "+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionText = findViewById(R.id.question_text)
        answerInput = findViewById(R.id.answer_input)
        resultText = findViewById(R.id.result_text)
        startButton = findViewById(R.id.start_button)
        submitButton = findViewById(R.id.submit_button)
        tryAgainButton = findViewById(R.id.try_again_button)
        operationSpinner = findViewById(R.id.operation_spinner)

        val operations = arrayOf("+", "-", "*", "/")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, operations)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        operationSpinner.adapter = adapter

        operationSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedOperation = operations[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedOperation = "+"
            }
        }

        startButton.setOnClickListener {
            generateQuestion()
            questionText.visibility = View.VISIBLE
            answerInput.visibility = View.VISIBLE
            submitButton.visibility = View.VISIBLE
        }

        submitButton.setOnClickListener {
            val answer = answerInput.text.toString().toIntOrNull()
            if (answer == result()) {
                resultText.text = "Correct!"
                resultText.setTextColor(resources.getColor(android.R.color.holo_green_dark))
            } else {
                resultText.text = "Wrong!!!"
                resultText.setTextColor(resources.getColor(android.R.color.holo_red_dark))
            }
            resultText.visibility = View.VISIBLE
            tryAgainButton.visibility = View.VISIBLE
        }

        tryAgainButton.setOnClickListener {
            generateQuestion()
            answerInput.text.clear()
            resultText.visibility = View.GONE
            tryAgainButton.visibility = View.GONE
        }
    }

    private fun generateQuestion() {
        num1 = Random.nextInt(1, 10)
        num2 = Random.nextInt(1, 10)
        questionText.text = "$num1 $selectedOperation $num2"
    }

    private fun result(): Int? {
        return when (selectedOperation) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> if (num2 != 0) num1 / num2 else null
            else -> null
        }
    }
}

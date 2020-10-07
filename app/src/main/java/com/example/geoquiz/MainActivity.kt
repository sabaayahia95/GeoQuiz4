package com.example.geoquiz

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    private var currentIndex = 0
    private fun toast(messageResID: Int) {
        Toast.makeText(this, messageResID, Toast.LENGTH_SHORT).show()
    }

    private fun checkAnswer(userPressedTrue: Boolean) {
        val answerIstrue = questionBank[currentIndex].answer
        val messageResId = if (userPressedTrue == answerIstrue) R.string.correct_toast else R.string.incorrect_toast
        toast(messageResId)
    }

    private fun updateQuestion() {
        val question = questionBank[currentIndex].textResId
        question_text_view.setText(question)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        true_button?.setOnClickListener { v -> checkAnswer(true) }

        false_button?.setOnClickListener { v -> checkAnswer(false) }

        next_button?.setOnClickListener{ v ->
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        question_text_view?.setOnClickListener{ v ->
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        previous_button?.setOnClickListener { v ->
            if (currentIndex == 0) {
                currentIndex = questionBank.size
            }

            currentIndex = (currentIndex - 1) % questionBank.size
            if (currentIndex < 0) {
                currentIndex = questionBank.size
            }
            updateQuestion()
        }

        updateQuestion()

    }
}

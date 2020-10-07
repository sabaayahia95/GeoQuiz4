package com.example.geoquiz

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


private val TAG = "MainActivity"
private const val KEY_INDEX = "index"


class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView
    private lateinit var prevButton: Button
    private lateinit var scoretextview :TextView




    private val questionBank = listOf(
        Question(R.string.question_australia, true,2),
        Question(R.string.question_oceans, true,2),
        Question(R.string.question_mideast, false,3),
        Question(R.string.question_africa, false,3),
        Question(R.string.question_americas, true,2),
        Question(R.string.question_asia, true,2)
    )
    private var currentIndex = 0
    private fun toast(messageResID: Int) {
        Toast.makeText(this, messageResID, Toast.LENGTH_SHORT).show()
    }

    private fun checkAnswer(userPressedTrue: Boolean) {
        val answerIstrue = questionBank[currentIndex].answer
        val messageResId = if (userPressedTrue == answerIstrue) R.string.correct_toast else R.string.incorrect_toast
        toast(messageResId)

        trueButton.isEnabled = false
        falseButton.isEnabled = false
    }

    private fun updateQuestion() {
        val question = questionBank[currentIndex].textResId
        question_text_view.setText(question)

        if(questionBank[currentIndex].answered <= 1){
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        }
        else{
            trueButton.isEnabled = true
            falseButton.isEnabled = true
        }

        if(showFinalScore() != 0){
            val scorePercent =Math.round((showFinalScore().toFloat() / questionBank.size.toFloat()) * 100.0f)
            Toast.makeText(this, "Your Final Score = " + showFinalScore() +"\n "
                    +scorePercent + "%", Toast.LENGTH_SHORT)
                .show()
        }



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton= findViewById(R.id.previous_button)
        questionTextView = findViewById(R.id.question_text_view)
         scoretextview= findViewById(R.id.score_text_view)
        updateQuestion()
        true_button?.setOnClickListener {  checkAnswer(true) }
        updateQuestion()
        false_button?.setOnClickListener { checkAnswer(false) }
        updateQuestion()
        next_button?.setOnClickListener{
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        question_text_view?.setOnClickListener{
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        previous_button?.setOnClickListener {
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

    private fun showFinalScore() : Int
    {
        var mScore = 0
        for ( n in questionBank )
        {
            if (n.answered==2) {
                mScore = 0
                break
            }
            else {
                mScore+= n.answered
            }
        }
        return mScore
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG , "onStart() called $currentIndex")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG , "onPause() called $currentIndex")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG , "onRestart() called $currentIndex")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG , "onResume() called $currentIndex")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG , "onStop() called $currentIndex")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG ,"onDestroy() called" )
    }
}

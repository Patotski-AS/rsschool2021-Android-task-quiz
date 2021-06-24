package com.rsschool.quiz.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.rsschool.quiz.R

abstract class BaseActivity : AppCompatActivity() {

    private var currentTheme = START_THEME

    override fun onCreate(savedInstanceState: Bundle?) {

        currentTheme = PreferenceManager.getDefaultSharedPreferences(this).getInt(KEY_THEME, START_THEME)

        super.onCreate(savedInstanceState)
    }

    protected fun setTheme() {
        setTheme(currentTheme)
    }

    protected fun switchTheme(questionNumber : Int) {
        currentTheme = when(questionNumber) {
            0 -> R.style.Theme_Quiz_First
            1 -> R.style.Theme_Quiz_Second
            2 -> R.style.Theme_Quiz_Three
            3 -> R.style.Theme_Quiz_Fourth
            4 -> R.style.Theme_Quiz_Fifth
            else -> R.style.Theme_Quiz
        }


        PreferenceManager.getDefaultSharedPreferences(this).edit().putInt(KEY_THEME, currentTheme).apply()
    }

    companion object {
        private const val KEY_THEME = "Theme"
        private const val START_THEME = R.style.Theme_Quiz
    }
}
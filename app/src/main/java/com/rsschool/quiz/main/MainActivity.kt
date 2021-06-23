package com.rsschool.quiz.main

import android.os.Bundle
import androidx.navigation.findNavController
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.screens.quiz.QuizFragment
import com.rsschool.quiz.screens.start.StartFragment


class MainActivity : BaseActivity(), QuizFragment.OnQuizFragmentListener,StartFragment.OnStartFragmentListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }

    private fun onSetTheme(questionNumber: Int) {
        switchTheme(questionNumber)
        recreate()
    }

    override fun onQuizFragmentListener(questionNumber: Int) {
        onSetTheme(questionNumber)
    }

    override fun onStartFragmentListener(questionNumber: Int) {
        onSetTheme(questionNumber)
    }

}
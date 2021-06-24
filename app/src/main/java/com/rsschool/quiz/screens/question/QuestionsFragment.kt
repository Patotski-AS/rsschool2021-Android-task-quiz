package com.rsschool.quiz.screens.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rsschool.quiz.databinding.FragmentQestionBinding
import com.rsschool.quiz.room.Question

class QuestionsFragment : Fragment() {
    private var _binding: FragmentQestionBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: QuestionAdapter
    private lateinit var questions: List<Question>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQestionBinding.inflate(inflater, container, false)
        val args = QuestionsFragmentArgs.fromBundle(requireArguments())
        questions = args.questions.toList()

        binding.questionList.layoutManager = LinearLayoutManager(requireActivity())
        adapter = QuestionAdapter(questions as ArrayList<Question>)

        binding.apply {
            questionList.adapter = adapter

            toolbar.title = "${questions.size} questions"

            buttonBack.setOnClickListener {
                goToBack()
            }

            toolbar.setNavigationOnClickListener {
                goToBack()
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun goToBack() {
        view?.findNavController()
            ?.navigate(QuestionsFragmentDirections.actionQuestionsFragmentToStartFragment())
    }


}
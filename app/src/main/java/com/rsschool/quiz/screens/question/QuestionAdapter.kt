package com.rsschool.quiz.screens.question

import android.annotation.SuppressLint
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rsschool.quiz.R
import com.rsschool.quiz.room.Question

class QuestionAdapter(
    private val questions: ArrayList<Question>
) : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val question: TextView = itemView.findViewById(R.id.question_item)
        val optionOne: TextView = itemView.findViewById(R.id.option_1)
        val optionTwo: TextView = itemView.findViewById(R.id.option_2)
        val optionThree: TextView = itemView.findViewById(R.id.option_3)
        val optionFour: TextView = itemView.findViewById(R.id.option_4)
        val optionFive: TextView = itemView.findViewById(R.id.option_5)
        val answer: TextView = itemView.findViewById(R.id.answer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
        return QuestionViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val count = holder.adapterPosition
        val question = questions[count]
        holder.question.text = "${count + 1}. " + question.question
        holder.optionOne.text = "1. " + question.answers?.get(0)
        holder.optionTwo.text = "2. " + question.answers?.get(1)
        holder.optionThree.text = "3. " + question.answers?.get(2)
        holder.optionFour.text = "4. " + question.answers?.get(3)
        holder.optionFive.text = "5. " + question.answers?.get(4)
        holder.answer.text = "answer: " + question.rightAnswer
    }

    override fun getItemCount(): Int = questions.size

}
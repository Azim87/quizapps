package com.kubatov.quizapp.presentation.quiz;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kubatov.quizapp.R;
import com.kubatov.quizapp.model.Questions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {
    private List<Questions> arrayList;
    private OnItemClick onItemClick;

    public QuizAdapter(List<Questions> arrayList,
                       OnItemClick onItemClick){
        this.arrayList = arrayList;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View quizView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quiz_viewholder, parent, false);
        return new QuizViewHolder(quizView, onItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        holder.mTextQuizQuestion.setText(arrayList.get(position).getQuestion());
        holder.mTextQuizQuestionAnswer1.setText(arrayList.get(position).getIncorrectAnswers().get(0));
        holder.mTextQuizQuestionAnswer2.setText(arrayList.get(position).getIncorrectAnswers().get(1));
        holder.mTextQuizQuestionAnswer3.setText(arrayList.get(position).getIncorrectAnswers().get(2));
        holder.mTextQuizQuestionAnswer4.setText(arrayList.get(position).getCorrectAnswers());
        Log.d("ololo", "onBindViewHolder: \n"
                + arrayList.get(position).getCategory() + " \n"
                + arrayList.get(position).getQuestion() +" \n"
                + arrayList.get(position).getCorrectAnswers());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {
        QuizAdapter.OnItemClick listener;

        @BindView(R.id.quiz_question) TextView mTextQuizQuestion;
        @BindView(R.id.quiz_question_answer_1) TextView mTextQuizQuestionAnswer1;
        @BindView(R.id.quiz_question_answer_2) TextView mTextQuizQuestionAnswer2;
        @BindView(R.id.quiz_question_answer_3) TextView mTextQuizQuestionAnswer3;
        @BindView(R.id.quiz_question_answer_4) TextView mTextQuizQuestionAnswer4;

        public QuizViewHolder(@NonNull View itemView, QuizAdapter.OnItemClick listener) {
            super(itemView);
            this.listener = listener;
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClick {
        void onClick(int questionPosition, int adapterPosition);
    }
}

package com.kubatov.quizapp.presentation.quiz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kubatov.quizapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View quizView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quiz_viewholder, parent, false);
        return new QuizViewHolder(quizView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.quiz_question) TextView mTextQuizQuestion;
        @BindView(R.id.quiz_question_answer_1) TextView mTextQuizQuestionAnswer1;
        @BindView(R.id.quiz_question_answer_2) TextView mTextQuizQuestionAnswer2;
        @BindView(R.id.quiz_question_answer_3) TextView mTextQuizQuestionAnswer3;
        @BindView(R.id.quiz_question_answer_4) TextView mTextQuizQuestionAnswer4;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

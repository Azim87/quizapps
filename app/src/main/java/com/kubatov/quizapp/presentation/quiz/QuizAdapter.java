package com.kubatov.quizapp.presentation.quiz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kubatov.quizapp.R;
import com.kubatov.quizapp.model.Questions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {
    private List<Questions> mQuestions = new ArrayList<>();
    private OnItemClickListener mListener;

    public QuizAdapter(OnItemClickListener onItemClick) {
        mListener = onItemClick;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View quizView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quiz_viewholder, parent, false);
        return new QuizViewHolder(quizView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        holder.onBind(mQuestions.get(position));
    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

    void setQuestions(List<Questions> questionsList) {
        mQuestions.clear();
        mQuestions.addAll(questionsList);
        notifyDataSetChanged();
    }

    public List<Questions> getListPosition(){
        return mQuestions;
    }


    public class QuizViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemClickListener listener;

        @BindView(R.id.quiz_question)
        TextView mTextQuizQuestion;
        @BindView(R.id.quiz_question_answer_1)
        TextView mTextQuizQuestionAnswer1;
        @BindView(R.id.quiz_question_answer_2)
        TextView mTextQuizQuestionAnswer2;
        @BindView(R.id.quiz_question_answer_3)
        TextView mTextQuizQuestionAnswer3;
        @BindView(R.id.quiz_question_answer_4)
        TextView mTextQuizQuestionAnswer4;


        public QuizViewHolder(@NonNull View itemView, QuizAdapter.OnItemClickListener listener) {
            super(itemView);
            this.listener = listener;
            ButterKnife.bind(this, itemView);
            mTextQuizQuestionAnswer1.setOnClickListener(this);
            mTextQuizQuestionAnswer2.setOnClickListener(this);
            mTextQuizQuestionAnswer3.setOnClickListener(this);
            mTextQuizQuestionAnswer4.setOnClickListener(this);
        }


        public void onBind(Questions questions) {
            mTextQuizQuestion.setText(questions.getQuestion());
            mTextQuizQuestionAnswer1.setText(questions.getAnswers().get(0));
            mTextQuizQuestionAnswer2.setText(questions.getAnswers().get(1));
        }

        @Override
        public void onClick(View v) {

        }
    }

    public interface OnItemClickListener {
        void onAnswerClick(int questionPosition, int answerPosition);
    }
}

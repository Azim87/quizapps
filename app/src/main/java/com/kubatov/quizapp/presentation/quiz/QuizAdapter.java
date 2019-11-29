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
    public final static String MULTIPLE = "multiple";
    public final static String TRUE = "true";
    public final static String BOOLEAN = "boolean";
    private List<Questions> mQuestions = new ArrayList<>();
    private OnItemClickListener mListener;
    private int positionOfAnswer;

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

    public List<Questions> getListPosition() {
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
        @BindView(R.id.quiz_question_boolean_1)
        TextView mTextQuizQuestionBoolean1;
        @BindView(R.id.quiz_question_boolean_2)
        TextView mTextQuizQuestionBoolean2;


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
            if (questions.getType().equals(MULTIPLE)) {
                showMultipleQuestion(questions);
                positionOfAnswer = 0;
            } else {
                hideQuestionContainers();
            }
            if (questions.getType().equals(BOOLEAN)) {
                if (questions.getCorrectAnswers().equals(TRUE)) {
                    positionOfAnswer = R.id.quiz_question_boolean_1;
                } else {
                    positionOfAnswer = R.id.quiz_question_boolean_2;
                }
            }
        }

        private void hideQuestionContainers() {
            mTextQuizQuestionBoolean1.setVisibility(View.INVISIBLE);
            mTextQuizQuestionBoolean2.setVisibility(View.INVISIBLE);
            mTextQuizQuestionAnswer1.setVisibility(View.VISIBLE);
            mTextQuizQuestionAnswer2.setVisibility(View.VISIBLE);
            mTextQuizQuestionAnswer3.setVisibility(View.VISIBLE);
            mTextQuizQuestionAnswer4.setVisibility(View.VISIBLE);
        }

        private void showMultipleQuestion(Questions questions) {
            mTextQuizQuestionAnswer1.setText(questions.getAnswers().get(0));
            mTextQuizQuestionAnswer2.setText(questions.getAnswers().get(1));
            mTextQuizQuestionAnswer3.setText(questions.getAnswers().get(2));
            mTextQuizQuestionAnswer4.setText(questions.getAnswers().get(3));
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.quiz_question_answer_1:
                    listener.onAnswerClick(getAdapterPosition(), 0);
                    break;
                case R.id.quiz_question_answer_2:
                    listener.onAnswerClick(getAdapterPosition(), 1);
                    break;
                case R.id.quiz_question_answer_3:
                    listener.onAnswerClick(getAdapterPosition(), 2);
                    break;
                case R.id.quiz_question_answer_4:
                    listener.onAnswerClick(getAdapterPosition(), 3);
                    break;
            }
        }
    }

    public interface OnItemClickListener {
        void onAnswerClick(int questionPosition, int answerPosition);
    }
}

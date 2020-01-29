package com.kubatov.quizapp.presentation.quiz;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kubatov.quizapp.R;
import com.kubatov.quizapp.model.EType;
import com.kubatov.quizapp.model.Questions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {
    private final static String TRUE = "true";
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

    public List<Questions> getListPosition() {
        return mQuestions;
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final static String YES = "yes";
        private final static String NO = "no";
        private OnItemClickListener listener;

        @BindView(R.id.quiz_question) TextView mTextQuizQuestion;
        @BindView(R.id.quiz_question_answer_1) TextView mTextQuizQuestionAnswer1;
        @BindView(R.id.quiz_question_answer_2) TextView mTextQuizQuestionAnswer2;
        @BindView(R.id.quiz_question_answer_3) TextView mTextQuizQuestionAnswer3;
        @BindView(R.id.quiz_question_answer_4) TextView mTextQuizQuestionAnswer4;
        @BindView(R.id.quiz_question_boolean_1) TextView mTextQuizQuestionBoolean1;
        @BindView(R.id.quiz_question_boolean_2) TextView mTextQuizQuestionBoolean2;
        @BindView(R.id.container_boolean) LinearLayout mContainerBoolean;
        @BindView(R.id.container_multiple) LinearLayout mContainerMultiple;


        private QuizViewHolder(@NonNull View itemView, QuizAdapter.OnItemClickListener listener) {
            super(itemView);
            this.listener = listener;
            ButterKnife.bind(this, itemView);
            mTextQuizQuestionAnswer1.setOnClickListener(this);
            mTextQuizQuestionAnswer2.setOnClickListener(this);
            mTextQuizQuestionAnswer3.setOnClickListener(this);
            mTextQuizQuestionAnswer4.setOnClickListener(this);
            mTextQuizQuestionBoolean1.setOnClickListener(this);
            mTextQuizQuestionBoolean2.setOnClickListener(this);
        }


        private void onBind(Questions questions) {
            mTextQuizQuestion.setText(Html.fromHtml(questions.getQuestion()));
            if (questions.getType().equals(EType.MULTIPLE)) {
                showMultipleQuestion(questions);
                hideQuestionBoolean();
            } else {
                hideQuestionMultiple();
            }
            if (questions.getType().equals(EType.BOOLEAN)) {
                if (questions.getCorrectAnswers().equals(TRUE)) {
                    mTextQuizQuestionBoolean1.setText("Yes");
                } else {
                    mTextQuizQuestionBoolean2.setText("No");
                }
            }
        }

        private void hideQuestionBoolean() {
            mContainerBoolean.setVisibility(View.INVISIBLE);
            mContainerMultiple.setVisibility(View.VISIBLE);
        }

        private void hideQuestionMultiple() {
            mContainerBoolean.setVisibility(View.VISIBLE);
            mContainerMultiple.setVisibility(View.INVISIBLE);
        }

        private void showMultipleQuestion(Questions questions) {
            mTextQuizQuestionAnswer1.setText(Html.fromHtml(questions.getAnswers().get(0)));
            mTextQuizQuestionAnswer2.setText(Html.fromHtml(questions.getAnswers().get(1)));
            mTextQuizQuestionAnswer3.setText(Html.fromHtml(questions.getAnswers().get(2)));
            mTextQuizQuestionAnswer4.setText(Html.fromHtml(questions.getAnswers().get(3)));
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.quiz_question_answer_1:
                case R.id.quiz_question_boolean_1:
                    listener.onAnswerClick(getAdapterPosition(), 0);
                    break;
                case R.id.quiz_question_answer_2:
                case R.id.quiz_question_boolean_2:
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

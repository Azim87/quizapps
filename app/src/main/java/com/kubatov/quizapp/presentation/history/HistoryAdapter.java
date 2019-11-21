package com.kubatov.quizapp.presentation.history;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kubatov.quizapp.R;
import com.kubatov.quizapp.model.HistoryModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private ArrayList<HistoryModel> models = new ArrayList<>();

    public void setHistoryList(ArrayList<HistoryModel> modelList) {
        models.clear();
        models.addAll(modelList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_viewholder, parent, false);
        return new HistoryViewHolder(rootView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.onBind(models.get(position));
    }

    @Override
    public int getItemCount() {
        if (models != null) {
            return models.size();
        } else {
            return 0;
        }
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.category_text_view_history)
        TextView mCategoryTextView;
        @BindView(R.id.difficulty_text_view)
        TextView mDifficultTextView;
        @BindView(R.id.history_time_text_view)
        TextView mTimeTextView;
        @BindView(R.id.correct_answers_text_view)
        TextView mAnswersTextView;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(HistoryModel historyModel) {
            mCategoryTextView.setText("Category: " + historyModel.getCategory());
            mAnswersTextView.setText("Correct answers: " + historyModel.getAnswers());
            mDifficultTextView.setText("Difficulty: " + historyModel.getDifficulty());
            mTimeTextView.setText(historyModel.getTime());
        }
    }
}

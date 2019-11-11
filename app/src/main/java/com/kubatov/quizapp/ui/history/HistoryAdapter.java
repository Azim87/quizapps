package com.kubatov.quizapp.ui.history;

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

    void setHistoryList(ArrayList<HistoryModel> modelList) {
        models = modelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_view_holder, parent, false);
        return new HistoryViewHolder(rootView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.mCategoryTextView.setText("Category: " + models.get(position).getCategory());
        holder.mAnswersTextView.setText("Correct answers: " + models.get(position).getAnswers());
        holder.mDifficultTextView.setText("Difficulty: " + models.get(position).getDifficulty());
        holder.mTimeTextView.setText(models.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        if (models != null) {
            return models.size();
        } else {
            return 0;
        }
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {
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
    }
}

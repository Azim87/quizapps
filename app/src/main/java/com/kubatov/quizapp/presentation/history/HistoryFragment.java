package com.kubatov.quizapp.presentation.history;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kubatov.quizapp.R;
import com.kubatov.quizapp.core.CoreFragment;
import com.kubatov.quizapp.model.HistoryModel;
import com.kubatov.quizapp.model.ShortQuizResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryFragment extends CoreFragment {
    private HistoryViewModel historyViewModel;
    private  HistoryAdapter historyAdapter;


    @BindView(R.id.history_recycle_view)
    RecyclerView historyRecyclerView;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_history;
    }

    @Override
    protected void initViewAfterCreated(View view) {
        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        ButterKnife.bind(this, view);
        initRecycler();
    }

    private void initHistoryViewModel(){
        historyViewModel.shortQuizResult.observe(this, new Observer<List<ShortQuizResult>>() {
            @Override
            public void onChanged(List<ShortQuizResult> shortQuizResults) {
                historyAdapter.setHistoryList(shortQuizResults);
            }
        });
    }

    private void initRecycler() {
        historyAdapter = new HistoryAdapter();
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        historyRecyclerView.setAdapter(historyAdapter);
    }
}

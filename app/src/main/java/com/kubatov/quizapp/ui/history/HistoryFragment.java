package com.kubatov.quizapp.ui.history;

import android.view.View;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kubatov.quizapp.R;
import com.kubatov.quizapp.core.CoreFragment;
import com.kubatov.quizapp.model.HistoryModel;
import com.kubatov.quizapp.ui.main.MainActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryFragment extends CoreFragment {

    @BindView(R.id.history_recycle_view)
    RecyclerView historyRecyclerView;

    private HistoryViewModel mViewModel;
    private HistoryAdapter historyAdapter;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_history;
    }

    @Override
    protected void initViewAfterCreated(View view) {
        ButterKnife.bind(this, view);
        initRecycler();
    }

    private void initRecycler() {
        historyAdapter = new HistoryAdapter();
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        historyRecyclerView.setAdapter(historyAdapter);
        historyAdapter.setHistoryList(setFakeData());
    }

    private ArrayList<HistoryModel> setFakeData() {
        ArrayList<HistoryModel> models = new ArrayList<>();
        models.add(new HistoryModel("Music", "Easy", "12:43", 8));
        models.add(new HistoryModel("VideoGames", "Hard", "22:43", 2));
        models.add(new HistoryModel("Science: Computers", "Medium", "11:45", 4));
        models.add(new HistoryModel("Musics", "Easy", "06:32", 10));
        return models;
    }
}

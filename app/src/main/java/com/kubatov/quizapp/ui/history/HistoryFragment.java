package com.kubatov.quizapp.ui.history;

import android.view.View;

import com.kubatov.quizapp.R;
import com.kubatov.quizapp.core.CoreFragment;

public class HistoryFragment extends CoreFragment {

    private HistoryViewModel mViewModel;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.history_fragment;
    }

    @Override
    protected void initViewAfterCreated(View view) {

    }
}

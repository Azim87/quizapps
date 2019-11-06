package com.kubatov.quizapp.ui.main;

import android.view.View;

import com.kubatov.quizapp.R;
import com.kubatov.quizapp.core.CoreFragment;

public class MainFragment extends CoreFragment {

    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment;
    }

    @Override
    protected void initViewAfterCreated(View view) {

    }
}

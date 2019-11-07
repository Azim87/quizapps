package com.kubatov.quizapp.ui.main;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.kubatov.quizapp.R;
import com.kubatov.quizapp.core.CoreFragment;
import com.kubatov.quizapp.util.MySpinner;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainFragment extends CoreFragment {

    @BindView(R.id.spinner_category)
    Spinner spinnerCategory;
    @BindView(R.id.spinner_difficulty)
    Spinner spinnerDifficulty;

    private String[] category = {"ALL"};
    private String[] difficulty = {"ALL"};

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
        ButterKnife.bind(this, view);
        initCategorySpinner();
        initDiffiSpinner();

    }

    private void initCategorySpinner() {
        MySpinner.show(category, spinnerCategory, getContext());
    }

    private void initDiffiSpinner() {
        MySpinner.show(difficulty, spinnerDifficulty, getContext());
    }
}

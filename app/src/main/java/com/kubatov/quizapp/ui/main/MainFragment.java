package com.kubatov.quizapp.ui.main;

import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.kubatov.quizapp.R;
import com.kubatov.quizapp.core.CoreFragment;
import com.kubatov.quizapp.util.MySpinner;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.apptik.widget.MultiSlider;


public class MainFragment extends CoreFragment {

    @BindView(R.id.spinner_category)
    NiceSpinner spinnerCategory;
    @BindView(R.id.spinner_difficulty)
    NiceSpinner spinnerDifficulty;
    @BindView(R.id.seek_bar_amount)
    MultiSlider mAmountSlider;
    @BindView(R.id.text_view_amount)
    TextView amountTextView;


    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_quiz;
    }

    @Override
    protected void initViewAfterCreated(View view) {
        ButterKnife.bind(this, view);
        initCategorySpinner();
        initDifficultSpinner();


    }

    private void initCategorySpinner() {
        List<String> category = new LinkedList<>(Arrays.asList("ALL"));
        category.add("");
        MySpinner.show(category, spinnerCategory);
    }

    private void initDifficultSpinner() {
        List<String> difficulty = new LinkedList<>(Arrays.asList("ALL"));
        difficulty.add("");
        MySpinner.show(difficulty, spinnerDifficulty);
        getValueFromSeekBar();
    }

    private void getValueFromSeekBar() {
        mAmountSlider.setOnThumbValueChangeListener((multiSlider, thumb, thumbIndex, value) -> amountTextView.setText(String.valueOf(value)));
    }
}

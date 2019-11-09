package com.kubatov.quizapp.ui.main;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kubatov.quizapp.R;
import com.kubatov.quizapp.core.CoreFragment;
import com.kubatov.quizapp.ui.quiz.QuizActivity;
import com.kubatov.quizapp.util.MySpinner;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.apptik.widget.MultiSlider;


public class MainFragment extends CoreFragment implements View.OnClickListener {
    private static final String ANY_DIFFICULTY = "ANY DIFFICULTY";
    private static final String ANY_CATEGORY = "ANY CATEGORY";
    private static final String EASY = "EASY";
    private static final String MEDIUM = "MEDIUM";
    private static final String HARD = "HARD";

    private MainViewModel mViewModel;

    @BindView(R.id.spinner_category)
    NiceSpinner spinnerCategory;
    @BindView(R.id.spinner_difficulty)
    NiceSpinner spinnerDifficulty;
    @BindView(R.id.seek_bar_amount)
    MultiSlider mAmountSlider;
    @BindView(R.id.text_view_amount)
    TextView amountTextView;
    @BindView(R.id.quiz_start_button)
    Button quizStartButton;

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
        quizStartButton.setOnClickListener(this);
    }

    private void initCategorySpinner() {
        List<String> category = new LinkedList<>(Arrays.asList(ANY_CATEGORY));
        category.add("ANIMALS");
        category.add("ART");
        category.add("CELEBRITIES");
        category.add("ENTERTAINMENT: BOARD GAMES");
        category.add("ENTERTAINMENT: BOOKS");
        category.add("ENTERTAINMENT: CARTOON & ANIMATIONS");
        category.add("ENTERTAINMENT: COMICS");
        category.add("ENTERTAINMENT: FILM");
        category.add("ENTERTAINMENT: JAPANESE ANIME & MANGA");
        category.add("GENERAL KNOWLEDGE");
        category.add("ENTERTAINMENT: MUSIC");
        category.add("ENTERTAINMENT: MUSICALS & THEATRES");
        category.add("ENTERTAINMENT: TELEVISION");
        category.add("ENTERTAINMENT: VIDEO GAMES");
        category.add("GEOGRAPHY");
        category.add("HISTORY");
        category.add("MYTHOLOGY");
        category.add("POLITICS");
        category.add("SCIENCE & NATURE");
        category.add("SCIENCE: COMPUTERS");
        category.add("SCIENCE: MATHEMATICS");
        category.add("SCIENCE: GADGETS");
        category.add("SPORTS");
        category.add("VEHICLES");
        MySpinner.show(category, spinnerCategory);
    }

    private void initDifficultSpinner() {
        List<String> difficulty = new LinkedList<>(Arrays.asList(ANY_DIFFICULTY));
        difficulty.add(EASY);
        difficulty.add(MEDIUM);
        difficulty.add(HARD);
        MySpinner.show(difficulty, spinnerDifficulty);
        getValueFromSeekBar();
    }

    private void getValueFromSeekBar() {
        mAmountSlider.getThumb(0).setValue(10).setEnabled(true);
        mAmountSlider.setOnThumbValueChangeListener(new MultiSlider.SimpleChangeListener() {
            @Override
            public void onValueChanged(MultiSlider multiSlider, MultiSlider.Thumb thumb, int thumbIndex, int value) {
                amountTextView.setText(String.valueOf(value));
            }
        });
    }

    @Override
    public void onClick(View v) {
        QuizActivity.start(getContext());
    }
}

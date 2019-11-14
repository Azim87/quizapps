package com.kubatov.quizapp.ui.main;

import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatSeekBar;

import com.kubatov.quizapp.R;
import com.kubatov.quizapp.core.CoreFragment;
import com.kubatov.quizapp.core.SimpleSeekBarChange;
import com.kubatov.quizapp.ui.quiz.QuizActivity;
import com.kubatov.quizapp.util.ViewHelperUtil;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainFragment extends CoreFragment implements View.OnClickListener {
    private static final String ANY_DIFFICULTY = "ANY DIFFICULTY";
    private static final String ANY_CATEGORY = "ANY CATEGORY";
    private static final String EASY = "EASY";
    private static final String MEDIUM = "MEDIUM";
    private static final String HARD = "HARD";
    public static final String SEEK_BAR = "seekbar";
    public static final String DIFF_CATEGORY = "category";
    public static final String DIFF_DIFFICULT = "difficult";
    private MainViewModel mViewModel;

    @BindView(R.id.spinner_category)
    NiceSpinner spinnerCategory;
    @BindView(R.id.spinner_difficulty)
    NiceSpinner spinnerDifficulty;
    @BindView(R.id.seek_bar_amount)
    AppCompatSeekBar mSeekBar;
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
        ViewHelperUtil.show(category, spinnerCategory);
    }

    private void initDifficultSpinner() {
        List<String> difficulty = new LinkedList<>(Arrays.asList(ANY_DIFFICULTY));
        difficulty.add(EASY);
        difficulty.add(MEDIUM);
        difficulty.add(HARD);
        ViewHelperUtil.show(difficulty, spinnerDifficulty);
        getValueFromSeekBar();
    }

    private void getValueFromSeekBar() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mSeekBar.setMin(5);
        }

        mSeekBar.setOnSeekBarChangeListener(new SimpleSeekBarChange() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                super.onProgressChanged(seekBar, progress, fromUser);
                amountTextView.setText(String.valueOf(progress));
            }
        });
    }

    @Override
    public void onClick(View v) {
        QuizActivity.start(
                getContext(),
                String.valueOf(mSeekBar.getProgress()),
                spinnerCategory.getSelectedItem().toString(),
                spinnerDifficulty.getSelectedItem().toString());
    }
}

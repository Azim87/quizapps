package com.kubatov.quizapp.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kubatov.quizapp.R;
import com.kubatov.quizapp.ui.main.MainFragment;
import com.kubatov.quizapp.ui.history.HistoryFragment;
import com.kubatov.quizapp.ui.settings.SettingsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final String MAIN = "main";
    public static final String HISTORY = "history";
    public static final String SETTINGS = "settings";
    public static final int MAIN_FRAG = 0;
    public static final int HISTORY_FRAG = 1;
    public static final int SETTINGS_FRAG = 2;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViewPager();
    }

    private void initViewPager() {
        QuizFragmentAdapter mQuizFragmentAdapter = new QuizFragmentAdapter(getSupportFragmentManager());
        mQuizFragmentAdapter.setFragment(getFragment());
        viewPager.setAdapter(mQuizFragmentAdapter);
        setSlidePages();
    }

    private void setSlidePages() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.bottom_navigation_main:
                    viewPager.setCurrentItem(MAIN_FRAG);
                    setTitle(MAIN);
                    break;
                case R.id.bottom_navigation_history:
                    viewPager.setCurrentItem(HISTORY_FRAG);
                    setTitle(HISTORY);
                    break;
                case R.id.bottom_navigation_settings:
                    viewPager.setCurrentItem(SETTINGS_FRAG);
                    setTitle(SETTINGS);
                    break;
            }
            return true;
        });
        onSlidePage();
    }

    private void onSlidePage() {
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mBottomNavigationView.getMenu().getItem(position).setChecked(true);
                if (viewPager.getCurrentItem() == MAIN_FRAG) {
                    setTitle(MAIN);
                } else if (viewPager.getCurrentItem() == HISTORY_FRAG) {
                    setTitle(HISTORY);
                } else if (viewPager.getCurrentItem() == SETTINGS_FRAG) {
                    setTitle(SETTINGS);
                } else {
                    setTitle(getTitle());
                }
            }
        });
    }

    private List<Fragment> getFragment() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new MainFragment());
        fragmentList.add(new HistoryFragment());
        fragmentList.add(new SettingsFragment());
        return fragmentList;
    }
}

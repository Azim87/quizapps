package com.kubatov.quizapp.ui;

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

    @BindView(R.id.bottom_navigation) BottomNavigationView mBottomNavigationView;
    @BindView(R.id.view_pager) ViewPager viewPager;

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
                    viewPager.setCurrentItem(0);
                    setTitle(MAIN);
                    break;
                case R.id.bottom_navigation_history:
                    viewPager.setCurrentItem(1);
                    setTitle(HISTORY);
                    break;
                case R.id.bottom_navigation_settings:
                    viewPager.setCurrentItem(2);
                    setTitle(SETTINGS);
                    break;
            }
            return false;
        });
        onSlidePage();
    }

    private void onSlidePage() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mBottomNavigationView.getMenu().getItem(position).setChecked(true);

                if (viewPager.getCurrentItem() == 0) {
                    setTitle(MAIN);
                } else if (viewPager.getCurrentItem() == 1) {
                    setTitle(HISTORY);
                } else if (viewPager.getCurrentItem() == 2) {
                    setTitle(SETTINGS);
                } else {
                    setTitle(getTitle());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
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

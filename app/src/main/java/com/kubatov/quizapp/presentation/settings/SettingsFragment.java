package com.kubatov.quizapp.presentation.settings;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.kubatov.quizapp.App;
import com.kubatov.quizapp.R;
import com.kubatov.quizapp.core.CoreFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsFragment extends CoreFragment {
    private SettingsViewModel mViewModel;

    @BindView(R.id.clear_history_text_view)
    TextView mClearHistory;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void initViewAfterCreated(View view) {
        ButterKnife.bind(this, view);
        mViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);

        mViewModel.deleteAllEvent.observe(this, integer -> {
            if (integer == null) return;
            if (integer == 0) Toast.makeText(getContext(), "History is empty", Toast.LENGTH_SHORT).show();
            if (integer > 0) Toast.makeText(getContext(), "History cleared", Toast.LENGTH_SHORT).show();

        });
    }
    @OnClick(R.id.clear_history_text_view)
    void clearHistory(View view){
        mViewModel.clearHistory();
    }
}

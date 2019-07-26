package com.farzam.rest.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.farzam.rest.BR;
import com.farzam.rest.R;
import com.farzam.rest.databinding.ActivitySettingBinding;
import com.farzam.rest.ui.base.BaseActivity;
import com.farzam.rest.ui.start.StartActivity;
import com.farzam.rest.utils.AppConstants;

import javax.inject.Inject;

public class SettingActivity extends BaseActivity<ActivitySettingBinding, SettingViewModel> implements SettingNavigator, AppConstants {

    @Inject
    SettingViewModel mSettingViewModel;

    ActivitySettingBinding mActivitySettingBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mActivitySettingBinding = getViewDataBinding();
        mSettingViewModel.setNavigator(this);
        mSettingViewModel.setActivity(SettingActivity.this);
    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        return intent;
    }

    @Override
    public SettingViewModel getViewModel() {
        return mSettingViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void startStartActivity() {
        if (mActivitySettingBinding.factor.isChecked())
            mSettingViewModel.getDataManager().setPrefKeyFactor(true);
        else
            mSettingViewModel.getDataManager().setPrefKeyFactor(false);

        if (mActivitySettingBinding.order.isChecked())
            mSettingViewModel.getDataManager().setPrefKeyOrder(true);
        else
            mSettingViewModel.getDataManager().setPrefKeyOrder(false);

        if (mActivitySettingBinding.dontUse.isChecked())
            mSettingViewModel.getDataManager().setPrefKeyDontUse(true);
        else
            mSettingViewModel.getDataManager().setPrefKeyDontUse(false);

        if (mActivitySettingBinding.print.isChecked())
            mSettingViewModel.getDataManager().setPrefKeyPrint(true);
        else
            mSettingViewModel.getDataManager().setPrefKeyPrint(false);

        startActivity(StartActivity.getStartIntent(SettingActivity.this));
    }
}

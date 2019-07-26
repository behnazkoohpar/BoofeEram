package com.farzam.rest.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;

import com.farzam.rest.BR;
import com.farzam.rest.R;
import com.farzam.rest.data.DataManager;
import com.farzam.rest.databinding.ActivitySplashBinding;
import com.farzam.rest.ui.base.BaseActivity;
import com.farzam.rest.ui.login.LoginActivity;
import com.farzam.rest.ui.main.MainActivity;
import com.farzam.rest.ui.start.StartActivity;
import com.farzam.rest.utils.AppConstants;
import com.farzam.rest.utils.CommonUtils;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator, AppConstants {

    final Handler handler = new Handler();
    ActivitySplashBinding mActivitySplashBinding;

    @Inject
    SplashViewModel mSplashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySplashBinding = getViewDataBinding();
        mSplashViewModel.setNavigator(this);
        mSplashViewModel.setActivity(SplashActivity.this);
        decideNextActivity();
    }

    @Override
    public SplashViewModel getViewModel() {
        return mSplashViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    public void openStartActivity() {
        Intent intent = StartActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }
    @Override
    public void openMainActivity() {
        Intent intent =MainActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }
    @Override
    public void decideNextActivity() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSplashViewModel.getDataManager().getCurrentUserLoggedInMode()
                        == DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()) {
//                    openStartActivity();
                    openLoginActivity();
                } else {
                    openStartActivity();
                }
            }
        }, 6500);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}

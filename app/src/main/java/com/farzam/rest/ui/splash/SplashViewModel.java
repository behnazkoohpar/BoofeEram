package com.farzam.rest.ui.splash;

import android.app.Activity;
import android.content.pm.PackageInfo;

import com.farzam.rest.R;
import com.farzam.rest.data.DataManager;
import com.farzam.rest.ui.base.BaseViewModel;
import com.farzam.rest.utils.AppConstants;
import com.farzam.rest.utils.CommonUtils;

import retrofit2.Response;

/**
 * behnaz on 10/29/2017.
 */

public class SplashViewModel extends BaseViewModel<SplashNavigator> implements AppConstants {

    Activity mActivity;
    private int version;
    private PackageInfo pInfo;

    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public SplashViewModel(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onResponseSuccess(Object mObject, int requestCode) {
        try {
            Response response = (Response) mObject;
            switch (requestCode) {

            }
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.problem), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void onResponseFailed(String errorMsg, int responseCode, int requestCode) {
        setIsLoading(false);
        CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.api_response_failed), mActivity.getString(R.string.btn_ok), null);
    }

    @Override
    public void onFailedAuth(String errorMsg, int requestCode) {
        setIsLoading(false);
        CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.authentication_failed), mActivity.getString(R.string.btn_ok), null);
    }

}

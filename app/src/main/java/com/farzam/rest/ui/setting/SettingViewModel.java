package com.farzam.rest.ui.setting;

import android.app.Activity;
import android.databinding.ObservableBoolean;

import com.farzam.rest.data.DataManager;
import com.farzam.rest.ui.base.BaseViewModel;
import com.farzam.rest.utils.AppConstants;

public class SettingViewModel extends BaseViewModel<SettingNavigator> implements AppConstants {

    Activity mActivity;

    public SettingViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void onSaveClick() {

        getNavigator().startStartActivity();
    }

    @Override
    public void onResponseSuccess(Object mObject, int requestCode) {

    }

    @Override
    public void onResponseFailed(String errorMsg, int responseCode, int requestCode) {

    }

    @Override
    public void onFailedAuth(String errorMsg, int requestCode) {

    }
}

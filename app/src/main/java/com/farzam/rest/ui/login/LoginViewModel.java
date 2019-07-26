package com.farzam.rest.ui.login;

import android.app.Activity;
import android.content.Context;

import com.farzam.rest.R;
import com.farzam.rest.api.BaseCallback;
import com.farzam.rest.api.ICallApi;
import com.farzam.rest.data.DataManager;
import com.farzam.rest.data.model.api.LoginResponse;
import com.farzam.rest.data.model.api.OrganizationResponse;
import com.farzam.rest.data.model.api.ReceptionResponse;
import com.farzam.rest.data.model.api.base.Data;
import com.farzam.rest.ui.base.BaseViewModel;
import com.farzam.rest.utils.AppConstants;
import com.farzam.rest.utils.CommonUtils;

import java.util.HashMap;
import java.util.List;

import retrofit2.Response;

/**
 * behnaz on 11/3/17.
 */

public class LoginViewModel extends BaseViewModel<LoginNavigator> implements AppConstants {

    Activity mActivity;

    public LoginViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void onLoginClick() {

        getNavigator().login();
    }

    public void openState() {
        getNavigator().openState();
    }

    public void openCity() {
        getNavigator().openCity();
    }

    public void login(ICallApi iCallApi, Context context, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(context, true, iCallApi, getDataManager(), API_CALL_LOGIN, this);
            iCallApi.login(map).enqueue(baseCallback);
            setIsLoading(true);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.not_can_call), null, null);
            e.printStackTrace();
        }
    }

    public void getOrganization(ICallApi iCallApi, LoginActivity context, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(context, true, iCallApi, getDataManager(), API_CALL_ORGANIZATION, this);
            iCallApi.getUnitOrganization(map).enqueue(baseCallback);
            setIsLoading(true);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.not_can_call), null, null);
            e.printStackTrace();
        }

    }

    public void getReception(ICallApi iCallApi, LoginActivity context, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(context, true, iCallApi, getDataManager(), API_CALL_PAZIRESH, this);
            iCallApi.getUnitReception(map).enqueue(baseCallback);
            setIsLoading(true);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.not_can_call), null, null);
            e.printStackTrace();
        }

    }

    @Override
    public void onResponseSuccess(Object mObject, int requestCode) {
        try {
            Response response = (Response) mObject;
            Data data = (Data) response.body();
            if (data.getSettings().getSuccess().equalsIgnoreCase("0")) {
                CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), data.getSettings().getMessage(), null, null);
            } else {
                switch (requestCode) {
                    case API_CALL_LOGIN:
                        List<LoginResponse> loginResponses = data.getData();
                        getNavigator().goToStart(loginResponses);
                        break;

                    case (API_CALL_ORGANIZATION):
                        List<OrganizationResponse> stateResponses = data.getData();
                        getNavigator().setOrganization(stateResponses);
                        break;

                    case (API_CALL_PAZIRESH):
                        List<ReceptionResponse> receptionResponses = data.getData();
                        getNavigator().setReception(receptionResponses);
                        break;
                }
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

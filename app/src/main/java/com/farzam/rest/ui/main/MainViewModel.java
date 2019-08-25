package com.farzam.rest.ui.main;

import android.app.Activity;

import com.farzam.rest.R;
import com.farzam.rest.api.BaseCallback;
import com.farzam.rest.api.ICallApi;
import com.farzam.rest.data.DataManager;
import com.farzam.rest.data.model.api.FoodDetailResponse;
import com.farzam.rest.data.model.api.FoodResponse;
import com.farzam.rest.data.model.api.StuffListResponse;
import com.farzam.rest.data.model.api.base.Data;
import com.farzam.rest.ui.base.BaseViewModel;
import com.farzam.rest.ui.start.StartActivity;
import com.farzam.rest.utils.AppConstants;
import com.farzam.rest.utils.CommonUtils;

import java.util.HashMap;
import java.util.List;

import retrofit2.Response;

/**
 * Created by cmos on 05/04/2018.
 */

public class MainViewModel extends BaseViewModel<MainNavigator> implements AppConstants {

    Activity mActivity;

    public MainViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void onCallSearch() {
        getNavigator().callSearch();
    }

    public void onCallCategory() {
        getNavigator().openCategoryList();
    }

    public void setFactor() {
        if (StartActivity.lockerSelected)
            getNavigator().setFactor();
        if (StartActivity.azadSelected)
            getNavigator().setFactorAzad();
        if (StartActivity.cardSelected)
            getNavigator().setFactorCard();
        if (StartActivity.personSelected)
            getNavigator().setFactorPerson();
    }

    public void getStuffList(ICallApi iCallApi, MainActivity context, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(context, true, iCallApi, getDataManager(), API_CALL_STUFF_LIST, this);
            iCallApi.getAllStuffList(map).enqueue(baseCallback);
            setIsLoading(true);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.not_can_call), null, null);
            e.printStackTrace();
        }
    }

    public void callSetFactor(ICallApi iCallApi, MainActivity context, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(context, true, iCallApi, getDataManager(), API_CALL_SET_FACTOR, this);
            iCallApi.callSetFactor(map).enqueue(baseCallback);
            setIsLoading(true);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.not_can_call), null, null);
            e.printStackTrace();
        }

    }

    public void callSetFactorCard(ICallApi iCallApi, MainActivity context, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(context, true, iCallApi, getDataManager(), API_CALL_SET_FACTOR, this);
            iCallApi.callSetFactorCard(map).enqueue(baseCallback);
            setIsLoading(true);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.not_can_call), null, null);
            e.printStackTrace();
        }

    }

    public void callSetFactorPerson(ICallApi iCallApi, MainActivity context, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(context, true, iCallApi, getDataManager(), API_CALL_SET_FACTOR, this);
            iCallApi.callSetFactorPerson(map).enqueue(baseCallback);
            setIsLoading(true);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.not_can_call), null, null);
            e.printStackTrace();
        }

    }

    public void callSetFactorAzad(ICallApi iCallApi, MainActivity context, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(context, true, iCallApi, getDataManager(), API_CALL_SET_FACTOR_AZAD, this);
            iCallApi.callSetFactorAzad(map).enqueue(baseCallback);
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
                    case API_CALL_STUFF_LIST:
                        List<StuffListResponse> stuffListResponses = data.getData();
                        getNavigator().setStuffList(stuffListResponses);
                        break;
                    case API_CALL_ALL_SPECIAL_OFFER:
                        List<FoodDetailResponse> FoodDetailResponses = data.getData();
                        getNavigator().setSpecialOffer(FoodDetailResponses);
                        break;
                    case API_CALL_SET_FACTOR:
                        CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.factor_ok), null, new CommonUtils.IL() {
                            @Override
                            public void onSuccess() {
                                getNavigator().openStartActivity();
                            }

                            @Override
                            public void onCancel() {
                                getNavigator().openStartActivity();
                            }
                        });

                        break;
                    case API_CALL_SET_FACTOR_AZAD:
                        CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.factor_ok), null, new CommonUtils.IL() {
                            @Override
                            public void onSuccess() {
                                getNavigator().openStartActivity();
                            }

                            @Override
                            public void onCancel() {
                                getNavigator().openStartActivity();
                            }
                        });

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

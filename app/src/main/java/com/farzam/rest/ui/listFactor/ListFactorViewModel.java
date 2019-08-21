package com.farzam.rest.ui.listFactor;

import android.app.Activity;
import android.databinding.ObservableBoolean;

import com.farzam.rest.R;
import com.farzam.rest.api.BaseCallback;
import com.farzam.rest.api.ICallApi;
import com.farzam.rest.data.DataManager;
import com.farzam.rest.data.model.api.ListFactorDetailResponse;
import com.farzam.rest.data.model.api.ListFactorResponse;
import com.farzam.rest.data.model.api.base.Data;
import com.farzam.rest.ui.base.BaseViewModel;
import com.farzam.rest.utils.AppConstants;
import com.farzam.rest.utils.CommonUtils;

import java.util.HashMap;
import java.util.List;

import retrofit2.Response;

public class ListFactorViewModel extends BaseViewModel<ListFactorNavigator> implements AppConstants {

    Activity mActivity;
    public ObservableBoolean all = new ObservableBoolean(true);
    public ObservableBoolean delivered = new ObservableBoolean(false);
    public ObservableBoolean notdelivered = new ObservableBoolean(false);
    public ObservableBoolean alltypeFactor = new ObservableBoolean(true);
    public ObservableBoolean locker = new ObservableBoolean(false);
    public ObservableBoolean withoutReception = new ObservableBoolean(false);
    public ObservableBoolean personel = new ObservableBoolean(false);

    public ListFactorViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void onReportClick() {
        getNavigator().onReportClick();
    }

    public void clickAll() {
        all.set(true);
        delivered.set(false);
        notdelivered.set(false);
        getNavigator().setDelivered(0);
    }

    public void clickDelivered() {
        all.set(false);
        delivered.set(true);
        notdelivered.set(false);
        getNavigator().setDelivered(1);
    }

    public void clickNotDelivered() {
        all.set(false);
        delivered.set(false);
        notdelivered.set(true);
        getNavigator().setDelivered(2);
    }

    public void clickLocker() {
        locker.set(true);
        withoutReception.set(false);
        alltypeFactor.set(false);
        personel.set(false);
        getNavigator().setPersonRecieved(2, 1);
    }

    public void clickWithoutReception() {
        locker.set(false);
        withoutReception.set(true);
        alltypeFactor.set(false);
        personel.set(false);
        getNavigator().setPersonRecieved(2, 2);
    }

    public void clickPersonel() {
        locker.set(false);
        withoutReception.set(false);
        alltypeFactor.set(false);
        personel.set(true);
        getNavigator().setPersonRecieved(1, 2);
    }

    public void clickAllTypeFactor() {
        locker.set(false);
        withoutReception.set(false);
        alltypeFactor.set(true);
        personel.set(false);
        getNavigator().setPersonRecieved(0, 0);
    }

    public void clickFromDate() {
        getNavigator().openFromDateCalendar();
    }

    public void clickToDate() {
        getNavigator().openToDateCalendar();
    }

    public void getListFactor(ICallApi iCallApi, ListFactorActivity context, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(context, true, iCallApi, getDataManager(), API_CALL_LIST_FACTOR, this);
            iCallApi.getListFactor(map).enqueue(baseCallback);
            setIsLoading(true);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.not_can_call), null, null);
            e.printStackTrace();
        }
    }


    public void getListDetailFactor(ICallApi iCallApi, ListFactorActivity context, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(context, true, iCallApi, getDataManager(), API_CALL_LIST_DETAIL_FACTOR, this);
            iCallApi.getListDetailFactor(map).enqueue(baseCallback);
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
                    case API_CALL_LIST_FACTOR:
                        List<ListFactorResponse> listFactorResponses = data.getData();
                        getNavigator().setList(listFactorResponses);
                        break;
                    case API_CALL_LIST_DETAIL_FACTOR:
                        List<ListFactorDetailResponse> listFactorDetailResponses = data.getData();
                        getNavigator().setListDetail(listFactorDetailResponses);
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

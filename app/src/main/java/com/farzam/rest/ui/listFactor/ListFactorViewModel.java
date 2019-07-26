package com.farzam.rest.ui.listFactor;

import android.app.Activity;
import android.database.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.farzam.rest.R;
import com.farzam.rest.api.BaseCallback;
import com.farzam.rest.api.ICallApi;
import com.farzam.rest.data.DataManager;
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
    public ObservableBoolean today = new ObservableBoolean(true);
    public ObservableBoolean month = new ObservableBoolean(false);
    public ObservableBoolean date = new ObservableBoolean(false);
    public ObservableBoolean fromDate = new ObservableBoolean(false);
    public ObservableBoolean fromTime = new ObservableBoolean(false);
    public ObservableBoolean toDate = new ObservableBoolean(false);
    public ObservableBoolean toTime = new ObservableBoolean(false);
    public final ObservableField<String> day = new ObservableField<>();

    public ListFactorViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void clickToday() {
        today.set(true);
        month.set(false);
        date.set(false);
        fromDate.set(false);
        fromTime.set(false);
        toDate.set(false);
        toTime.set(false);
        getNavigator().toUpAnim();
        day.set("روز جاری");
        getNavigator().setToday();
    }

    public void clickMonth() {
        today.set(false);
        month.set(true);
        date.set(false);
        fromDate.set(false);
        fromTime.set(false);
        toDate.set(false);
        toTime.set(false);
        getNavigator().toUpAnim();
        day.set("ماه جاری");
        getNavigator().setMonth();
    }

    public void clickFromDate() {
        today.set(false);
        month.set(false);
        date.set(true);
        fromDate.set(true);
        toDate.set(true);
        fromTime.set(true);
        toTime.set(true);
        day.set("تاریخ درخواستی");
        getNavigator().openFromDateCalendar();
    }

    public void clickFromTime() {
        today.set(false);
        month.set(false);
        date.set(true);
        fromDate.set(true);
        toDate.set(true);
        fromTime.set(true);
        toTime.set(true);
        day.set("تاریخ درخواستی");
        getNavigator().openFromTimeCalendar();
    }

    public void clickToDate() {
        today.set(false);
        month.set(false);
        date.set(true);
        fromDate.set(true);
        toDate.set(true);
        fromTime.set(true);
        toTime.set(true);
        day.set("تاریخ درخواستی");
        getNavigator().openToDateCalendar();
    }

    public void clickToTime() {
        today.set(false);
        month.set(false);
        date.set(true);
        fromDate.set(true);
        toDate.set(true);
        fromTime.set(true);
        toTime.set(true);
        day.set("تاریخ درخواستی");
        getNavigator().openToTimeCalendar();
    }

    public void filterClick() {
        getNavigator().filterClick();

    }

    public void sortClick() {

        getNavigator().toUpAnim();
//        getNavigator().filterClick();

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
                        List<ListFactorResponse> listFactorResponses = data.getData();
                        getNavigator().setList(listFactorResponses);
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

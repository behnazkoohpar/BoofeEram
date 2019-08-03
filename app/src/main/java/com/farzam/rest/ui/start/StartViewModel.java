package com.farzam.rest.ui.start;

import android.app.Activity;
import android.content.Intent;
import android.database.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.farzam.rest.R;
import com.farzam.rest.api.BaseCallback;
import com.farzam.rest.api.ICallApi;
import com.farzam.rest.data.DataManager;
import com.farzam.rest.data.model.api.CardInfoResponse;
import com.farzam.rest.data.model.api.HistoryResponse;
import com.farzam.rest.data.model.api.LockerInfoResponse;
import com.farzam.rest.data.model.api.base.Data;
import com.farzam.rest.ui.base.BaseViewModel;
import com.farzam.rest.ui.main.MainActivity;
import com.farzam.rest.utils.AppConstants;
import com.farzam.rest.utils.CommonUtils;

import java.util.HashMap;
import java.util.List;

import retrofit2.Response;

/**
 * Created by cmos on 07/09/2018.
 */

public class StartViewModel extends BaseViewModel<StartNavigator> implements AppConstants {

    Activity mActivity;

    public ObservableBoolean cart = new ObservableBoolean(false);
    public ObservableBoolean dastband = new ObservableBoolean(true);
    public ObservableBoolean azad = new ObservableBoolean(false);
    public ObservableBoolean personeli = new ObservableBoolean(false);
    public ObservableBoolean showDastband = new ObservableBoolean(true);
    public ObservableBoolean showCart = new ObservableBoolean(false);
    public ObservableBoolean showAzad = new ObservableBoolean(false);
    public ObservableBoolean showPersoneli = new ObservableBoolean(false);

    public StartViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void openMenu() {
        getNavigator().openMenu();
    }

    public void callLockerInfo() {
        getNavigator().callLockerInfo();
    }
    public void callLockerName() {
        getNavigator().callLockerName();
    }
    public void callLockerCard() {
        getNavigator().callLockerCard();
    }

    public void callCardInfo() {
        getNavigator().callCheckCard();
    }

    public void callCardNameFamily() {
        getNavigator().callCardNameFamily();
    }

    public void callCardTel() {
        getNavigator().callCardTel();
    }


    public void callPersonInfo() {
        getNavigator().callPersonInfo();
    }

    public void callPersonNameFamily() {
        getNavigator().callPersonNameFamily();
    }

    public void callPersonCodeGharardad() {
        getNavigator().callPersonCodeGharardad();
    }

    public void getHistoryLocker() {
        getNavigator().getHistory();
    }

    public void getHistoryCard() {
        getNavigator().getHistoryCard();
    }

    public void getHistoryPersoneli() {
        getNavigator().getHistoryPersonli();
    }

    public void showSaleLocker() {
        Intent intent = MainActivity.getStartIntent(mActivity);
        mActivity.startActivity(intent);
    }

    public void showSaleCard() {
        Intent intent = MainActivity.getStartIntent(mActivity);
        mActivity.startActivity(intent);
    }

    public void showSalePersoneli() {
        Intent intent = MainActivity.getStartIntent(mActivity);
        mActivity.startActivity(intent);
    }


    public void callLocker(ICallApi iCallApi, StartActivity context, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(context, true, iCallApi, getDataManager(), API_CALL_LOCKER_INFO, this);
            iCallApi.getLockerInfo(map).enqueue(baseCallback);
            setIsLoading(true);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.not_can_call), null, null);
            e.printStackTrace();
        }
    }

    public void callHistory(ICallApi iCallApi, StartActivity context, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(context, true, iCallApi, getDataManager(), API_CALL_HISTORY, this);
            iCallApi.getHistory(map).enqueue(baseCallback);
            setIsLoading(true);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.not_can_call), null, null);
            e.printStackTrace();
        }
    }

    public void callHistoryCard(ICallApi iCallApi, StartActivity context, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(context, true, iCallApi, getDataManager(), API_CALL_HISTORY2, this);
            iCallApi.getHistoryCard(map).enqueue(baseCallback);
            setIsLoading(true);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.not_can_call), null, null);
            e.printStackTrace();
        }
    }

    public void callHistoryPersoneli(ICallApi iCallApi, StartActivity context, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(context, true, iCallApi, getDataManager(), API_CALL_HISTORY3, this);
            iCallApi.getHistory3(map).enqueue(baseCallback);
            setIsLoading(true);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.not_can_call), null, null);
            e.printStackTrace();
        }
    }

    public void callCard(ICallApi iCallApi, StartActivity context, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(context, true, iCallApi, getDataManager(), API_CALL_CHECK_CARD, this);
            iCallApi.getCardInfo(map).enqueue(baseCallback);
            setIsLoading(true);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.not_can_call), null, null);
            e.printStackTrace();
        }
    }

    public void callPerson(ICallApi iCallApi, StartActivity context, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(context, true, iCallApi, getDataManager(), API_CALL_CHECK_PERSON, this);
            iCallApi.getCardInfo(map).enqueue(baseCallback);
            setIsLoading(true);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.not_can_call), null, null);
            e.printStackTrace();
        }
    }

    public void callNameFamilyPerson(ICallApi iCallApi, StartActivity context, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(context, true, iCallApi, getDataManager(), API_CALL_NAME_FAMILY_PERSON, this);
            iCallApi.callNameFamilyPerson(map).enqueue(baseCallback);
            setIsLoading(true);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.not_can_call), null, null);
            e.printStackTrace();
        }
    }


    public void callPersonNumberLockerCard(ICallApi iCallApi, StartActivity context, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(context, true, iCallApi, getDataManager(), API_CALL_NUMBER_LOCKER_CARD, this);
            iCallApi.callPersonNumberLockerCard(map).enqueue(baseCallback);
            setIsLoading(true);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.not_can_call), null, null);
            e.printStackTrace();
        }
    }

    public void callNameMemberShip(ICallApi iCallApi, StartActivity context, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(context, true, iCallApi, getDataManager(), API_CALL_NAME_MEMBERSHIP, this);
            iCallApi.callNameMemberShip(map).enqueue(baseCallback);
            setIsLoading(true);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.not_can_call), null, null);
            e.printStackTrace();
        }
    }

    public void callTelMemberShip(ICallApi iCallApi, StartActivity context, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(context, true, iCallApi, getDataManager(), API_CALL_TEL_MEMBERSHIP, this);
            iCallApi.callTelMemberShip(map).enqueue(baseCallback);
            setIsLoading(true);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.not_can_call), null, null);
            e.printStackTrace();
        }
    }

    public void callPersonNameAndFamily(ICallApi iCallApi, StartActivity context, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(context, true, iCallApi, getDataManager(), API_CALL_PERSON_NAME_FAMILY, this);
            iCallApi.callPersonNameAndFamily(map).enqueue(baseCallback);
            setIsLoading(true);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), mActivity.getString(R.string.not_can_call), null, null);
            e.printStackTrace();
        }
    }

    public void callCodeGharardad(ICallApi iCallApi, StartActivity context, HashMap<String, String> map) {
        try {
            BaseCallback baseCallback = new BaseCallback(context, true, iCallApi, getDataManager(), API_CALL_CODE_GHARARDAD, this);
            iCallApi.callCodeGharardad(map).enqueue(baseCallback);
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
                CommonUtils.showSingleButtonAlert(mActivity, mActivity.getString(R.string.text_attention), data.getSettings().getMessage(), null, new CommonUtils.IL() {
                    @Override
                    public void onSuccess() {
                        getNavigator().setDataNull();
                    }

                    @Override
                    public void onCancel() {
                        getNavigator().setDataNull();
                    }
                });
            } else {
                switch (requestCode) {
                    case API_CALL_LOCKER_INFO:
                        List<LockerInfoResponse> loginResponses = data.getData();
                        getNavigator().setLockerInfo(loginResponses,0);
                        break;
                    case API_CALL_HISTORY:
                        List<HistoryResponse> historyResponses = data.getData();
                        getNavigator().setHistoryLocker(historyResponses);
                        break;

                    case API_CALL_HISTORY2:
                        List<HistoryResponse> historyResponses2 = data.getData();
                        getNavigator().setHistoryCard(historyResponses2);
                        break;

                    case API_CALL_HISTORY3:
                        List<HistoryResponse> historyResponses3 = data.getData();
                        getNavigator().setHistoryPersoneli(historyResponses3);
                        break;
                    case API_CALL_CHECK_CARD:
                        List<CardInfoResponse> cardInfoResponses = data.getData();
                        getNavigator().setCardInfo(cardInfoResponses,0);
                        break;
                    case API_CALL_CHECK_PERSON:
                        List<CardInfoResponse> personInfoResponses = data.getData();
                        getNavigator().setPersonInfo(personInfoResponses,0);
                        break;
                    case API_CALL_NAME_FAMILY_PERSON:
                        List<LockerInfoResponse> lockerInfoResponses = data.getData();
                        getNavigator().setLockerName(lockerInfoResponses);
                        break;
                    case API_CALL_NUMBER_LOCKER_CARD:
                        List<LockerInfoResponse> loginResponsesCard = data.getData();
                        getNavigator().setLockerCard(loginResponsesCard);
                        break;
                    case API_CALL_NAME_MEMBERSHIP:
                        List<CardInfoResponse> cardInfoResponses1 = data.getData();
                        getNavigator().setCardName(cardInfoResponses1);
                        break;
                    case API_CALL_TEL_MEMBERSHIP:
                        List<CardInfoResponse> cardInfoResponses2 = data.getData();
                        getNavigator().setCardTel(cardInfoResponses2);
                        break;
                    case API_CALL_PERSON_NAME_FAMILY:
                        List<CardInfoResponse> cardInfoResponses3 = data.getData();
                        getNavigator().setPersonName(cardInfoResponses3);
                        break;
                    case API_CALL_CODE_GHARARDAD:
                        List<CardInfoResponse> cardInfoResponses4 = data.getData();
                        getNavigator().setPersonGharadad(cardInfoResponses4);
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

    public void clickDastband() {
        cart.set(false);
        dastband.set(true);
        personeli.set(false);
        azad.set(false);
        showCart.set(false);
        showPersoneli.set(false);
        showDastband.set(true);
        StartActivity.cardSelected = false;
        StartActivity.azadSelected = false;
        StartActivity.lockerSelected = true;
        StartActivity.personSelected = false;
        getNavigator().setDataNull();

    }

    public void clickCart() {
        cart.set(true);
        dastband.set(false);
        personeli.set(false);
        azad.set(false);
        showCart.set(true);
        showDastband.set(false);
        showAzad.set(false);
        showPersoneli.set(false);
        StartActivity.cardSelected = true;
        StartActivity.azadSelected = false;
        StartActivity.lockerSelected = false;
        StartActivity.personSelected = false;
        getNavigator().setDataNull();
    }

    public void clickAzad() {
        cart.set(false);
        dastband.set(false);
        personeli.set(false);
        azad.set(true);
        showCart.set(false);
        showDastband.set(false);
        showPersoneli.set(false);
        showAzad.set(true);
        StartActivity.cardSelected = false;
        StartActivity.azadSelected = true;
        StartActivity.lockerSelected = false;
        StartActivity.personSelected = false;
        getNavigator().setDataNull();
        Intent intent = MainActivity.getStartIntent(mActivity);
        mActivity.startActivity(intent);
    }

    public void clickPersoneli() {
        cart.set(false);
        dastband.set(false);
        personeli.set(true);
        azad.set(false);
        showCart.set(false);
        showDastband.set(false);
        showPersoneli.set(true);
        showAzad.set(false);
        StartActivity.cardSelected = false;
        StartActivity.azadSelected = false;
        StartActivity.lockerSelected = false;
        StartActivity.personSelected = true;
        getNavigator().setDataNull();
    }


}
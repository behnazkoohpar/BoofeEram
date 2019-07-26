package com.farzam.rest.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.farzam.rest.BR;
import com.farzam.rest.R;
import com.farzam.rest.data.DataManager;
import com.farzam.rest.data.model.api.LoginResponse;
import com.farzam.rest.data.model.api.OrganizationResponse;
import com.farzam.rest.data.model.api.ReceptionResponse;
import com.farzam.rest.databinding.ActivityLoginBinding;
import com.farzam.rest.ui.base.BaseActivity;
import com.farzam.rest.ui.start.StartActivity;
import com.farzam.rest.utils.AppConstants;
import com.farzam.rest.utils.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * behnaz on 11/3/17.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginNavigator, AppConstants {

    @Inject
    LoginViewModel mLoginViewModel;

    ActivityLoginBinding mActivityLoginBinding;
    List<OrganizationResponse> stateList = new ArrayList<>();
    List<ReceptionResponse> cityList = new ArrayList<>();
    private int sizeStateList = 0;
    private int sizeCityList = 0;
    private String citySelected, stateSelected;
    private String[][] stateArray, cityArray;
    private int s, s1;
    private ArrayAdapter<String> arrayAdapterCity;
    private ArrayAdapter<String> arrayAdapterState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding = getViewDataBinding();
        mLoginViewModel.setNavigator(this);
        mLoginViewModel.setActivity(LoginActivity.this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getUnitOrganization();

        mActivityLoginBinding.listState.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                mActivityLoginBinding.expandableLayoutState.toggle();
                mActivityLoginBinding.btn.setVisibility(View.VISIBLE);
                mActivityLoginBinding.btn2.setVisibility(View.INVISIBLE);
                mActivityLoginBinding.state.setText(stateArray[position][0]);
                stateSelected = stateArray[position][1];
            }
        });

        mActivityLoginBinding.listCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                mActivityLoginBinding.expandableLayoutCity.toggle();
                mActivityLoginBinding.btnS.setVisibility(View.VISIBLE);
                mActivityLoginBinding.btnS2.setVisibility(View.INVISIBLE);
                mActivityLoginBinding.city.setText(cityArray[position][0]);
                citySelected = cityArray[position][1];

            }
        });
    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    public LoginViewModel getViewModel() {
        return mLoginViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    private void getUnitOrganization() {
        try {
            HashMap<String, String> map = new HashMap<>();
            if (LOGTRUE)
                Log.d("mPARAMS :::::::: ", map.toString());
            mLoginViewModel.getOrganization(iCallApi, LoginActivity.this, map);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(LoginActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void getUnitPaziresh() {
        try {
            HashMap<String, String> map = new HashMap<>();
            if (LOGTRUE)
                Log.d("mPARAMS :::::::: ", map.toString());
            mLoginViewModel.getReception(iCallApi, LoginActivity.this, map);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(LoginActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void login() {
        try {
            if (mActivityLoginBinding.username.getText().toString().isEmpty() ||
                    mActivityLoginBinding.password.getText().toString().isEmpty() ||
                    stateSelected == null ||
                    stateSelected.equalsIgnoreCase("") ||
                    citySelected == null ||
                    citySelected.equalsIgnoreCase("")) {
                CommonUtils.showSingleButtonAlert(this, getString(R.string.text_attention), getString(R.string.data_is_null), getString(R.string.ok), null);
                return;
            }
            HashMap<String, String> map = new HashMap<>();
            map.put(REQUEST_KEY_USER_NAME, mActivityLoginBinding.username.getText().toString());
            map.put(REQUEST_KEY_PASSWORD, mActivityLoginBinding.password.getText().toString());
            if (LOGTRUE)
                Log.d("mPARAMS :::::::: ", map.toString());
            mLoginViewModel.login(iCallApi, LoginActivity.this, map);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(LoginActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void setOrganization(List<OrganizationResponse> organizationRespons) {
        try {
            stateArray = new String[organizationRespons.size()][organizationRespons.size()];

            final ArrayAdapter<String> arrayAdapterState = new ArrayAdapter<String>(
                    LoginActivity.this,
                    R.layout.select_list_radio);
            s = 0;
            for (int i = 0; i < organizationRespons.size(); i++) {
                stateArray[i][0] = organizationRespons.get(i).getName();
                stateArray[i][1] = organizationRespons.get(i).getOrganizationUnitID();
                arrayAdapterState.add(stateArray[i][0]);
                s++;
            }
            ViewGroup.LayoutParams params = mActivityLoginBinding.expandableLayoutState.getLayoutParams();
            int height = (s * 50);
            params.height = height;
            mActivityLoginBinding.expandableLayoutState.setLayoutParams(params);
            mActivityLoginBinding.listState.setAdapter(arrayAdapterState);
            getUnitPaziresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setReception(List<ReceptionResponse> receptionResponses) {
        try {
            cityArray = new String[receptionResponses.size()][receptionResponses.size()];

            final ArrayAdapter<String> arrayAdapterState = new ArrayAdapter<String>(
                    LoginActivity.this,
                    R.layout.select_list_radio);
            s1 = 0;
            for (int i = 0; i < receptionResponses.size(); i++) {
                cityArray[i][0] = receptionResponses.get(i).getName();
                cityArray[i][1] = receptionResponses.get(i).getReceptionUnitsID();
                arrayAdapterState.add(cityArray[i][0]);
                s1++;
            }
            ViewGroup.LayoutParams params = mActivityLoginBinding.expandableLayoutCity.getLayoutParams();
            int height = (s1 * 50);
            params.height = height;
            mActivityLoginBinding.expandableLayoutCity.setLayoutParams(params);
            mActivityLoginBinding.listCity.setAdapter(arrayAdapterState);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void goToStart(List<LoginResponse> loginResponses) {
        mLoginViewModel.getDataManager().setCurrentUserLoggedInMode(DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER);
        mLoginViewModel.getDataManager().setUserId(loginResponses.get(0).getUserID());
        mLoginViewModel.getDataManager().setUsername(loginResponses.get(0).getName());
        mLoginViewModel.getDataManager().setGender(loginResponses.get(0).getGender());
//        mLoginViewModel.getDataManager().setOrganizationalPosition(loginResponses.get(0).getOrganizationalPosition());
        mLoginViewModel.getDataManager().setReception(citySelected);
        mLoginViewModel.getDataManager().setOrganizationalPosition(stateSelected);

        Intent intent = new Intent(StartActivity.getStartIntent(LoginActivity.this));
        startActivity(intent);
    }

    @Override
    public void openState() {
        ViewGroup.LayoutParams params = mActivityLoginBinding.expandableLayoutState.getLayoutParams();
        int height = (s * 50);
        params.height = height;
        mActivityLoginBinding.expandableLayoutState.setLayoutParams(params);
        mActivityLoginBinding.expandableLayoutState.toggle();
        if (mActivityLoginBinding.btn.getVisibility() == View.VISIBLE) {
            mActivityLoginBinding.btn.setVisibility(View.INVISIBLE);
            mActivityLoginBinding.btn2.setVisibility(View.VISIBLE);
        } else {
            mActivityLoginBinding.btn.setVisibility(View.VISIBLE);
            mActivityLoginBinding.btn2.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void openCity() {
        ViewGroup.LayoutParams params = mActivityLoginBinding.expandableLayoutCity.getLayoutParams();
        int height = (s1 * 50);
        params.height = height;
        mActivityLoginBinding.expandableLayoutCity.setLayoutParams(params);
        mActivityLoginBinding.expandableLayoutCity.toggle();
        if (mActivityLoginBinding.btnS.getVisibility() == View.VISIBLE) {
            mActivityLoginBinding.btnS.setVisibility(View.INVISIBLE);
            mActivityLoginBinding.btnS2.setVisibility(View.VISIBLE);
        } else {
            mActivityLoginBinding.btnS.setVisibility(View.VISIBLE);
            mActivityLoginBinding.btnS2.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}

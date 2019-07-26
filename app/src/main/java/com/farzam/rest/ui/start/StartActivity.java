package com.farzam.rest.ui.start;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.farzam.rest.BR;
import com.farzam.rest.R;
import com.farzam.rest.data.DataManager;
import com.farzam.rest.data.model.api.CardInfoResponse;
import com.farzam.rest.data.model.api.HistoryResponse;
import com.farzam.rest.data.model.api.LockerInfoResponse;
import com.farzam.rest.databinding.ActivityStartBinding;
import com.farzam.rest.ui.base.BaseActivity;
import com.farzam.rest.ui.listFactor.ListFactorActivity;
import com.farzam.rest.ui.login.LoginActivity;
import com.farzam.rest.ui.setting.SettingActivity;
import com.farzam.rest.utils.AppConstants;
import com.farzam.rest.utils.CommonUtils;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

public class StartActivity extends BaseActivity<ActivityStartBinding, StartViewModel> implements StartNavigator, AppConstants
        , NavigationView.OnNavigationItemSelectedListener {

    @Inject
    StartViewModel mStartViewModel;

    ActivityStartBinding mActivityStartBinding;
    private Typeface typeface;
    public static String membershipFileID;
    private LinearLayoutManager layoutManager, layoutManager2, layoutManager3;
    public static String lockerNumber;
    public static String poolReceptionID;
    public static String poolReceiptionAssignLocker;
    public static boolean cardSelected = false;
    public static boolean lockerSelected = true;
    public static boolean azadSelected = false;
    public static boolean personSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        typeface = ResourcesCompat.getFont(this, R.font.iran_sans);
        mActivityStartBinding = getViewDataBinding();
        mStartViewModel.setNavigator(this);
        mStartViewModel.setActivity(StartActivity.this);
        layoutManager = new LinearLayoutManager(this);
        layoutManager2 = new LinearLayoutManager(this);
        layoutManager3 = new LinearLayoutManager(this);
        mActivityStartBinding.list.setLayoutManager(layoutManager);
        mActivityStartBinding.listCard.setLayoutManager(layoutManager2);
        mActivityStartBinding.listPersoneli.setLayoutManager(layoutManager3);

        mActivityStartBinding.list.setVisibility(View.GONE);
        mActivityStartBinding.listCard.setVisibility(View.GONE);
        mActivityStartBinding.listPersoneli.setVisibility(View.GONE);
        mActivityStartBinding.nv.setNavigationItemSelectedListener(this);
        initView();
//        mActivityStartBinding.tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
//
//            @Override
//            public void onTabChanged(String tabId) {
//
//                for (int i = 0; i < mActivityStartBinding.tabHost.getTabWidget().getChildCount(); i++) {
//                    mActivityStartBinding.tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#c2c2c2")); // unselected
//                    TextView tv = (TextView) mActivityStartBinding.tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
//                    tv.setTextColor(Color.parseColor("#ffffff"));
//                    tv.setTypeface(typeface);
//                }
//
//                mActivityStartBinding.tabHost.getTabWidget().getChildAt(mActivityStartBinding.tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#fbb747")); // selected
//                TextView tv = (TextView) mActivityStartBinding.tabHost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
//                tv.setTextColor(Color.parseColor("#ffffff"));
//                tv.setTypeface(typeface);
//
//            }
//        });
    }

    private void initView() {
        if (mStartViewModel.getDataManager().getPrefKeyDontUse()) {
            mActivityStartBinding.nv.getMenu().findItem(R.id.nav_setting).setVisible(false);
        }
        if (mStartViewModel.getDataManager().getPrefKeyFactor()) {
            mActivityStartBinding.nv.getMenu().findItem(R.id.nav_list_factor).setVisible(false);
        }
//        TabHost host = (TabHost) findViewById(R.id.tabHost);
//        host.setup();
//
//        //Tab 1
//        TabHost.TabSpec spec = host.newTabSpec("شماره دستبند");
//        spec.setContent(R.id.tab1);
//        spec.setIndicator("شماره دستبند");
//        host.addTab(spec);
//
//        //Tab 2
//        spec = host.newTabSpec("شماره کارت");
//        spec.setContent(R.id.tab2);
//        spec.setIndicator("شماره کارت");
//        host.addTab(spec);
//
//        //Tab 3
//        spec = host.newTabSpec("شماره پرسنل");
//        spec.setContent(R.id.tab3);
//        spec.setIndicator("شماره پرسنل");
//        host.addTab(spec);
//
//        //Tab 4
//        spec = host.newTabSpec("آزاد");
//        spec.setContent(R.id.tab4);
//        spec.setIndicator("آزاد");
//        host.addTab(spec);

    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, StartActivity.class);
    }

    @Override
    public StartViewModel getViewModel() {
        return mStartViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_start;
    }

//    public static void hideKeyboard(Activity activity) {
//        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
//        //Find the currently focused view, so we can grab the correct window token from it.
//        View view = activity.getCurrentFocus();
//        //If no view currently has focus, create a new one, just so we can grab a window token from it
//        if (view == null) {
//            view = new View(activity);
//        }
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//    }

    @Override
    public void callLockerInfo() {
        try {
            hideKeyboard();
            HashMap<String, String> map = new HashMap<>();
            map.put(REQUEST_KEY_LOCKER_NUMBER, mActivityStartBinding.numberLocker.getText().toString());
            map.put(REQUEST_KEY_ORGANIZATION_UNIT, mStartViewModel.getDataManager().getOrganizationalPosition());
            if (LOGTRUE)
                Log.d("mPARAMS :::::::: ", map.toString());
            mStartViewModel.callLocker(iCallApi, StartActivity.this, map);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void setPersonInfo(List<CardInfoResponse> cardInfo) {
        membershipFileID = cardInfo.get(0).getMembershipfileID();
        mActivityStartBinding.namePerson.setText(cardInfo.get(0).getFullName());
        mActivityStartBinding.nameP.setText(cardInfo.get(0).getLockerNumber());
        mActivityStartBinding.btnSale3.setEnabled(true);
        lockerNumber = cardInfo.get(0).getLockerNumber();
        mActivityStartBinding.listPersoneli.setVisibility(View.VISIBLE);
        mActivityStartBinding.ok3.setVisibility(View.VISIBLE);
        mActivityStartBinding.btnSale3.setVisibility(View.VISIBLE);
    }

    @Override
    public void setCardInfo(List<CardInfoResponse> cardInfo) {
        membershipFileID = cardInfo.get(0).getMembershipfileID();
        mActivityStartBinding.namelocker.setText(cardInfo.get(0).getFullName());
        mActivityStartBinding.locker.setText(cardInfo.get(0).getLockerNumber());
        mActivityStartBinding.btnSale2.setEnabled(true);
        lockerNumber = cardInfo.get(0).getLockerNumber();
        mActivityStartBinding.listCard.setVisibility(View.VISIBLE);
        mActivityStartBinding.ok2.setVisibility(View.VISIBLE);
        mActivityStartBinding.btnSale2.setVisibility(View.VISIBLE);
    }

    @Override
    public void setLockerInfo(List<LockerInfoResponse> lockerInfo) {
        membershipFileID = lockerInfo.get(0).getMembershipfileID();
        mActivityStartBinding.namefull.setText(lockerInfo.get(0).getFullName());
        mActivityStartBinding.numberCard.setText(lockerInfo.get(0).getCardNumber());
        mActivityStartBinding.btnSale.setEnabled(true);
        lockerNumber = lockerInfo.get(0).getLockerNumber();
        mActivityStartBinding.list.setVisibility(View.VISIBLE);
        mActivityStartBinding.ok.setVisibility(View.VISIBLE);
        mActivityStartBinding.btnSale.setVisibility(View.VISIBLE);
        poolReceptionID = lockerInfo.get(0).getPoolReceptionID();
        poolReceiptionAssignLocker = lockerInfo.get(0).getPoolReceiptionAssignLocker();
    }

    @Override
    public void getHistory() {
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put(REQUEST_KEY_MEMBERSHIP_FILE_ID, membershipFileID);
            if (LOGTRUE)
                Log.d("mPARAMS :::::::: ", map.toString());
            mStartViewModel.callHistory(iCallApi, StartActivity.this, map);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void setHistoryLocker(List<HistoryResponse> historyResponses) {
        mActivityStartBinding.list.setAdapter(new HistoryAdapter(historyResponses));
    }

    @Override
    public void setHistoryCard(List<HistoryResponse> historyResponses) {
        mActivityStartBinding.listCard.setAdapter(new HistoryAdapter(historyResponses));
    }

    @Override
    public void setHistoryPersoneli(List<HistoryResponse> historyResponses) {
        mActivityStartBinding.listPersoneli.setAdapter(new HistoryAdapter(historyResponses));
    }

    @Override
    public void callCheckCard() {
        try {
            hideKeyboard();
            HashMap<String, String> map = new HashMap<>();
            map.put(REQUEST_KEY_CARD_NUMBER, mActivityStartBinding.memberShip.getText().toString());
            if (LOGTRUE)
                Log.d("mPARAMS :::::::: ", map.toString());
            mStartViewModel.callCard(iCallApi, StartActivity.this, map);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void callPersonInfo() {
        try {
            hideKeyboard();
            HashMap<String, String> map = new HashMap<>();
            map.put(REQUEST_KEY_CARD_NUMBER, mActivityStartBinding.personNumber.getText().toString());
            if (LOGTRUE)
                Log.d("mPARAMS :::::::: ", map.toString());
            mStartViewModel.callPerson(iCallApi, StartActivity.this, map);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void getHistoryCard() {
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put(REQUEST_KEY_MEMBERSHIP_FILE_ID, membershipFileID);
            if (LOGTRUE)
                Log.d("mPARAMS :::::::: ", map.toString());
            mStartViewModel.callHistoryCard(iCallApi, StartActivity.this, map);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void getHistoryPersonli() {
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put(REQUEST_KEY_MEMBERSHIP_FILE_ID, membershipFileID);
            if (LOGTRUE)
                Log.d("mPARAMS :::::::: ", map.toString());
            mStartViewModel.callHistoryPersoneli(iCallApi, StartActivity.this, map);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    public void openMenu() {
        mActivityStartBinding.dl.openDrawer(Gravity.RIGHT);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        try {
            int id = menuItem.getItemId();
            if (id == R.id.nav_setting) {
                startActivity(new Intent(SettingActivity.getStartIntent(StartActivity.this)));
            } else if (id == R.id.nav_exit) {
                CommonUtils.showTwoButtonAlert(StartActivity.this, getString(R.string.exit_ok), getString(R.string.txt_menu_exit), getString(R.string.btn_cancel), new CommonUtils.IL() {
                    @Override
                    public void onSuccess() {
                        setLogOut();
                    }

                    @Override
                    public void onCancel() {
                    }
                });
            } else if (id == R.id.nav_list_factor) {
                startActivity(new Intent(ListFactorActivity.getStartIntent(StartActivity.this)));
            }

            mActivityStartBinding.dl.closeDrawer(Gravity.RIGHT);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void setLogOut() {
        try {
            mStartViewModel.getDataManager().setCurrentUserLoggedInMode(DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT);
            mStartViewModel.getDataManager().updateUserInfo(
                    DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
                    mStartViewModel.getDataManager().getUsername(),
                    mStartViewModel.getDataManager().getPassword(),
                    false,
                    false,
                    false,
                    false);

            Intent intent = new Intent(StartActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
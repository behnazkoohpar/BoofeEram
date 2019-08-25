package com.farzam.rest.ui.start;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.ArrayAdapter;

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
    }

    private void initView() {
        cardSelected = false;
        azadSelected = false;
        lockerSelected = true;
        personSelected = false;
        if (mStartViewModel.getDataManager().getShowAzad()) {
            mActivityStartBinding.rAzad.setVisibility(View.VISIBLE);
            mActivityStartBinding.txtRAzad.setVisibility(View.VISIBLE);
        }
        if (mStartViewModel.getDataManager().getShowMembership()) {
            mActivityStartBinding.cart.setVisibility(View.VISIBLE);
            mActivityStartBinding.txtRCart.setVisibility(View.VISIBLE);
        }
        if (mStartViewModel.getDataManager().getShowPersone()) {
            mActivityStartBinding.rPersonel.setVisibility(View.VISIBLE);
            mActivityStartBinding.txtRPersonel.setVisibility(View.VISIBLE);
        }

        if (mStartViewModel.getDataManager().getPrefKeyDontUse()) {
            mActivityStartBinding.nv.getMenu().findItem(R.id.nav_setting).setVisible(false);
        }
        if (mStartViewModel.getDataManager().getPrefKeyFactor()) {
            mActivityStartBinding.nv.getMenu().findItem(R.id.nav_list_factor).setVisible(false);
        }
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

    @Override
    public void setPersonInfo(List<CardInfoResponse> cardInfo, int position) {
        membershipFileID = cardInfo.get(position).getMembershipfileID();
        mActivityStartBinding.namePerson.setText(cardInfo.get(position).getFullName());
        mActivityStartBinding.nameP.setText(cardInfo.get(position).getLockerNumber());
        mActivityStartBinding.btnSale3.setEnabled(true);
        lockerNumber = cardInfo.get(position).getLockerNumber();
        mActivityStartBinding.listPersoneli.setVisibility(View.VISIBLE);
        mActivityStartBinding.ok3.setVisibility(View.VISIBLE);
        mActivityStartBinding.lastfactor3.setVisibility(View.VISIBLE);
        mActivityStartBinding.btnSale3.setVisibility(View.VISIBLE);
    }

    @Override
    public void setCardInfo(List<CardInfoResponse> cardInfo, int position) {
        membershipFileID = cardInfo.get(position).getMembershipfileID();
        mActivityStartBinding.namelocker.setText(cardInfo.get(position).getFullName());
        mActivityStartBinding.locker.setText(cardInfo.get(position).getLockerNumber());
        mActivityStartBinding.btnSale2.setEnabled(true);
        lockerNumber = cardInfo.get(position).getLockerNumber();
        mActivityStartBinding.listCard.setVisibility(View.VISIBLE);
        mActivityStartBinding.ok2.setVisibility(View.VISIBLE);
        mActivityStartBinding.lastfactor2.setVisibility(View.VISIBLE);
        mActivityStartBinding.btnSale2.setVisibility(View.VISIBLE);
    }

    @Override
    public void setLockerInfo(List<LockerInfoResponse> lockerInfo, int position) {
        membershipFileID = lockerInfo.get(position).getMembershipfileID();
        mActivityStartBinding.namefull.setText(lockerInfo.get(position).getFullName());
        mActivityStartBinding.numberCard.setText(lockerInfo.get(position).getCardNumber());
        mActivityStartBinding.btnSale.setEnabled(true);
        lockerNumber = lockerInfo.get(position).getLockerNumber();
        mActivityStartBinding.list.setVisibility(View.VISIBLE);
        mActivityStartBinding.ok.setVisibility(View.VISIBLE);
        mActivityStartBinding.lastfactor.setVisibility(View.VISIBLE);
        mActivityStartBinding.btnSale.setVisibility(View.VISIBLE);
        poolReceptionID = lockerInfo.get(position).getPoolReceptionID();
        poolReceiptionAssignLocker = lockerInfo.get(position).getPoolReceiptionAssignLocker();
    }

    @Override
    public void setDataNull() {

        mActivityStartBinding.codeGharardad.setText("");
        mActivityStartBinding.personNumber.setText("");
        mActivityStartBinding.pNameAndFamily.setText("");
        mActivityStartBinding.numberLocker.setText("");
        mActivityStartBinding.nameNumberLocker.setText("");
        mActivityStartBinding.numberLockerCard.setText("");
        mActivityStartBinding.memberShip.setText("");
        mActivityStartBinding.telMemberShip.setText("");
        mActivityStartBinding.namefamilyMemberShip.setText("");
        mActivityStartBinding.locker.setText("");
        mActivityStartBinding.namelocker.setText("");
        mActivityStartBinding.namePerson.setText("");
        mActivityStartBinding.nameP.setText("");
        mActivityStartBinding.numberCard.setText("");
        mActivityStartBinding.namefull.setText("");
        mActivityStartBinding.listCard.setAdapter(null);
        mActivityStartBinding.list.setAdapter(null);
        mActivityStartBinding.listPersoneli.setAdapter(null);
        mActivityStartBinding.lastfactor3.setVisibility(View.GONE);
        mActivityStartBinding.lastfactor2.setVisibility(View.GONE);
        mActivityStartBinding.lastfactor.setVisibility(View.GONE);
        mActivityStartBinding.ok.setVisibility(View.GONE);
        mActivityStartBinding.ok2.setVisibility(View.GONE);
        mActivityStartBinding.ok3.setVisibility(View.GONE);
        mActivityStartBinding.btnSale.setVisibility(View.GONE);
        mActivityStartBinding.btnSale2.setVisibility(View.GONE);
        mActivityStartBinding.btnSale3.setVisibility(View.GONE);
        membershipFileID = null;
    }

    @Override
    public void getHistory() {
        try {
            if (membershipFileID != null) {
                HashMap<String, String> map = new HashMap<>();
                map.put(REQUEST_KEY_MEMBERSHIP_FILE_ID, membershipFileID);
                if (LOGTRUE)
                    Log.d("mPARAMS :::::::: ", map.toString());
                mStartViewModel.callHistory(iCallApi, StartActivity.this, map);
            } else {
                CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect_locker), null, null);
            }
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
    public void callLockerInfo() {
        try {
            if (!mActivityStartBinding.numberLocker.getText().toString().isEmpty()) {
                mActivityStartBinding.nameNumberLocker.setText("");
                mActivityStartBinding.numberLockerCard.setText("");
                hideKeyboard();
                HashMap<String, String> map = new HashMap<>();
                map.put(REQUEST_KEY_LOCKER_NUMBER, mActivityStartBinding.numberLocker.getText().toString());
                map.put(REQUEST_KEY_ORGANIZATION_UNIT, mStartViewModel.getDataManager().getOrganizationalPosition());
                if (LOGTRUE)
                    Log.d("mPARAMS :::::::: ", map.toString());
                mStartViewModel.callLocker(iCallApi, StartActivity.this, map);
            } else {
                CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect_locker), null, null);
            }
        } catch (
                Exception e) {
            CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }

    }

    @Override
    public void callCheckCard() {
        try {
            if (!mActivityStartBinding.memberShip.getText().toString().isEmpty()) {
                mActivityStartBinding.telMemberShip.setText("");
                mActivityStartBinding.namefamilyMemberShip.setText("");
                hideKeyboard();
                HashMap<String, String> map = new HashMap<>();
                map.put(REQUEST_KEY_CARD_NUMBER, mActivityStartBinding.memberShip.getText().toString());
                if (LOGTRUE)
                    Log.d("mPARAMS :::::::: ", map.toString());
                mStartViewModel.callCard(iCallApi, StartActivity.this, map);
            } else {
                CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect_card), null, null);
            }
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void callPersonInfo() {
        try {
            if (!mActivityStartBinding.personNumber.getText().toString().isEmpty()) {
                mActivityStartBinding.codeGharardad.setText("");
                mActivityStartBinding.pNameAndFamily.setText("");
                hideKeyboard();
                HashMap<String, String> map = new HashMap<>();
                map.put(REQUEST_KEY_CARD_NUMBER, mActivityStartBinding.personNumber.getText().toString());
                if (LOGTRUE)
                    Log.d("mPARAMS :::::::: ", map.toString());
                mStartViewModel.callPerson(iCallApi, StartActivity.this, map);
            } else {
                CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect_personeli), null, null);
            }
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void getHistoryCard() {
        try {
            if (membershipFileID != null) {
                HashMap<String, String> map = new HashMap<>();
                map.put(REQUEST_KEY_MEMBERSHIP_FILE_ID, membershipFileID);
                if (LOGTRUE)
                    Log.d("mPARAMS :::::::: ", map.toString());
                mStartViewModel.callHistoryCard(iCallApi, StartActivity.this, map);
            } else {
                CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect_card), null, null);
            }
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void getHistoryPersonli() {
        try {
            if (membershipFileID != null) {
                HashMap<String, String> map = new HashMap<>();
                map.put(REQUEST_KEY_MEMBERSHIP_FILE_ID, membershipFileID);
                if (LOGTRUE)
                    Log.d("mPARAMS :::::::: ", map.toString());
                mStartViewModel.callHistoryPersoneli(iCallApi, StartActivity.this, map);
            } else {
                CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect_personeli), null, null);
            }
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

    @Override
    public void callLockerName() {
        try {
            if (!mActivityStartBinding.nameNumberLocker.getText().toString().isEmpty()) {
                hideKeyboard();
                HashMap<String, String> map = new HashMap<>();
                map.put(REQUEST_KEY_LOCKER_NAME_FAMILY, mActivityStartBinding.nameNumberLocker.getText().toString());
                map.put(REQUEST_KEY_ORGANIZATION_UNIT, mStartViewModel.getDataManager().getOrganizationalPosition());
                if (LOGTRUE)
                    Log.d("mPARAMS :::::::: ", map.toString());
                mStartViewModel.callNameFamilyPerson(iCallApi, StartActivity.this, map);
            } else {
                CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect_personelinameFamily), null, null);
            }
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void callLockerCard() {
        try {
            if (!mActivityStartBinding.numberLockerCard.getText().toString().isEmpty()) {
                hideKeyboard();
                HashMap<String, String> map = new HashMap<>();
                map.put(REQUEST_KEY_NUMBER_LOCKER_CARD, mActivityStartBinding.numberLockerCard.getText().toString());
                map.put(REQUEST_KEY_ORGANIZATION_UNIT, mStartViewModel.getDataManager().getOrganizationalPosition());
                if (LOGTRUE)
                    Log.d("mPARAMS :::::::: ", map.toString());
                mStartViewModel.callPersonNumberLockerCard(iCallApi, StartActivity.this, map);
            } else {
                CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect_numberLockerCard), null, null);
            }
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void callCardNameFamily() {
        try {
            if (!mActivityStartBinding.namefamilyMemberShip.getText().toString().isEmpty()) {
                hideKeyboard();
                HashMap<String, String> map = new HashMap<>();
                map.put(REQUEST_KEY_LOCKER_NAME_FAMILY, mActivityStartBinding.namefamilyMemberShip.getText().toString());
                if (LOGTRUE)
                    Log.d("mPARAMS :::::::: ", map.toString());
                mStartViewModel.callNameMemberShip(iCallApi, StartActivity.this, map);
            } else {
                CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect_personelinameFamily), null, null);
            }
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void callCardTel() {
        try {
            if (!mActivityStartBinding.telMemberShip.getText().toString().isEmpty() || mActivityStartBinding.telMemberShip.length() < 10) {
                hideKeyboard();
                HashMap<String, String> map = new HashMap<>();
                String telNumber = "0" + mActivityStartBinding.telMemberShip.getText().toString().substring((mActivityStartBinding.telMemberShip.getText().toString().length() - 10), mActivityStartBinding.telMemberShip.getText().toString().length());
                map.put(REQUEST_KEY_TEL_MEMBERSHIP, telNumber);
                map.put(REQUEST_KEY_ORGANIZATION_UNIT, mStartViewModel.getDataManager().getOrganizationalPosition());
                if (LOGTRUE)
                    Log.d("mPARAMS :::::::: ", map.toString());
                mStartViewModel.callTelMemberShip(iCallApi, StartActivity.this, map);
            } else {
                CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect_telMemberShip), null, null);
            }
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void callPersonNameFamily() {
        try {
            if (!mActivityStartBinding.pNameAndFamily.getText().toString().isEmpty()) {
                hideKeyboard();
                HashMap<String, String> map = new HashMap<>();
                map.put(REQUEST_KEY_LOCKER_NAME_FAMILY, mActivityStartBinding.pNameAndFamily.getText().toString());
                if (LOGTRUE)
                    Log.d("mPARAMS :::::::: ", map.toString());
                mStartViewModel.callPersonNameAndFamily(iCallApi, StartActivity.this, map);
            } else {
                CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect_personelinameFamily), null, null);
            }
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void callPersonCodeGharardad() {
        try {
            if (!mActivityStartBinding.codeGharardad.getText().toString().isEmpty()) {
                hideKeyboard();
                HashMap<String, String> map = new HashMap<>();
                map.put(REQUEST_KEY_CODE_GHARARDAD, mActivityStartBinding.codeGharardad.getText().toString());
                map.put(REQUEST_KEY_ORGANIZATION_UNIT, mStartViewModel.getDataManager().getOrganizationalPosition());
                if (LOGTRUE)
                    Log.d("mPARAMS :::::::: ", map.toString());
                mStartViewModel.callCodeGharardad(iCallApi, StartActivity.this, map);
            } else {
                CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect_codeGharardad), null, null);
            }
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(StartActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void setLockerCard(final List<LockerInfoResponse> lockerInfo) {
        if (lockerInfo.size() == 1) {
            mActivityStartBinding.numberLocker.setText("");
            mActivityStartBinding.nameNumberLocker.setText("");
            setLockerInfo(lockerInfo, 0);
        } else {
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(StartActivity.this, android.R.layout.select_dialog_singlechoice);
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(StartActivity.this);

            for (int i = 0; i < lockerInfo.size(); i++)
                arrayAdapter.add(lockerInfo.get(i).getFullName());
            builderSingle.setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String strName = arrayAdapter.getItem(which);
                    mActivityStartBinding.numberLocker.setText("");
                    mActivityStartBinding.nameNumberLocker.setText("");
                    setLockerInfo(lockerInfo, which);
                }
            });
        }
    }

    @Override
    public void setLockerName(final List<LockerInfoResponse> lockerInfo) {
        if (lockerInfo.size() == 1) {
            mActivityStartBinding.numberLocker.setText("");
            mActivityStartBinding.numberLockerCard.setText("");
            setLockerInfo(lockerInfo, 0);
        } else {

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(StartActivity.this, android.R.layout.select_dialog_singlechoice);
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(StartActivity.this);

            for (int i = 0; i < lockerInfo.size(); i++)
                arrayAdapter.add(lockerInfo.get(i).getFullName());
            builderSingle.setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String strName = arrayAdapter.getItem(which);
                    mActivityStartBinding.numberLocker.setText("");
                    mActivityStartBinding.numberLockerCard.setText("");
                    setLockerInfo(lockerInfo, which);
                }
            });
            builderSingle.show();
        }
    }

    @Override
    public void setCardName(final List<CardInfoResponse> cardInfoResponses1) {
        if (cardInfoResponses1.size() == 1) {
            mActivityStartBinding.memberShip.setText("");
            mActivityStartBinding.telMemberShip.setText("");
            setCardInfo(cardInfoResponses1, 0);
        } else {
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(StartActivity.this, android.R.layout.select_dialog_singlechoice);
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(StartActivity.this);

            for (int i = 0; i < cardInfoResponses1.size(); i++)
                arrayAdapter.add(cardInfoResponses1.get(i).getFullName());
            builderSingle.setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String strName = arrayAdapter.getItem(which);
                    mActivityStartBinding.memberShip.setText("");
                    mActivityStartBinding.telMemberShip.setText("");
                    setCardInfo(cardInfoResponses1, which);
                }
            });
            builderSingle.show();
        }
    }

    @Override
    public void setCardTel(final List<CardInfoResponse> cardInfoResponses2) {
        if (cardInfoResponses2.size() == 1) {
            mActivityStartBinding.memberShip.setText("");
            mActivityStartBinding.namefamilyMemberShip.setText("");
            setCardInfo(cardInfoResponses2, 0);
        } else {
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(StartActivity.this, android.R.layout.select_dialog_singlechoice);
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(StartActivity.this);

            for (int i = 0; i < cardInfoResponses2.size(); i++)
                arrayAdapter.add(cardInfoResponses2.get(i).getFullName());
            builderSingle.setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String strName = arrayAdapter.getItem(which);
                    mActivityStartBinding.memberShip.setText("");
                    mActivityStartBinding.namefamilyMemberShip.setText("");
                    setCardInfo(cardInfoResponses2, which);
                }
            });
            builderSingle.show();
        }
    }

    @Override
    public void setPersonName(final List<CardInfoResponse> cardInfoResponses3) {
        if (cardInfoResponses3.size() == 1) {
            mActivityStartBinding.personNumber.setText("");
            mActivityStartBinding.codeGharardad.setText("");
            setPersonInfo(cardInfoResponses3, 0);
        } else {
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(StartActivity.this, android.R.layout.select_dialog_singlechoice);
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(StartActivity.this);

            for (int i = 0; i < cardInfoResponses3.size(); i++)
                arrayAdapter.add(cardInfoResponses3.get(i).getFullName());
            builderSingle.setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String strName = arrayAdapter.getItem(which);
                    mActivityStartBinding.personNumber.setText("");
                    mActivityStartBinding.codeGharardad.setText("");
                    setPersonInfo(cardInfoResponses3, which);
                }
            });
            builderSingle.show();
        }
    }

    @Override
    public void setPersonGharadad(final List<CardInfoResponse> cardInfoResponses4) {
        if (cardInfoResponses4.size() == 1) {
            mActivityStartBinding.personNumber.setText("");
            mActivityStartBinding.pNameAndFamily.setText("");
            setPersonInfo(cardInfoResponses4, 0);
        } else {
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(StartActivity.this, android.R.layout.select_dialog_singlechoice);
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(StartActivity.this);

            for (int i = 0; i < cardInfoResponses4.size(); i++)
                arrayAdapter.add(cardInfoResponses4.get(i).getFullName());
            builderSingle.setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String strName = arrayAdapter.getItem(which);
                    mActivityStartBinding.personNumber.setText("");
                    mActivityStartBinding.pNameAndFamily.setText("");
                    setPersonInfo(cardInfoResponses4, which);
                }
            });
            builderSingle.show();
        }
    }
}
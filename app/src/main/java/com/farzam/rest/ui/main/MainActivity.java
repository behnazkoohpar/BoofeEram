package com.farzam.rest.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;

import com.farzam.rest.BR;
import com.farzam.rest.R;
import com.farzam.rest.data.model.api.FoodDetailResponse;
import com.farzam.rest.data.model.api.StuffListResponse;
import com.farzam.rest.databinding.ActivityMainBinding;
import com.farzam.rest.ui.base.BaseActivity;
import com.farzam.rest.ui.start.StartActivity;
import com.farzam.rest.utils.AppConstants;
import com.farzam.rest.utils.CommonUtils;
import com.google.gson.Gson;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator, AppConstants
        , View.OnClickListener{

    @Inject
    MainViewModel mMainViewModel;
    ActivityMainBinding mActivityMainBinding;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<StuffListResponse>> expandableListDetail;
    private RecyclerView recyclerViewListSelectedStuff;
    private LinearLayoutManager layoutManagerListSelectedStuff;
    private ListSelectedStuffAdapter mAdapter;
    private List<StuffListResponse> stuffSelecteds = new ArrayList<>();
    private int sumP;
    NumberFormat numberFormat = NumberFormat.getNumberInstance();
    private ArrayList<StuffListResponse> parents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = getViewDataBinding();
        mMainViewModel.setNavigator(this);
        mMainViewModel.setActivity(MainActivity.this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initView();
        getAllStuffList();
    }

    private void initView() {

        recyclerViewListSelectedStuff = (RecyclerView) findViewById(R.id.category_stuff_list_selected);
        layoutManagerListSelectedStuff = new LinearLayoutManager(this);
        recyclerViewListSelectedStuff.setLayoutManager(layoutManagerListSelectedStuff);
        mAdapter = new ListSelectedStuffAdapter(stuffSelecteds);
        recyclerViewListSelectedStuff.setAdapter(mAdapter);
        if (stuffSelecteds != null)
            for (int i = 0; i < stuffSelecteds.size(); i++) {
                sumP = sumP + Integer.parseInt(stuffSelecteds.get(i).getPrice());
            }
        mActivityMainBinding.sum.setText(numberFormat.format(sumP));

        mAdapter.setOnitemclickListener(new ListSelectedStuffAdapter.OnItemClickListener() {
            @Override
            public void onIncreaseClick(int position) {
                stuffSelecteds.get(position).setNumber(stuffSelecteds.get(position).getNumber() + 1);
                stuffSelecteds.get(position).setSumPrice(Integer.parseInt(stuffSelecteds.get(position).getPrice()) * stuffSelecteds.get(position).getNumber());

                mAdapter.notifyItemChanged(position);
                sumP = 0;
                for (int i = 0; i < stuffSelecteds.size(); i++) {
                    sumP = sumP + Integer.parseInt(stuffSelecteds.get(i).getPrice()) * stuffSelecteds.get(i).getNumber();
                }
                mActivityMainBinding.sum.setText(numberFormat.format(sumP));
            }

            @Override
            public void onDecreaseClick(int position) {
                if (stuffSelecteds.get(position).getNumber() - 1 > 0) {
                    stuffSelecteds.get(position).setNumber(stuffSelecteds.get(position).getNumber() - 1);
                    mAdapter.notifyItemChanged(position);
                } else {
                    stuffSelecteds.remove(position);
                    mAdapter.notifyItemRemoved(position);
                }
                sumP = 0;
                for (int i = 0; i < stuffSelecteds.size(); i++) {
                    sumP = sumP + Integer.parseInt(stuffSelecteds.get(i).getPrice()) * stuffSelecteds.get(i).getNumber();
                }
                mActivityMainBinding.sum.setText(numberFormat.format(sumP));
            }
        });
    }

    @Override
    public MainViewModel getViewModel() {
        return mMainViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }

    @Override
    public void openCategoryList() {
        //      Intent intent = CategoryActivity.getStartIntent(this);
        //    startActivity(intent);
    }

    @Override
    public void callSearch() {

    }


    private void getAllStuffList() {
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put(REQUEST_KEY_RECEPTON_UNIT, mMainViewModel.getDataManager().getReception());
            if (LOGTRUE)
                Log.d("mPARAMS :::::::: ", map.toString());
            mMainViewModel.getStuffList(iCallApi, this, map);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void setStuffList(List<StuffListResponse> stuffListResponses) {

        HashMap<String, List<StuffListResponse>> ParentItem = new HashMap<String, List<StuffListResponse>>();
        parents = new ArrayList<>();

        for (int i = 0; i < stuffListResponses.size(); i++) {
            parents.add(stuffListResponses.get(i));
            List<StuffListResponse> childs2 = new ArrayList<>();
            for (int k = 0; k < stuffListResponses.size(); k++) {
                if (stuffListResponses.get(k).getServiceGroupID() != null && stuffListResponses.get(k).getServiceGroupID().equalsIgnoreCase(stuffListResponses.get(i).getServiceGroupID()))
                    childs2.add(stuffListResponses.get(k));
            }
            ParentItem.put(stuffListResponses.get(i).getGroupName(), childs2);
        }
        expandableListDetail = ParentItem;
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new ExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        mActivityMainBinding.expandableListView.setAdapter(expandableListAdapter);
        mActivityMainBinding.expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        mActivityMainBinding.expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });

        mActivityMainBinding.expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                StuffListResponse stuffListResponse = expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition);
                stuffSelecteds.add(stuffListResponse);
                mAdapter.notifyDataSetChanged();
                sumP = 0;
                for (int i = 0; i < stuffSelecteds.size(); i++) {
                    sumP = sumP + Integer.parseInt(stuffSelecteds.get(i).getPrice()) * stuffSelecteds.get(i).getNumber();
                }
                mActivityMainBinding.sum.setText(numberFormat.format(sumP));
                return false;
            }
        });
    }

    @Override
    public void setFactor() {
        try {
            String jsonArray =  new Gson().toJson(stuffSelecteds);
            HashMap<String, String> map = new HashMap<>();
            map.put(REQUEST_KEY_RECEPTON_UNIT, mMainViewModel.getDataManager().getReception());
            map.put(REQUEST_KEY_ORGANIZATION_UNIT, mMainViewModel.getDataManager().getOrganizationalPosition());
            map.put(REQUEST_KEY_USER_ID, mMainViewModel.getDataManager().getUserId());
            map.put(REQUEST_KEY_TOTAL_PRICE, String.valueOf(sumP));
            map.put(REQUEST_KEY_POOL_RECEPTION_ASSIGN_LOCKER, StartActivity.poolReceiptionAssignLocker);
            map.put(REQUEST_KEY_POOL_RECEPTION,StartActivity.poolReceptionID);
            map.put(REQUEST_KEY_DETAIL_FACTOR,jsonArray);

            if (LOGTRUE)
                Log.d("mPARAMS :::::::: ", map.toString());
            mMainViewModel.callSetFactor(iCallApi, this, map);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void setFactorPerson() {
        try {
            String jsonArray =  new Gson().toJson(stuffSelecteds);
            HashMap<String, String> map = new HashMap<>();
            map.put(REQUEST_KEY_RECEPTON_UNIT, mMainViewModel.getDataManager().getReception());
            map.put(REQUEST_KEY_ORGANIZATION_UNIT, mMainViewModel.getDataManager().getOrganizationalPosition());
            map.put(REQUEST_KEY_USER_ID, mMainViewModel.getDataManager().getUserId());
            map.put(REQUEST_KEY_TOTAL_PRICE, String.valueOf(sumP));
            map.put(REQUEST_KEY_DETAIL_FACTOR,jsonArray);
            map.put(REQUEST_KEY_MEMBERSHIP_FILE_ID,StartActivity.membershipFileID);


            if (LOGTRUE)
                Log.d("mPARAMS :::::::: ", map.toString());
            mMainViewModel.callSetFactorPerson(iCallApi, this, map);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void setFactorAzad() {
        try {
            String jsonArray =  new Gson().toJson(stuffSelecteds);
            HashMap<String, String> map = new HashMap<>();
            map.put(REQUEST_KEY_RECEPTON_UNIT, mMainViewModel.getDataManager().getReception());
            map.put(REQUEST_KEY_ORGANIZATION_UNIT, mMainViewModel.getDataManager().getOrganizationalPosition());
            map.put(REQUEST_KEY_USER_ID, mMainViewModel.getDataManager().getUserId());
            map.put(REQUEST_KEY_TOTAL_PRICE, String.valueOf(sumP));
            map.put(REQUEST_KEY_DETAIL_FACTOR,jsonArray);

            if (LOGTRUE)
                Log.d("mPARAMS :::::::: ", map.toString());
            mMainViewModel.callSetFactorAzad(iCallApi, this, map);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void setFactorCard() {
        try {
            String jsonArray =  new Gson().toJson(stuffSelecteds);
            HashMap<String, String> map = new HashMap<>();
            map.put(REQUEST_KEY_RECEPTON_UNIT, mMainViewModel.getDataManager().getReception());
            map.put(REQUEST_KEY_ORGANIZATION_UNIT, mMainViewModel.getDataManager().getOrganizationalPosition());
            map.put(REQUEST_KEY_USER_ID, mMainViewModel.getDataManager().getUserId());
            map.put(REQUEST_KEY_TOTAL_PRICE, String.valueOf(sumP));
            map.put(REQUEST_KEY_DETAIL_FACTOR,jsonArray);
            map.put(REQUEST_KEY_MEMBERSHIP_FILE_ID,StartActivity.membershipFileID);

            if (LOGTRUE)
                Log.d("mPARAMS :::::::: ", map.toString());
            mMainViewModel.callSetFactorCard(iCallApi, this, map);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void setSpecialOffer(List<FoodDetailResponse> FoodDetailResponses) {
        buildRecycleView(FoodDetailResponses);
    }

    private void buildRecycleView(List<FoodDetailResponse> FoodDetailResponses) {

    }

    private void removeItem(int position) {

    }


    @Override
    public void openStartActivity() {
        startActivity(StartActivity.getStartIntent(MainActivity.this));
        finish();
    }
}

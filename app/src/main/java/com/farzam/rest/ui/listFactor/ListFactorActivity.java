package com.farzam.rest.ui.listFactor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.WindowManager;

import com.farzam.rest.BR;
import com.farzam.rest.R;
import com.farzam.rest.data.model.api.ListFactorDetailResponse;
import com.farzam.rest.data.model.api.ListFactorResponse;
import com.farzam.rest.databinding.ActivityListFactorBinding;
import com.farzam.rest.ui.base.BaseActivity;
import com.farzam.rest.utils.AppConstants;
import com.farzam.rest.utils.CommonUtils;
import com.mojtaba.materialdatetimepicker.date.DatePickerDialog;
import com.mojtaba.materialdatetimepicker.time.TimePickerDialog;
import com.mojtaba.materialdatetimepicker.utils.PersianCalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

public class ListFactorActivity extends BaseActivity<ActivityListFactorBinding, ListFactorViewModel> implements ListFactorNavigator, AppConstants,
        DatePickerDialog.OnDateSetListener, OnItemClickListener {

    @Inject
    ListFactorViewModel mListFactorViewModel;

    ActivityListFactorBinding mActivityListFactorBinding;
    private LinearLayoutManager layoutManagerListSelectedStuff;
    private ListFactorAdapter mAdapter;
    private List<ListFactorResponse> listFactors;
    private DatePickerDialog dpd;
    private TimePickerDialog tpd;
    private PersianCalendar now;
    private Calendar nowCal;
    private boolean selectedDateFrom;
    private boolean selectedTimeFrom;
    private String mYear;
    private String mMonth;
    private String mDay;
    private int isDelivered;
    private int isPerson;
    private int isRecipt;
    private ListFactorAdapter.ViewHolder viewholder;
    private int positionn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityListFactorBinding = getViewDataBinding();
        mListFactorViewModel.setNavigator(this);
        mListFactorViewModel.setActivity(ListFactorActivity.this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        PersianCalendar persianCalendar = new PersianCalendar();
        mYear = String.valueOf(persianCalendar.getPersianYear());
        mMonth = String.valueOf(persianCalendar.getPersianMonth() + 1);
        mDay = String.valueOf(persianCalendar.getPersianDay());
        if (Integer.parseInt(mMonth) < 10)
            mMonth = "0" + mMonth;
        if (Integer.parseInt(mDay) < 10)
            mDay = "0" + mDay;
        mActivityListFactorBinding.fromDate.setText(mYear + "/" + mMonth + "/" + mDay);
        mActivityListFactorBinding.toDate.setText(mYear + "/" + mMonth + "/" + mDay);

//        getListfactor();
    }


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ListFactorActivity.class);
        return intent;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String month = String.valueOf(monthOfYear + 1);
        String day = String.valueOf(dayOfMonth);
        if ((monthOfYear + 1) < 10)
            month = "0" + (monthOfYear + 1);
        if (dayOfMonth < 10)
            day = "0" + dayOfMonth;
        if (selectedDateFrom) {
            String date = year + "/" + (month) + "/" + day;
            mActivityListFactorBinding.fromDate.setText(date);
        } else {
            String date = year + "/" + (month) + "/" + day;
            mActivityListFactorBinding.toDate.setText(date);
        }
    }

    @Override
    public ListFactorViewModel getViewModel() {
        return mListFactorViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_list_factor;
    }


    @Override
    public void openFromDateCalendar() {
        now = new PersianCalendar();
        dpd = DatePickerDialog.newInstance(
                this,
                now.getPersianYear(),
                now.getPersianMonth(),
                now.getPersianDay()
        );
        selectedDateFrom = true;
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void openToDateCalendar() {
        now = new PersianCalendar();
        dpd = DatePickerDialog.newInstance(
                this,
                now.getPersianYear(),
                now.getPersianMonth(),
                now.getPersianDay()
        );
        selectedDateFrom = false;
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void setDelivered(int i) {
        isDelivered = i;
    }

    @Override
    public void setPersonRecieved(int i, int i1) {
        isPerson = i;
        isRecipt = i1;
    }

    @Override
    public void onReportClick() {
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put(REQUEST_KEY_RECEPTON_UNIT, mListFactorViewModel.getDataManager().getReception());
            map.put(REQUEST_KEY_ORGANIZATION_UNIT, mListFactorViewModel.getDataManager().getOrganizationalPosition());
            map.put(REQUEST_KEY_FROM_DATE, mActivityListFactorBinding.fromDate.getText().toString());
            map.put(REQUEST_KEY_TO_DATE, mActivityListFactorBinding.toDate.getText().toString());
            map.put(REQUEST_KEY_IS_DELIVERED, String.valueOf(isDelivered));
            map.put(REQUEST_KEY_IS_PERSON, String.valueOf(isPerson));
            map.put(REQUEST_KEY_IS_RECEIPT, String.valueOf(isRecipt));
            if (LOGTRUE)
                Log.d("mPARAMS :::::::: ", map.toString());
            mListFactorViewModel.getListFactor(iCallApi, this, map);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void callDetailFactor(ListFactorAdapter.ViewHolder viewHolder, int position, String id) {
        try {
            viewholder = viewHolder;
            positionn = position;
            HashMap<String, String> map = new HashMap<>();
            map.put(REQUEST_KEY_SALES_INVOICE_ID, id);
            if (LOGTRUE)
                Log.d("mPARAMS :::::::: ", map.toString());
            mListFactorViewModel.getListDetailFactor(iCallApi, this, map);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void setList(List<ListFactorResponse> listFactorResponses) {
        listFactors = new ArrayList<>();
        listFactors = listFactorResponses;
        layoutManagerListSelectedStuff = new LinearLayoutManager(this);
        mActivityListFactorBinding.list.setLayoutManager(layoutManagerListSelectedStuff);
        mAdapter = new ListFactorAdapter(listFactors);
        mActivityListFactorBinding.list.setAdapter(mAdapter);
        mAdapter.setOnitemclickListener(this);
    }

    @Override
    public void setListDetail(List<ListFactorDetailResponse> listFactorDetailResponses) {
        listFactors.get(positionn).setListFactorDetailResponseList(listFactorDetailResponses);

//        mAdapter.setListInList(viewholder,positionn,listFactorDetailResponses);
        mAdapter.notifyDataSetChanged();
    }

}

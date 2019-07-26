package com.farzam.rest.ui.listFactor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.farzam.rest.BR;
import com.farzam.rest.R;
import com.farzam.rest.data.model.api.ListFactorResponse;
import com.farzam.rest.databinding.ActivityListFactorBinding;
import com.farzam.rest.ui.base.BaseActivity;
import com.farzam.rest.utils.AppConstants;
import com.farzam.rest.utils.CommonUtils;
import com.mojtaba.materialdatetimepicker.date.DatePickerDialog;
import com.mojtaba.materialdatetimepicker.time.RadialPickerLayout;
import com.mojtaba.materialdatetimepicker.time.TimePickerDialog;
import com.mojtaba.materialdatetimepicker.utils.PersianCalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

public class ListFactorActivity extends BaseActivity<ActivityListFactorBinding, ListFactorViewModel> implements ListFactorNavigator, AppConstants,
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

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
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mhour;
    private int mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityListFactorBinding = getViewDataBinding();
        mListFactorViewModel.setNavigator(this);
        mListFactorViewModel.setActivity(ListFactorActivity.this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mActivityListFactorBinding.textday.setText(R.string.today);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mhour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
//        getListfactor();
    }


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ListFactorActivity.class);
        return intent;
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        if (selectedTimeFrom) {
            String time = hourOfDay + ":" + minute;
            mActivityListFactorBinding.fromTime.setText(time);
        } else {
            String time = hourOfDay + ":" + minute;
            mActivityListFactorBinding.toTime.setText(time);

        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        if (selectedDateFrom) {
            String date = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
            mActivityListFactorBinding.fromDate.setText(date);
        } else {
            String date = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
            mActivityListFactorBinding.toDate.setText(date);
            toUpAnim();
            getListfactor();
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
    public void setToday() {
        mActivityListFactorBinding.fromDate.setText(now.getPersianYear() + "/" + now.getPersianMonth() + "/" + now.getPersianDay());
        mActivityListFactorBinding.fromTime.setText("00:00");
        mActivityListFactorBinding.toDate.setText(now.getPersianYear() + "/" + now.getPersianMonth() + "/" + now.getPersianDay());
        mActivityListFactorBinding.toTime.setText("23:59");

        getListfactor();
    }

    @Override
    public void setMonth() {

//        mActivityListFactorBinding.fromDate.setText(now.getPersianYear() + "/" + now.getPersianMonth() + "/01");
//        mActivityListFactorBinding.fromTime.setText("00:00");
//        if (now.getPersianMonth() <= 6)
//            mActivityListFactorBinding.toDate.setText(now.getPersianYear() + "/" + now.getPersianMonth() + "/31");
//        else
//            mActivityListFactorBinding.toDate.setText(now.getPersianYear() + "/" + now.getPersianMonth() + "/30");
//        mActivityListFactorBinding.toTime.setText("23:59");

//        getListfactor();
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
    public void openFromTimeCalendar() {
        nowCal = Calendar.getInstance();
        tpd = TimePickerDialog.newInstance(
                this,
                nowCal.get(Calendar.HOUR_OF_DAY),
                nowCal.get(Calendar.MINUTE),
                true
        );
        selectedTimeFrom = true;
        tpd.show(getFragmentManager(), "Datepickerdialog");
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
    public void openToTimeCalendar() {
        nowCal = Calendar.getInstance();
        tpd = TimePickerDialog.newInstance(
                this,
                nowCal.get(Calendar.HOUR_OF_DAY),
                nowCal.get(Calendar.MINUTE),
                true
        );
        selectedTimeFrom = false;
        tpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void filterClick() {
        Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.to_down_animate);
        slide_down.setDuration(500);
        mActivityListFactorBinding.layoutFilter.setVisibility(View.VISIBLE);
        mActivityListFactorBinding.layoutFilter.startAnimation(slide_down);
    }

    @Override
    public void toUpAnim() {
        Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.to_up_animate);
//        slide_up.setDuration(1200);
        mActivityListFactorBinding.layoutFilter.setVisibility(View.GONE);
        mActivityListFactorBinding.layoutFilter.startAnimation(slide_up);
    }

    private void getListfactor() {
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put(REQUEST_KEY_RECEPTON_UNIT, mListFactorViewModel.getDataManager().getReception());
            map.put(REQUEST_KEY_FROM_DATE, mActivityListFactorBinding.fromDate.getText().toString());
            map.put(REQUEST_KEY_TO_DATE, mActivityListFactorBinding.toDate.getText().toString());
            map.put(REQUEST_KEY_FROM_TIME,mActivityListFactorBinding.fromTime.getText().toString());
            map.put(REQUEST_KEY_TO_TIME, mActivityListFactorBinding.toTime.getText().toString());
            if (LOGTRUE)
                Log.d("mPARAMS :::::::: ", map.toString());
            mListFactorViewModel.getListFactor(iCallApi, this, map);
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
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

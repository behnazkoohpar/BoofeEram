package com.farzam.rest.ui.listFactor;

import com.farzam.rest.data.model.api.ListFactorDetailResponse;
import com.farzam.rest.data.model.api.ListFactorResponse;

import java.util.List;

public interface ListFactorNavigator {

    void setList(List<ListFactorResponse> listFactorResponses);

    void openFromDateCalendar();

    void openToDateCalendar();

    void onReportClick();

    void setDelivered(int i);

    void setPersonRecieved(int i, int i1);

    void setListDetail(List<ListFactorDetailResponse> listFactorDetailResponses);
}

package com.farzam.rest.ui.listFactor;

import com.farzam.rest.data.model.api.ListFactorResponse;

import java.util.List;

public interface ListFactorNavigator {
    void filterClick();

    void toUpAnim();

    void setList(List<ListFactorResponse> listFactorResponses);

    void openFromDateCalendar();

    void openFromTimeCalendar();

    void openToDateCalendar();

    void openToTimeCalendar();

    void setToday();

    void setMonth();
}

package com.farzam.rest.ui.main;

import com.farzam.rest.data.model.api.FoodDetailResponse;
import com.farzam.rest.data.model.api.FoodResponse;
import com.farzam.rest.data.model.api.StuffListResponse;

import java.util.List;

/**
 * Created by cmos on 05/04/2018.
 */

public interface MainNavigator {
    void setSpecialOffer(List<FoodDetailResponse> FoodDetailResponses);
    void openCategoryList();
    void callSearch();
    void setStuffList(List<StuffListResponse> stuffListResponses);
    void setFactor();
    void setFactorAzad();
    void setFactorPerson();
    void setFactorCard();
}

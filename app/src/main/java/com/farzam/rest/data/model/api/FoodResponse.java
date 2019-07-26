package com.farzam.rest.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cmos on 10/04/2018.
 */

public class FoodResponse {

    @SerializedName("StuffID")
    @Expose
    private String StuffID;

    public String getStuffID() {
        return StuffID;
    }

    public void setStuffID(String stuffID) {
        StuffID = stuffID;
    }
}

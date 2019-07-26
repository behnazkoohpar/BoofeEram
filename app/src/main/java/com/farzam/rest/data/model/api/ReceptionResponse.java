package com.farzam.rest.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cmos on 13/09/2018.
 */

public class ReceptionResponse {
    @SerializedName("ReceptionUnitsID")
    @Expose
    private String ReceptionUnitsID;

    @SerializedName("Name")
    @Expose
    private String Name;

    public String getReceptionUnitsID() {
        return ReceptionUnitsID;
    }

    public void setReceptionUnitsID(String receptionUnitsID) {
        ReceptionUnitsID = receptionUnitsID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

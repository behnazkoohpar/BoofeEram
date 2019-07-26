package com.farzam.rest.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cmos on 14/09/2018.
 */

public class StuffListResponse {

    private int number = 1;
    private int sumPrice=0 ;
    @SerializedName("ServiceGroupID")
    @Expose
    private String ServiceGroupID;

    @SerializedName("ReceptionUnit")
    @Expose
    private String ReceptionUnit;

    @SerializedName("GroupName")
    @Expose
    private String GroupName;

    @SerializedName("StuffID")
    @Expose
    private String StuffID;

    @SerializedName("StuffName")
    @Expose
    private String StuffName;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("Price")
    @Expose
    private String Price;

    public String getServiceGroupID() {
        return ServiceGroupID;
    }

    public void setServiceGroupID(String serviceGroupID) {
        ServiceGroupID = serviceGroupID;
    }

    public String getReceptionUnit() {
        return ReceptionUnit;
    }

    public void setReceptionUnit(String receptionUnit) {
        ReceptionUnit = receptionUnit;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public String getStuffID() {
        return StuffID;
    }

    public void setStuffID(String stuffID) {
        StuffID = stuffID;
    }

    public String getStuffName() {
        return StuffName;
    }

    public void setStuffName(String stuffName) {
        StuffName = stuffName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(int sumPrice) {
        this.sumPrice = Integer.parseInt(getPrice());
    }
}

package com.farzam.rest.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cmos on 14/04/2018.
 */

public class FoodDetailResponse {
    @SerializedName("ID")
    @Expose
    private String ID;
    @SerializedName("BrandName")
    @Expose
    private String BrandName;
    @SerializedName("StuffName")
    @Expose
    private String StuffName;
    @SerializedName("Price")
    @Expose
    private String Price;
    @SerializedName("offer")
    @Expose
    private String offer;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public String getStuffName() {
        return StuffName;
    }

    public void setStuffName(String stuffName) {
        StuffName = stuffName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

}

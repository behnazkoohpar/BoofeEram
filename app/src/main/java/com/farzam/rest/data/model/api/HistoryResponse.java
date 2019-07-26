package com.farzam.rest.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cmos on 14/09/2018.
 */

public class HistoryResponse {

    @SerializedName("Code")
    @Expose
    private String Code;

    @SerializedName("Stuff_Text")
    @Expose
    private String Stuff_Text;

    @SerializedName("Value")
    @Expose
    private String Value;

    @SerializedName("UnitPrice")
    @Expose
    private String UnitPrice;

    @SerializedName("TotalPrice")
    @Expose
    private String TotalPrice;

    @SerializedName("InvoiceDate")
    @Expose
    private String InvoiceDate;

    @SerializedName("FactorNo")
    @Expose
    private String FactorNo;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getStuff_Text() {
        return Stuff_Text;
    }

    public void setStuff_Text(String stuff_Text) {
        Stuff_Text = stuff_Text;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        UnitPrice = unitPrice;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getInvoiceDate() {
        return InvoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        InvoiceDate = invoiceDate;
    }

    public String getFactorNo() {
        return FactorNo;
    }

    public void setFactorNo(String factorNo) {
        FactorNo = factorNo;
    }
}

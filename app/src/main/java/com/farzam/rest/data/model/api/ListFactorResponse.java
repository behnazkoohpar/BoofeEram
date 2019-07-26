package com.farzam.rest.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListFactorResponse {

    @SerializedName("TotalPrice")
    @Expose
    private String TotalPrice;

    @SerializedName("InvoiceDate")
    @Expose
    private String InvoiceDate;

    @SerializedName("FactorNo")
    @Expose
    private String FactorNo;


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

package com.farzam.rest.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListFactorResponse {

    private List<ListFactorDetailResponse> listFactorDetailResponseList;
    @SerializedName("TotalPrice")
    @Expose
    private String TotalPrice;

    @SerializedName("InvoiceDate")
    @Expose
    private String InvoiceDate;

    @SerializedName("FactorNo")
    @Expose
    private String FactorNo;

    @SerializedName("SalesInvoiceID")
    @Expose
    private String SalesInvoiceID;

    @SerializedName("InvoiceTime")
    @Expose
    private String InvoiceTime;

    @SerializedName("PaymentedTotalAmount")
    @Expose
    private String PaymentedTotalAmount;

    @SerializedName("RegisterType")
    @Expose
    private String RegisterType;

    @SerializedName("Printed")
    @Expose
    private boolean Printed;

    @SerializedName("IsDeliverd")
    @Expose
    private boolean IsDeliverd;

    @SerializedName("LockerNumber")
    @Expose
    private String LockerNumber;

    @SerializedName("Membership_CardNumber")
    @Expose
    private String Membership_CardNumber;

    @SerializedName("Membership_FullName")
    @Expose
    private String Membership_FullName;

    @SerializedName("IsPoolReception")
    @Expose
    private String IsPoolReception;

    @SerializedName("IsPerson")
    @Expose
    private String IsPerson;

    @SerializedName("Person_BiometricCode")
    @Expose
    private String Person_BiometricCode;

    @SerializedName("Person_FullName")
    @Expose
    private String Person_FullName;


    public void setDeliverd(boolean deliverd) {
        IsDeliverd = deliverd;
    }

    public List<ListFactorDetailResponse> getListFactorDetailResponseList() {
        return listFactorDetailResponseList;
    }

    public void setListFactorDetailResponseList(List<ListFactorDetailResponse> listFactorDetailResponseList) {
        this.listFactorDetailResponseList = listFactorDetailResponseList;
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

    public String getSalesInvoiceID() {
        return SalesInvoiceID;
    }

    public void setSalesInvoiceID(String salesInvoiceID) {
        SalesInvoiceID = salesInvoiceID;
    }

    public String getInvoiceTime() {
        return InvoiceTime;
    }

    public void setInvoiceTime(String invoiceTime) {
        InvoiceTime = invoiceTime;
    }

    public String getPaymentedTotalAmount() {
        return PaymentedTotalAmount;
    }

    public void setPaymentedTotalAmount(String paymentedTotalAmount) {
        PaymentedTotalAmount = paymentedTotalAmount;
    }

    public String getRegisterType() {
        return RegisterType;
    }

    public void setRegisterType(String registerType) {
        RegisterType = registerType;
    }

    public boolean getPrinted() {
        return Printed;
    }

    public void setPrinted(boolean printed) {
        Printed = printed;
    }

    public boolean getIsDeliverd() {
        return IsDeliverd;
    }

    public void setIsDeliverd(boolean isDeliverd) {
        IsDeliverd = isDeliverd;
    }

    public String getLockerNumber() {
        return LockerNumber;
    }

    public void setLockerNumber(String lockerNumber) {
        LockerNumber = lockerNumber;
    }

    public String getMembership_CardNumber() {
        return Membership_CardNumber;
    }

    public void setMembership_CardNumber(String membership_CardNumber) {
        Membership_CardNumber = membership_CardNumber;
    }

    public String getMembership_FullName() {
        return Membership_FullName;
    }

    public void setMembership_FullName(String membership_FullName) {
        Membership_FullName = membership_FullName;
    }

    public String getIsPoolReception() {
        return IsPoolReception;
    }

    public void setIsPoolReception(String isPoolReception) {
        IsPoolReception = isPoolReception;
    }

    public String getIsPerson() {
        return IsPerson;
    }

    public void setIsPerson(String isPerson) {
        IsPerson = isPerson;
    }

    public String getPerson_BiometricCode() {
        return Person_BiometricCode;
    }

    public void setPerson_BiometricCode(String person_BiometricCode) {
        Person_BiometricCode = person_BiometricCode;
    }

    public String getPerson_FullName() {
        return Person_FullName;
    }

    public void setPerson_FullName(String person_FullName) {
        Person_FullName = person_FullName;
    }
}

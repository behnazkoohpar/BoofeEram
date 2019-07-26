package com.farzam.rest.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cmos on 14/09/2018.
 */

public class LockerInfoResponse {
    @SerializedName("MembershipfileID")
    @Expose
    private String MembershipfileID;

    @SerializedName("FullName")
    @Expose
    private String FullName;

    @SerializedName("CardNumber")
    @Expose
    private String CardNumber;

    @SerializedName("LockerNumber")
    @Expose
    private String LockerNumber;

    @SerializedName("PoolReceptionID")
    @Expose
    private String PoolReceptionID;

    @SerializedName("PoolReceiptionAssignLocker")
    @Expose
    private String PoolReceiptionAssignLocker;

    public String getPoolReceiptionAssignLocker() {
        return PoolReceiptionAssignLocker;
    }

    public void setPoolReceiptionAssignLocker(String poolReceiptionAssignLocker) {
        PoolReceiptionAssignLocker = poolReceiptionAssignLocker;
    }

    public String getPoolReceptionID() {
        return PoolReceptionID;
    }

    public void setPoolReceptionID(String poolReceptionID) {
        PoolReceptionID = poolReceptionID;
    }

    public String getMembershipfileID() {
        return MembershipfileID;
    }

    public void setMembershipfileID(String membershipfileID) {
        MembershipfileID = membershipfileID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public String getLockerNumber() {
        return LockerNumber;
    }

    public void setLockerNumber(String lockerNumber) {
        LockerNumber = lockerNumber;
    }
}

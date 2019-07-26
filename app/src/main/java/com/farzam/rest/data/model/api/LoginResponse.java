package com.farzam.rest.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cmos on 13/09/2018.
 */

public class LoginResponse {

    @SerializedName("UserID")
    @Expose
    private String UserID;

    @SerializedName("Name")
    @Expose
    private String Name;

    @SerializedName("OrganizationalPosition")
    @Expose
    private String OrganizationalPosition;

    @SerializedName("Gender")
    @Expose
    private String Gender;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOrganizationalPosition() {
        return OrganizationalPosition;
    }

    public void setOrganizationalPosition(String organizationalPosition) {
        OrganizationalPosition = organizationalPosition;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}

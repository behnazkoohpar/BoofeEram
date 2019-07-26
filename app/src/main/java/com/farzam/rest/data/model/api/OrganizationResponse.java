package com.farzam.rest.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cmos on 28/06/2018.
 */

public class OrganizationResponse {
    @SerializedName("OrganizationUnitID")
    @Expose
    private String OrganizationUnitID;

    @SerializedName("Name")
    @Expose
    private String Name;

    public String getOrganizationUnitID() {
        return OrganizationUnitID;
    }

    public void setOrganizationUnitID(String organizationUnitID) {
        OrganizationUnitID = organizationUnitID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

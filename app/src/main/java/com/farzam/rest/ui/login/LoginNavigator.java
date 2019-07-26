package com.farzam.rest.ui.login;

import com.farzam.rest.data.model.api.LoginResponse;
import com.farzam.rest.data.model.api.OrganizationResponse;
import com.farzam.rest.data.model.api.ReceptionResponse;

import java.util.List;

/**
 * behnaz on 11/3/17.
 */

public interface LoginNavigator {

    void login();

    void openState();

    void openCity();

    void setOrganization(List<OrganizationResponse> stateResponses);

    void setReception(List<ReceptionResponse> receptionResponses);

    void goToStart(List<LoginResponse> loginResponses);
}

package com.farzam.rest.data.pref;

import com.farzam.rest.data.DataManager;

/**
 * behnaz on 10/29/2017.
 */

public interface PreferencesHelper {

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    String getSecurityKey();

    void setSecurityKey(String userId);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getProfilePicture();

    void setProfilePicture(String profilePicture);

    String getUserId();

    void setUserId(String userId);

    String getGender();

    void setGender(String gender);

    String getOrganizationalPosition();

    void setOrganizationalPosition(String organizationalPosition);

    String getReception();

    void setReception(String reception);

    boolean getPrefKeyFactor();

    void setPrefKeyFactor(boolean factor);

    boolean getPrefKeyPrint();

    void setPrefKeyPrint(boolean factor);

    boolean getPrefKeyDontUse();

    void setPrefKeyDontUse(boolean dontUse);

    boolean getPrefKeyOrder();

    void setPrefKeyOrder(boolean order);
}

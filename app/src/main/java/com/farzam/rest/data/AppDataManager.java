package com.farzam.rest.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.farzam.rest.data.pref.PreferencesHelper;
import com.google.gson.Gson;

import javax.inject.Inject;


/**
 * behnaz on 10/29/2017.
 */

public class AppDataManager implements DataManager {

    private final Context mContext;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(Context context,
                          PreferencesHelper preferencesHelper) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public String getUsername() {
        return mPreferencesHelper.getUsername();
    }

    @Override
    public void setUsername(String username) {
        mPreferencesHelper.setUsername(username);
    }

    @Override
    public String getPassword() {
        return mPreferencesHelper.getPassword();
    }

    @Override
    public void setPassword(String password) {
        mPreferencesHelper.setPassword(password);
    }

    @Override
    public String getSecurityKey() {
        return mPreferencesHelper.getSecurityKey();
    }

    @Override
    public void setSecurityKey(String securityKey) {
        mPreferencesHelper.setSecurityKey(securityKey);
    }

    @Override
    public String getFirstName() {
        return mPreferencesHelper.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        mPreferencesHelper.setFirstName(firstName);
    }

    @Override
    public String getLastName() {
        return mPreferencesHelper.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        mPreferencesHelper.setLastName(lastName);
    }

    @Override
    public String getProfilePicture() {
        return mPreferencesHelper.getProfilePicture();
    }

    @Override
    public void setProfilePicture(String profilePicture) {
        mPreferencesHelper.setProfilePicture(profilePicture);
    }

    @Override
    public String getUserId() {
        return mPreferencesHelper.getUserId();
    }

    @Override
    public void setUserId(String userId) {
        mPreferencesHelper.setUserId(userId);
    }

    @Override
    public String getGender() {
        return mPreferencesHelper.getGender();
    }

    @Override
    public void setGender(String gender) {
        mPreferencesHelper.setGender(gender);
    }

    @Override
    public String getOrganizationalPosition() {
        return mPreferencesHelper.getOrganizationalPosition();
    }

    @Override
    public void setOrganizationalPosition(String organizationalPosition) {
        mPreferencesHelper.setOrganizationalPosition(organizationalPosition);
    }

    @Override
    public String getReception() {
        return mPreferencesHelper.getReception();
    }

    @Override
    public void setReception(String reception) {
        mPreferencesHelper.setReception(reception);
    }

    @Override
    public boolean getPrefKeyFactor() {
        return mPreferencesHelper.getPrefKeyFactor();
    }

    @Override
    public void setPrefKeyFactor(boolean factor) {
        mPreferencesHelper.setPrefKeyFactor(factor);
    }

    @Override
    public boolean getPrefKeyDontUse() {
        return mPreferencesHelper.getPrefKeyDontUse();
    }

    @Override
    public void setPrefKeyDontUse(boolean factor) {
        mPreferencesHelper.setPrefKeyDontUse(factor);
    }

    @Override
    public boolean getPrefKeyPrint() {
        return mPreferencesHelper.getPrefKeyPrint();
    }

    @Override
    public void setPrefKeyPrint(boolean print) {
        mPreferencesHelper.setPrefKeyPrint(print);
    }

    @Override
    public boolean getPrefKeyOrder() {
        return mPreferencesHelper.getPrefKeyOrder();
    }

    @Override
    public void setPrefKeyOrder(boolean order) {
        mPreferencesHelper.setPrefKeyOrder(order);
    }

    @Override
    public boolean getShowAzad() {
        return mPreferencesHelper.getShowAzad();
    }

    @Override
    public void setShowAzad(boolean azad) {
        mPreferencesHelper.setShowAzad(azad);
    }

    @Override
    public boolean getShowPersone() {
        return mPreferencesHelper.getShowPersone();
    }

    @Override
    public void setShowPerson(boolean person) {
        mPreferencesHelper.setShowPerson(person);
    }

    @Override
    public boolean getShowMembership() {
        return mPreferencesHelper.getShowMembership();
    }

    @Override
    public void setShowMembership(boolean membership) {
        mPreferencesHelper.setShowMembership(membership);
    }

    @Override
    public void updateUserInfo(
            LoggedInMode loggedInMode,
            String vUserName,
            String vPassword,
            boolean dontUse,
            boolean print,
            boolean factor,
            boolean order) {
        setCurrentUserLoggedInMode(loggedInMode);
        setUsername(vUserName);
        setPassword(vPassword);
        setPrefKeyDontUse(dontUse);
        setPrefKeyPrint(print);
        setPrefKeyFactor(factor);
        setPrefKeyOrder(order);
    }

    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
                "",
                "", false
                , false, false, false);
    }

}

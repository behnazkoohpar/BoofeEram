package com.farzam.rest.data.pref;

import android.content.Context;
import android.content.SharedPreferences;

import com.farzam.rest.data.DataManager;
import com.farzam.rest.di.PreferenceInfo;

import javax.inject.Inject;

/**
 * behnaz on 10/29/2017.
 */

public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";
    private static final String PREF_KEY_USERNAME = "PREF_KEY_USERNAME";
    private static final String PREF_KEY_PASSWORD = "PREF_KEY_PASSWORD";
    private static final String PREF_KEY_SECURITY_KEY = "PREF_KEY_SECURITY_KEY";
    private static final String PREF_KEY_FIRST_NAME = "PREF_KEY_FIRST_NAME";
    private static final String PREF_KEY_LAST_NAME = "PREF_KEY_LAST_NAME";
    private static final String PREF_KEY_PROFILE_PICTURE = "PREF_KEY_PROFILE_PICTURE";
    private static final String PREF_KEY_USER_ID = "PREF_KEY_USER_ID";
    private static final String PREF_KEY_GENDER = "PREF_KEY_GENDER";
    private static final String PREF_KEY_ORGANIZATION_POSITION = "PREF_KEY_ORGANIZATION_POSITION";
    private static final String PREF_KEY_RECEPTION = "PREF_KEY_RECEPTION";
    private static final String PREF_KEY_FACTOR = "PREF_KEY_FACTOR";
    private static final String PREF_KEY_PRINT = "PREF_KEY_PRINT";
    private static final String PREF_KEY_ORDER = "PREF_KEY_ORDER";
    private static final String PREF_KEY_DONTUSE = "PREF_KEY_DONTUSE";
    private static final String PREF_KEY_MEMBERSHIP = "PREF_KEY_MEMBERSHIP";
    private static final String PREF_KEY_AZAD = "PREF_KEY_AZAD";
    private static final String PREF_KEY_PERSON = "PREF_KEY_PERSON";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public String getUsername() {
        return mPrefs.getString(PREF_KEY_USERNAME, "");
    }

    @Override
    public void setUsername(String username) {
        mPrefs.edit().putString(PREF_KEY_USERNAME, username).apply();
    }

    @Override
    public String getPassword() {
        return mPrefs.getString(PREF_KEY_PASSWORD, "");
    }

    @Override
    public void setPassword(String password) {
        mPrefs.edit().putString(PREF_KEY_PASSWORD, password).apply();
    }

    @Override
    public String getFirstName() {
        return mPrefs.getString(PREF_KEY_FIRST_NAME, "");
    }

    @Override
    public void setFirstName(String firstName) {
        mPrefs.edit().putString(PREF_KEY_FIRST_NAME, firstName).apply();
    }

    @Override
    public String getLastName() {
        return mPrefs.getString(PREF_KEY_LAST_NAME, "");
    }

    @Override
    public void setLastName(String lastName) {
        mPrefs.edit().putString(PREF_KEY_LAST_NAME, lastName).apply();
    }

    @Override
    public String getProfilePicture() {
        return mPrefs.getString(PREF_KEY_PROFILE_PICTURE, "");
    }

    @Override
    public void setProfilePicture(String profilePicture) {
        mPrefs.edit().putString(PREF_KEY_PROFILE_PICTURE, profilePicture).apply();
    }

    @Override
    public String getSecurityKey() {
        return mPrefs.getString(PREF_KEY_SECURITY_KEY, "");
    }

    @Override
    public void setSecurityKey(String securityKey) {
        mPrefs.edit().putString(PREF_KEY_SECURITY_KEY, securityKey).apply();
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
    }

    @Override
    public void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }

    @Override
    public String getUserId() {
        return mPrefs.getString(PREF_KEY_USER_ID, "");
    }

    @Override
    public void setUserId(String userId) {
        mPrefs.edit().putString(PREF_KEY_USER_ID, userId).apply();
    }


    @Override
    public String getGender() {
        return mPrefs.getString(PREF_KEY_GENDER, "");
    }

    @Override
    public void setGender(String gender) {
        mPrefs.edit().putString(PREF_KEY_GENDER, gender).apply();
    }


    @Override
    public String getOrganizationalPosition() {
        return mPrefs.getString(PREF_KEY_ORGANIZATION_POSITION, "");
    }

    @Override
    public void setOrganizationalPosition(String organizationalPosition) {
        mPrefs.edit().putString(PREF_KEY_ORGANIZATION_POSITION, organizationalPosition).apply();
    }

    @Override
    public String getReception() {
        return mPrefs.getString(PREF_KEY_RECEPTION, "");
    }

    @Override
    public void setReception(String reception) {
        mPrefs.edit().putString(PREF_KEY_RECEPTION, reception).apply();
    }

    @Override
    public boolean getPrefKeyFactor() {
        return mPrefs.getBoolean(PREF_KEY_FACTOR,false);
    }

    @Override
    public void setPrefKeyFactor(boolean factor) {
        mPrefs.edit().putBoolean(PREF_KEY_FACTOR, factor).apply();
    }

    @Override
    public boolean getPrefKeyPrint() {
        return mPrefs.getBoolean(PREF_KEY_PRINT, false);
    }

    @Override
    public void setPrefKeyPrint(boolean print) {
        mPrefs.edit().putBoolean(PREF_KEY_PRINT, print).apply();
    }

    @Override
    public boolean getPrefKeyDontUse() {
        return mPrefs.getBoolean(PREF_KEY_DONTUSE, false);
    }

    @Override
    public void setPrefKeyDontUse(boolean dontUse) {
        mPrefs.edit().putBoolean(PREF_KEY_DONTUSE, dontUse).apply();
    }

    @Override
    public boolean getPrefKeyOrder() {
        return mPrefs.getBoolean(PREF_KEY_ORDER, false);
    }

    @Override
    public void setPrefKeyOrder(boolean order) {
        mPrefs.edit().putBoolean(PREF_KEY_ORDER, order).apply();
    }

    @Override
    public boolean getShowAzad() {
        return mPrefs.getBoolean(PREF_KEY_AZAD, false);
    }

    @Override
    public void setShowAzad(boolean azad) {
        mPrefs.edit().putBoolean(PREF_KEY_AZAD, azad).apply();
    }

    @Override
    public boolean getShowPersone() {
        return mPrefs.getBoolean(PREF_KEY_PERSON, false);
    }

    @Override
    public void setShowPerson(boolean person) {
        mPrefs.edit().putBoolean(PREF_KEY_PERSON, person).apply();
    }

    @Override
    public boolean getShowMembership() {
        return mPrefs.getBoolean(PREF_KEY_MEMBERSHIP, false);
    }

    @Override
    public void setShowMembership(boolean membership) {
        mPrefs.edit().putBoolean(PREF_KEY_MEMBERSHIP, membership).apply();
    }
}

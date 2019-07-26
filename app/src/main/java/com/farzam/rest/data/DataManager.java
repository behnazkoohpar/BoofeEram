package com.farzam.rest.data;

import com.farzam.rest.data.pref.PreferencesHelper;

/**
 * behnaz on 10/29/2017.
 */

public interface DataManager extends PreferencesHelper {

    void setUserAsLoggedOut();
    void updateUserInfo(
            LoggedInMode loggedInMode,
            String vUserName,
            String vPassword,
            boolean dontUse,
            boolean print,
            boolean factor,
            boolean order);

    enum LoggedInMode {
        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_SERVER(1);
        private final int mType;
        LoggedInMode(int type) {
            mType = type;
        }
        public int getType() {
            return mType;
        }
    }

}

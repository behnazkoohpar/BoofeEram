package com.farzam.rest.utils;

/**
 * behnaz on 10/29/2017.
 */
public interface AppConstants {

//    String BASE_URL = "http://5.202.192.147:5000";
    String BASE_URL = "http://192.168.120.7:5000";
    String PREF_NAME = "rest_pref";
    String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    boolean LOGTRUE = false;

    // Headers
    String HEADER_SECURITY_KEY = "security_key";
    String HEADER_TYPE = "type";

    int API_CALL_LOGIN = 1001;
    int API_CALL_PROFILE_USER = 1002;
    int API_CALL_ALL_BANER_LIST = 1003;
    int API_CALL_ALL_SPECIAL_OFFER = 1004;
    int API_CALL_ORGANIZATION = 1005;
    int API_CALL_PAZIRESH = 1006;
    int API_CALL_LOCKER_INFO = 1007;
    int API_CALL_HISTORY = 1008;
    int API_CALL_HISTORY2 = 1012;
    int API_CALL_HISTORY3 = 1013;
    int API_CALL_CHECK_CARD = 1009;
    int API_CALL_STUFF_LIST = 1010;
    int API_CALL_SET_FACTOR = 1011;
    int API_CALL_LIST_FACTOR = 1012;
    int API_CALL_CHECK_PERSON = 1014;
    int API_CALL_SET_FACTOR_AZAD = 1015;


    String REQUEST_KEY_USER_NAME = "user_name";
    String REQUEST_KEY_PASSWORD = "password";
    String REQUEST_KEY_LOCKER_NUMBER = "locker_number";
    String REQUEST_KEY_GENDER = "gender_id";
    String REQUEST_KEY_MEMBERSHIP_FILE_ID = "membershipfile_id";
    String REQUEST_KEY_CARD_NUMBER = "card_number";
    String REQUEST_KEY_RECEPTON_UNIT = "reception_unit";
    String REQUEST_KEY_ORGANIZATION_UNIT = "organization_unit";
    String REQUEST_KEY_USER_ID = "user_id";
    String REQUEST_KEY_TOTAL_PRICE = "total_price";
    String REQUEST_KEY_POOL_RECEPTION_ASSIGN_LOCKER = "pool_receiption_assign_locker";
    String REQUEST_KEY_POOL_RECEPTION = "pool_receiption";
    String REQUEST_KEY_DETAIL_FACTOR = "detailes_factor";
    String REQUEST_KEY_FROM_DATE = "from_date";
    String REQUEST_KEY_FROM_TIME = "from_time";
    String REQUEST_KEY_TO_DATE = "to_date";
    String REQUEST_KEY_TO_TIME = "to_time";


}


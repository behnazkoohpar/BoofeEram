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
    int API_CALL_CHECK_CARD = 1009;
    int API_CALL_STUFF_LIST = 1010;
    int API_CALL_SET_FACTOR = 1011;
    int API_CALL_LIST_FACTOR = 1012;
    int API_CALL_HISTORY3 = 1013;
    int API_CALL_CHECK_PERSON = 1014;
    int API_CALL_SET_FACTOR_AZAD = 1015;
    int API_CALL_HISTORY2 = 1016;
    int API_CALL_NAME_FAMILY_PERSON = 1017;
    int API_CALL_NUMBER_LOCKER_CARD = 1018;
    int API_CALL_NAME_MEMBERSHIP = 1019;
    int API_CALL_TEL_MEMBERSHIP = 1020;
    int API_CALL_PERSON_NAME_FAMILY = 1021;
    int API_CALL_CODE_GHARARDAD = 1022;
    int API_CALL_LIST_DETAIL_FACTOR = 102;

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
    String REQUEST_KEY_LOCKER_NAME_FAMILY = "name";
    String REQUEST_KEY_NUMBER_LOCKER_CARD = "card_number";
    String REQUEST_KEY_CODE_GHARARDAD = "finger_print";
    String REQUEST_KEY_TEL_MEMBERSHIP = "phone";
    String REQUEST_KEY_IS_PERSON = "is_person";
    String REQUEST_KEY_IS_DELIVERED = "is_delivered";
    String REQUEST_KEY_IS_RECEIPT = "is_receipt";
    String REQUEST_KEY_SALES_INVOICE_ID = "sales_invoice_id";


}


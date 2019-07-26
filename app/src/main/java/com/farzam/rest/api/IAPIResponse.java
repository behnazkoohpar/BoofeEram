package com.farzam.rest.api;

/**
 * behnaz on 10/31/2017.
 */

public interface IAPIResponse {

    void onResponseSuccess(Object mObject, int requestCode);
    void onResponseFailed(String errorMsg,int responseCode , int requestCode);
    void onFailedAuth(String errorMsg , int requestCode );

}

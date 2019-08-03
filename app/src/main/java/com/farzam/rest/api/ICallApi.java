package com.farzam.rest.api;

import android.app.DownloadManager;

import com.farzam.rest.data.model.api.CardInfoResponse;
import com.farzam.rest.data.model.api.HistoryResponse;
import com.farzam.rest.data.model.api.ListFactorResponse;
import com.farzam.rest.data.model.api.LockerInfoResponse;
import com.farzam.rest.data.model.api.LoginResponse;
import com.farzam.rest.data.model.api.OrganizationResponse;
import com.farzam.rest.data.model.api.ProfileUserResponse;
import com.farzam.rest.data.model.api.ReceptionResponse;
import com.farzam.rest.data.model.api.StuffListResponse;
import com.farzam.rest.data.model.api.base.Data;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Create on 10/31/2017.
 */

public interface ICallApi {

    @FormUrlEncoded
    @POST("/WS/get_user_info")
    Call<Data<LoginResponse>> login(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("/WS/check_activation_code")
    Call<Data> activation(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("/WS/get_all_organization_unit")
    Call<Data<OrganizationResponse>> getUnitOrganization(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("/WS/get_all_reception_unit")
    Call<Data<ReceptionResponse>> getUnitReception(@FieldMap HashMap<String, String> map);


    @FormUrlEncoded
    @POST("/WS/get_locker_info")
    Call<Data<LockerInfoResponse>> getLockerInfo(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("/WS/get_history_factor_item_from_membership")
    Call<Data<HistoryResponse>> getHistory(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("/WS/get_history_card_factor_item_from_membership")
    Call<Data<HistoryResponse>> getHistoryCard(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("/WS/get_history_card_factor_item_from_membership")
    Call<Data<HistoryResponse>> getHistory3(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("/WS/get_card_info")
    Call<Data<CardInfoResponse>> getCardInfo(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("/WS/get_stuff_list_in_unit")
    Call<Data<StuffListResponse>> getAllStuffList(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("/WS/set_factor_for_locker")
    Call<Data> callSetFactor(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("/WS/set_factor_for_personel")
    Call<Data> callSetFactorPerson(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("/WS/set_factor_for_card")
    Call<Data> callSetFactorCard(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("/WS/set_factor_for_azad")
    Call<Data> callSetFactorAzad(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("/WS/get_list_factor")
    Call<Data<ListFactorResponse>> getListFactor(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("/WS/search_by_name_locker")
    Call<Data<LockerInfoResponse>> callNameFamilyPerson(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("/WS/search_by_card_number_locker")
    Call<Data<LockerInfoResponse>> callPersonNumberLockerCard(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("/WS/search_by_name_member")
    Call<Data<CardInfoResponse>> callNameMemberShip(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("/WS/search_by_phone_member")
    Call<Data<CardInfoResponse>> callTelMemberShip(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("/WS/search_by_name_person")
    Call<Data<CardInfoResponse>> callPersonNameAndFamily(@FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("/WS/search_by_finger_print_person")
    Call<Data<CardInfoResponse>> callCodeGharardad(@FieldMap HashMap<String, String> map);
}

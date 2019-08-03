package com.farzam.rest.ui.start;

import com.farzam.rest.data.model.api.CardInfoResponse;
import com.farzam.rest.data.model.api.HistoryResponse;
import com.farzam.rest.data.model.api.LockerInfoResponse;

import java.util.List;

/**
 * Created by cmos on 07/09/2018.
 */

public interface StartNavigator {
    void callLockerInfo();

    void setPersonInfo(List<CardInfoResponse> cardInfo,int position);

    void setCardInfo(List<CardInfoResponse> cardInfo,int position);

    void getHistory();

    void setHistoryLocker(List<HistoryResponse> historyResponses);

    void callCheckCard();

    void setLockerInfo(List<LockerInfoResponse> loginResponses,int position);

    void getHistoryCard();

    void getHistoryPersonli();

    void setHistoryCard(List<HistoryResponse> historyResponses2);
    void setHistoryPersoneli(List<HistoryResponse> historyResponses3);

    void openMenu();

    void callPersonInfo();

    void setDataNull();

    void callLockerName();

    void callLockerCard();

    void callCardNameFamily();

    void callCardTel();

    void callPersonNameFamily();

    void callPersonCodeGharardad();

    void setLockerCard(List<LockerInfoResponse> loginResponsesCard);

    void setLockerName(List<LockerInfoResponse> lockerInfoResponses);

    void setCardName(List<CardInfoResponse> cardInfoResponses1);

    void setCardTel(List<CardInfoResponse> cardInfoResponses2);

    void setPersonName(List<CardInfoResponse> cardInfoResponses3);

    void setPersonGharadad(List<CardInfoResponse> cardInfoResponses4);
}

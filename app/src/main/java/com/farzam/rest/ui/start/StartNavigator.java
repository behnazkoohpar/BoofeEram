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

    void setPersonInfo(List<CardInfoResponse> cardInfo);

    void setCardInfo(List<CardInfoResponse> cardInfo);

    void getHistory();

    void setHistoryLocker(List<HistoryResponse> historyResponses);

    void callCheckCard();

    void setLockerInfo(List<LockerInfoResponse> loginResponses);

    void getHistoryCard();

    void getHistoryPersonli();

    void setHistoryCard(List<HistoryResponse> historyResponses2);
    void setHistoryPersoneli(List<HistoryResponse> historyResponses3);

    void openMenu();

    void callPersonInfo();

    void setDataNull();

}

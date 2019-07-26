package com.farzam.rest.data.model.api.base;

/**
 * Created by Priyesh Bhargava on 2/15/16.
 */
public class DataObject<T> extends BaseResponse {

    protected T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

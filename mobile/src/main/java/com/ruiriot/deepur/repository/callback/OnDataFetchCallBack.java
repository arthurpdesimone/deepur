package com.ruiriot.deepur.repository.callback;

public interface OnDataFetchCallBack <T> {
    void onFetch(T data);
    void onError(Exception e);
}

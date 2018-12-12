package com.ruiriot.deepur.repository.callback;

public interface OnDataSavedCallBack {
    void onSave();
    void onError(Exception e);
}

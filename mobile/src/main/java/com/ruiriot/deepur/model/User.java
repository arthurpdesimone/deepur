package com.ruiriot.deepur.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ruiriot on 30/08/17.
 */

public class User implements Parcelable {

    private String uid;
    private String name;
    private String email;
    private String photoUrl;
    private String pushToken;

    private boolean isAdmin;

    public User(String uid, String name, String email, String url) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.photoUrl = url;
    }

    public User() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uid);
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.photoUrl);
    }

    private User(Parcel in) {
        this.uid = in.readString();
        this.name = in.readString();
        this.email = in.readString();
        this.photoUrl = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }
}

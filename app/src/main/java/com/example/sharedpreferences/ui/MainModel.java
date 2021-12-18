package com.example.sharedpreferences.ui;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainModel implements Parcelable {

    @SerializedName("pk")
    @Expose
    private int pk;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private int gender;
    @SerializedName("user_image")
    @Expose
    private String userImage;

    protected MainModel(Parcel in) {
        pk = in.readInt();
        username = in.readString();
        mobileNo = in.readString();
        email = in.readString();
        gender = in.readInt();
        userImage = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(pk);
        dest.writeString(username);
        dest.writeString(mobileNo);
        dest.writeString(email);
        dest.writeInt(gender);
        dest.writeString(userImage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MainModel> CREATOR = new Creator<MainModel>() {
        @Override
        public MainModel createFromParcel(Parcel in) {
            return new MainModel(in);
        }

        @Override
        public MainModel[] newArray(int size) {
            return new MainModel[size];
        }
    };

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}

package com.example.nelson.presentation.model;


import com.example.nelson.presentation.view.model.EmptyViewModel;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;

/**
 * Created by Nelson on 13/08/2016.
 */
@Parcel(Parcel.Serialization.BEAN)
public class HeaderInfoModel extends EmptyViewModel {

    String playerName;

    int balance;

    URL avatarURL;

    Date lastLogindate;

    @ParcelConstructor
    public HeaderInfoModel(String playerName, int balance, URL avatarURL, Date lastLogindate) {
        this.playerName = playerName;
        this.balance = balance;
        this.avatarURL = avatarURL;
        this.lastLogindate = lastLogindate;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getBalance() {
        return balance;
    }

    public URL getAvatarURL() {
        return avatarURL;
    }

    public Date getLastLogindate() {
        return lastLogindate;
    }

    @Override
    public String toString() {
        return "HeaderInfoModel{" +
            "playerName='" + playerName + '\'' +
            ", balance=" + balance +
            ", avatarURL=" + avatarURL +
            ", lastLogindate=" + lastLogindate.toString() +
            '}';
    }
}

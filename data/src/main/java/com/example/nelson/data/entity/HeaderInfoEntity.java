package com.example.nelson.data.entity;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.net.URL;

/**
 * Created by Nelson on 13/08/2016.
 */
public class HeaderInfoEntity extends TestResponse {

    @SerializedName("name")
    String playerName;

    @SerializedName("balance")
    int balance;

    @SerializedName("avatarLink")
    URL avatarURL;

    @SerializedName("lastLogindate")
    DateTime lastLogindate;


    public HeaderInfoEntity() {
        super();
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

    public DateTime getLastLogindate() {
        return lastLogindate;
    }

    @Override
    public String toString() {
        return "HeaderInfoEntity{" +
            "playerName='" + playerName + '\'' +
            ", balance=" + balance +
            ", avatarURL=" + avatarURL +
            ", lastLogindate=" + lastLogindate +
            '}';
    }
}

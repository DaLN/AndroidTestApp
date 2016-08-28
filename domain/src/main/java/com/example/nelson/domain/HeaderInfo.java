package com.example.nelson.domain;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.net.URL;

/**
 * Created by Nelson on 13/08/2016.
 */
public class HeaderInfo {

    String playerName;

    int balance;

    URL avatarURL;

    DateTime lastLogindate;

    public HeaderInfo(String playerName, int balance, URL avatarURL, DateTime lastLogindate) {
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

    public DateTime getLastLogindate() {
        return lastLogindate;
    }

    @Override
    public String toString() {
        return "HeaderInfo{" +
            "playerName='" + playerName + '\'' +
            ", balance=" + balance +
            ", avatarURL=" + avatarURL +
            ", lastLogindate=" + lastLogindate +
            '}';
    }
}

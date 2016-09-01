package com.example.nelson.domain;

import java.net.URL;
import java.util.Date;

/**
 * Created by Nelson on 13/08/2016.
 */
public class HeaderInfo {

    String playerName;

    int balance;

    URL avatarURL;

    Date lastLogindate;

    public HeaderInfo(String playerName, int balance, URL avatarURL, Date lastLogindate) {
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
        return "HeaderInfo{" +
            "playerName='" + playerName + '\'' +
            ", balance=" + balance +
            ", avatarURL=" + avatarURL +
            ", lastLogindate=" + lastLogindate +
            '}';
    }
}

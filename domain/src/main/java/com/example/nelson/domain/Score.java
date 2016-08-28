package com.example.nelson.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nelson on 14/08/2016.
 */
public class Score {
  @SerializedName("name")
  String name;

  @SerializedName("jackpot")
  String jackpot;

  @SerializedName("date")
  String date;

  public Score(String name, String jackpot, String date) {
    this.name = name;
    this.jackpot = jackpot;
    this.date = date;
  }

  public String getName() {
    return name;
  }

  public String getJackpot() {
    return jackpot;
  }

  public String getDate() {
    return date;
  }

  @Override
  public String toString() {
    return "Score{"
        + "name='" + name + '\''
        + ", jackpot='" + jackpot + '\''
        + ", date='" + date + '\''
        + '}';
  }
}

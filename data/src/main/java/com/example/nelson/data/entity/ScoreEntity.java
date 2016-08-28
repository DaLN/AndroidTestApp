package com.example.nelson.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nelson on 14/08/2016.
 */
public class ScoreEntity {
  @SerializedName("name")
  String name;

  @SerializedName("jackpot")
  String jackpot;

  @SerializedName("date")
  String date;


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
    return "ScoreEntity{"
        + "name='" + name + '\''
        + ", jackpot='" + jackpot + '\''
        + ", date='" + date + '\''
        + '}';
  }
}

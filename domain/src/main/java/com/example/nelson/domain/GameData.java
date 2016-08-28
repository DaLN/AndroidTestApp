package com.example.nelson.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nelson on 13/08/2016.
 */
public class GameData {

  @SerializedName("data")
  List<Score> scoreList;

  @SerializedName("response")
  String response;

  @SerializedName("currency")
  String currency;

  public GameData(List<Score> scoreList, String response, String currency) {
    this.scoreList = scoreList;
    this.response = response;
    this.currency = currency;
  }

  public List<Score> getScoreList() {
    return scoreList;
  }

  public String getResponse() {
    return response;
  }

  public String getCurrency() {
    return currency;
  }

  @Override
  public String toString() {
    return "GameData{" +
        "scoreList=" + scoreList.toString() +
        ", response='" + response + '\'' +
        ", currency='" + currency + '\'' +
        '}';
  }
}

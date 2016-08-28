package com.example.nelson.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nelson on 13/08/2016.
 */
public class GameDataEntity extends TestResponse {

  @SerializedName("data")
  List<ScoreEntity> scoreEntityList;

  @SerializedName("response")
  String response;

  @SerializedName("currency")
  String currency;

  public GameDataEntity() {
    super();
  }

  public List<ScoreEntity> getScoreEntityList() {
    return scoreEntityList;
  }

  public String getResponse() {
    return response;
  }

  public String getCurrency() {
    return currency;
  }

  @Override
  public String toString() {
    return "GameDataEntity{" +
        "scoreEntityList=" + scoreEntityList.toString() +
        ", response='" + response + '\'' +
        ", currency='" + currency + '\'' +
        '}';
  }
}

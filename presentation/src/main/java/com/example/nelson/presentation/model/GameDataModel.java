package com.example.nelson.presentation.model;

import java.util.List;

/**
 * Created by Nelson on 13/08/2016.
 */
public class GameDataModel {

  List<ScoreModel> scoreList;

  String response;

  String currency;

  public GameDataModel(List<ScoreModel> scoreList, String response, String currency) {
    this.scoreList = scoreList;
    this.response = response;
    this.currency = currency;
  }

  public List<ScoreModel> getScoreList() {
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
    return "GameDataModel{" +
        "scoreList=" + scoreList.toString() +
        ", response='" + response + '\'' +
        ", currency='" + currency + '\'' +
        '}';
  }
}

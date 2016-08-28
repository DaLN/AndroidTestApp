package com.example.nelson.presentation.model;

/**
 * Created by Nelson on 14/08/2016.
 */
public class ScoreModel {
  String name;

  String jackpot;

  String date;

  public ScoreModel(String name, String jackpot, String date) {
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
    return "ScoreModel{"
        + "name='" + name + '\''
        + ", jackpot='" + jackpot + '\''
        + ", date='" + date + '\''
        + '}';
  }
}

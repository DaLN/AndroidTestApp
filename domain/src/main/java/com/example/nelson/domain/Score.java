package com.example.nelson.domain;

import java.util.Date;

/**
 * Created by Nelson on 14/08/2016.
 */
public class Score {
  String name;

  String jackpot;

  Date date;

  public Score(String name, String jackpot, Date date) {
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

  public Date getDate() {
    return date;
  }

  @Override
  public String toString() {
    return "Score{"
        + "name='" + name + '\''
        + ", jackpot='" + jackpot + '\''
        + ", date='" + date.toString() + '\''
        + '}';
  }
}

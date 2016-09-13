package com.example.nelson.presentation.model;

import com.example.nelson.presentation.view.model.EmptyViewModel;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.Date;

/**
 * Created by Nelson on 14/08/2016.
 */
@Parcel(Parcel.Serialization.BEAN)
public class ScoreModel {
  String name;

  String jackpot;

  Date date;

  @ParcelConstructor
  public ScoreModel(String name, String jackpot, Date date) {
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
    return "ScoreModel{"
        + "name='" + name + '\''
        + ", jackpot='" + jackpot + '\''
        + ", date='" + date.toString() + '\''
        + '}';
  }
}

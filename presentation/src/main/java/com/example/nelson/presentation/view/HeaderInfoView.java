package com.example.nelson.presentation.view;

import com.example.nelson.presentation.model.HeaderInfoModel;

import org.parceler.Parcel;

/**
 * Created by Nelson on 04/09/2016.
 */
public interface HeaderInfoView extends LceView<HeaderInfoModel, HeaderInfoView.HeaderInfoError> {

  void showLastLoginDate();

  void hideLastLoginDate();

  @Parcel
  enum HeaderInfoError {
    GENERAL
  }
}

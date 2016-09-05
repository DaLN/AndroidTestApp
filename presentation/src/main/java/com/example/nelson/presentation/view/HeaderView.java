package com.example.nelson.presentation.view;

import com.example.nelson.presentation.model.HeaderInfoModel;

/**
 * Created by Nelson on 04/09/2016.
 */
public interface HeaderView extends LceView<HeaderInfoModel, HeaderView.ScoreListError> {
  public enum ScoreListError {
    GENERAL
  }
}

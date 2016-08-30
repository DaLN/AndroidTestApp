package com.example.nelson.presentation.view;

import android.content.Context;

/**
 * Created by Nelson on 16/08/2016.
 */
public interface LoadDataView {
  //void refreshScores(List<ScoreModel> scores);

  /**
   * Indicate that the view is loading data.
   */
  void showLoading();

  /**
   * Remove the indication that the view is loading data.
   */
  void hideLoading();

  /**
   * Indicate that the view is retrying to load the data after an error.
   */
  void showRetry();

  /**
   * Remove the indication that the view is retrying to load the data.
   */
  void hideRetry();

  /**
   * Show an error message
   *
   * @param message Error message.
   */
  void showError(String message);

  /**
   * Get a {@link android.content.Context}.
   */
  Context getContext();
}

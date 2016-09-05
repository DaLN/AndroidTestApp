package com.example.nelson.presentation.view;

import android.content.Context;

import com.example.nelson.presentation.view.model.EmptyViewModel;

/**
 * Interface for Load-Content-Error view
 * From [here] (https://github.com/nbarishok/RxMvpAndroid/blob/master/app/src/main/java/com/onemanparty/rxmvpandroid/core/view/LCEView.java)
 */
public interface LceView<D extends EmptyViewModel, E> extends View {
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
   *
   * @param data
   */
  void setData(D data);

  void loadData();

  /**
   *
   */
  void showContent();

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

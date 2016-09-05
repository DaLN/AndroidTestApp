package com.example.nelson.presentation.view.layout;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.nelson.presentation.R;
import com.example.nelson.presentation.model.GameDataModel;
import com.example.nelson.presentation.view.GameDataView;

import butterknife.BindView;

/**
 * Created by Nelson on 04/09/2016.
 */
public class GameDataLayout implements GameDataView {

  @BindView(R.id.view_progress)
  RelativeLayout viewProgress;

  @BindView(R.id.view_retry)
  RelativeLayout viewRetry;

  @Override
  public void showLoading() {
    Log.d("NELSON", "showLoading, viewProgress = " + viewProgress);
    this.viewProgress.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideLoading() {
    Log.d("NELSON", "hideLoading, viewProgress = " + viewProgress);
    this.viewProgress.setVisibility(View.GONE);
  }

  /**
   * @param data
   */
  @Override
  public void setData(GameDataModel data) {
    this.gameDataModel = gameDataModel;
  }

  @Override
  public void loadData() {

  }

  /**
   *
   */
  @Override
  public void showContent() {
    adapter.clear();
    adapter.addAll(gameDataModel.getScoreList());
  }

  @Override
  public void showRetry() {
    this.viewRetry.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideRetry() {
    this.viewRetry.setVisibility(View.GONE);
  }

  @Override
  public void showError(String message) {
    this.showToastMessage(message);
  }

  /**
   * Get a {@link Context}.
   */
  @Override
  public Context getContext() {
    return null;
  }
}

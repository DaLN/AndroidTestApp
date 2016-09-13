package com.example.nelson.presentation.view.fragment;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import com.example.nelson.presentation.R;
import com.example.nelson.presentation.model.GameDataModel;
import com.example.nelson.presentation.model.ScoreModel;
import com.example.nelson.presentation.presenter.GameDataPresenter;
import com.example.nelson.presentation.view.GameDataView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nelson on 12/09/2016.
 */
public class GameDataLayout extends RelativeLayout implements GameDataView {

  @BindView(R.id.view_progress_gamedata)
  RelativeLayout viewProgress;

  @BindView(R.id.view_retry_gamedata)
  RelativeLayout viewRetry;

  @Inject
  GameDataPresenter gameDataPresenter;

  private GameDataModel model;

  private ArrayAdapter<ScoreModel> adapter;

  public GameDataLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.bind(this);
  }

  @Override
  public void showLoading() {
    Log.d("NELSON", "GameDataLayout, showLoading, viewProgress = " + viewProgress);
    this.viewProgress.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideLoading() {
    Log.d("NELSON", "GameDataLayout, hideLoading, viewProgress = " + viewProgress);
    this.viewProgress.setVisibility(View.GONE);
  }

  /**
   * @param data
   */
  @Override
  public void setData(GameDataModel data) {
    Log.d("NELSON", "GameDataLayout, setData, data = " + data.toString());
    this.model = data;
  }

  @Override
  public void loadData() {
    Log.d("NELSON", "GameDataLayout, loadData, gameDataPresenter = " + gameDataPresenter);
    gameDataPresenter.callGameData();
  }

  /**
   *
   */
  @Override
  public void showContent() {
    Log.d("NELSON", "GameDataLayout, showContent, model = " + model.toString());
    adapter.clear();
    adapter.addAll(model.getScoreList());
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
  public void showError(GameDataError gameDataError) {

  }

  public void setAdapter(ArrayAdapter<ScoreModel> adapter) {
    this.adapter = adapter;
  }
}

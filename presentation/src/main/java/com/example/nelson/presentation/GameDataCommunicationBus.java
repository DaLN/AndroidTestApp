package com.example.nelson.presentation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.nelson.presentation.model.GameDataModel;
import com.example.nelson.presentation.presenter.GameDataPresenter;
import com.example.nelson.presentation.view.GameDataView;
import com.example.nelson.presentation.view.LceView;

/**
 * Created by Nelson on 04/09/2016.
 */
public class GameDataCommunicationBus implements GameDataView, GameDataPresenter {

  private GameDataView view;
  private GameDataPresenter presenter;
  private static final String VIEW_STATE_KEY = "VIEW_STATE";
  private GameDataViewState viewState;

  public GameDataCommunicationBus(GameDataPresenter presenter) {
    this.presenter = presenter;
    viewState = createViewState();
    presenter.bindView(this);
  }

  private GameDataViewState createViewState() {
    return new GameDataViewState();
  }

  @Override
  public void showLoading() {
    if (view != null) {
      view.showLoading();
    }
  }

  @Override
  public void hideLoading() {
    if (view != null) {
      view.hideLoading();
    }
  }

  @Override
  public void setData(GameDataModel data) {
    if(view != null) {
      view.setData(data);
    }
  }

  @Override
  public void loadData() {
    if (view != null) {
      view.loadData();
    }

  }

  @Override
  public void showContent() {
    if (view != null) {
      view.showContent();
    }
  }

  @Override
  public void showRetry() {
    if (view != null) {
      view.showRetry();
    }
  }

  @Override
  public void hideRetry() {
    if (view != null) {
      view.hideRetry();
    }
  }

  @Override
  public void showError(String message) {
    if (view != null) {
      view.showError(message);
    }
  }

  @Override
  public Context getContext() {
    Context context = null;
    if (view != null) {
      context = view.getContext();
    }
    return context;
  }

  @Override
  public void bindView(LceView view) {
    this.view = (GameDataView) view;
  }

  @Override
  public void unbindView() {
    this.view = null;
  }

  @Override
  public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
    presenter.onCreate(arguments, savedInstanceState);
  }

  @Override
  public void onSaveInstanceState(Bundle bundle) {
    presenter.onSaveInstanceState(bundle);
  }

  @Override
  public void onDestroy() {
    presenter.unbindView();
    presenter.onDestroy();
  }
}

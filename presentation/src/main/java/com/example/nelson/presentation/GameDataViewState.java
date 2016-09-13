package com.example.nelson.presentation;


import com.example.nelson.presentation.model.GameDataModel;
import com.example.nelson.presentation.view.GameDataView;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by Nelson on 04/09/2016.
 */
@Parcel(Parcel.Serialization.BEAN)
public class GameDataViewState implements ViewState<GameDataView> {

  private static final int STATE_UNINITIALIZED = -1;
  private static final int STATE_DEFAULT = 0;
  private static final int STATE_SHOW_LOADING = 1;
  private static final int STATE_SHOW_ERROR = 2;

  private int currentState = STATE_DEFAULT;
  private GameDataView.GameDataError error;
  private GameDataModel model;

  public GameDataViewState() {
    currentState = STATE_UNINITIALIZED;
  }

  @ParcelConstructor
  public GameDataViewState(int currentState, GameDataView.GameDataError error,
                           GameDataModel model) {
    this.currentState = currentState;
    this.error = error;
    this.model = model;
  }

  public void setData(GameDataModel model) {
    this.model = model;
  }

  public void setStateShowContent() {
    currentState = STATE_DEFAULT;
  }

  public void setStateShowLoading() {
    currentState = STATE_SHOW_LOADING;
  }

  public void setStateHideLoading() {
    if (currentState != STATE_SHOW_ERROR) { // to avoid such things use State pattern
      currentState = STATE_DEFAULT;
      error = null;
    }
  }

  public void setStateShowError(GameDataView.GameDataError error) {
    currentState = STATE_SHOW_ERROR;
    this.error = error;
  }

  public GameDataModel getModel() {
    return model;
  }

  public int getCurrentState() {
    return currentState;
  }

  public GameDataView.GameDataError getError() {
    return error;
  }

  @Override
  public void apply(GameDataView view) {
    if (model != null) {
      view.setData(model);
      view.showContent();
    } else {
      view.loadData();
    }
    switch (currentState) {
      case STATE_SHOW_LOADING:
        view.showLoading();
        break;
      case STATE_SHOW_ERROR:
        view.showError(error);
        break;
      default:
        break;
    }
  }
}

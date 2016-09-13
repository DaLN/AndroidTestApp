package com.example.nelson.presentation;

import com.example.nelson.presentation.model.HeaderInfoModel;
import com.example.nelson.presentation.view.HeaderInfoView;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by Nelson on 04/09/2016.
 */
@Parcel(Parcel.Serialization.BEAN)
public class HeaderInfoViewState implements ViewState<HeaderInfoView> {

  private static final int STATE_UNINITIALIZED = -1;
  private static final int STATE_DEFAULT = 0;
  private static final int STATE_SHOW_LOADING = 1;
  private static final int STATE_SHOW_ERROR = 2;

  private int currentState = STATE_DEFAULT;
  private HeaderInfoView.HeaderInfoError error;
  private HeaderInfoModel model;

  public HeaderInfoViewState() {
    currentState = STATE_UNINITIALIZED;
  }

  @ParcelConstructor
  public HeaderInfoViewState(int currentState,
                             HeaderInfoView.HeaderInfoError error,
                             HeaderInfoModel model) {
    this.currentState = currentState;
    this.error = error;
    this.model = model;
  }

  public void setData(HeaderInfoModel model) {
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

  public void setStateShowError(HeaderInfoView.HeaderInfoError error) {
    currentState = STATE_SHOW_ERROR;
    this.error = error;
  }

  public int getCurrentState() {
    return currentState;
  }

  public HeaderInfoView.HeaderInfoError getError() {
    return error;
  }

  public HeaderInfoModel getModel() {
    return model;
  }

  @Override
  public void apply(HeaderInfoView view) {
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

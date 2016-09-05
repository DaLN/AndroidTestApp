package com.example.nelson.presentation;

import android.os.Parcelable;

import com.example.nelson.presentation.model.GameDataModel;
import com.example.nelson.presentation.view.GameDataView;

/**
 * Created by Nelson on 04/09/2016.
 */
public class GameDataViewState implements ViewState<GameDataView>, Parcelable {

  private final static int STATE_UNINITIALIZED = -1;
  private final static int STATE_DEFAULT = 0;
  private final static int STATE_SHOW_LOADING = 1;
  private final static int STATE_SHOW_ERROR = 2;

  private int mCurrentState = 0;
  private GameDataView.GameDataError mError;
  private GameDataModel mModel;

  public GameDataViewState() {
    mCurrentState = STATE_UNINITIALIZED;
  }

  public void setData(GameDataModel model) {
    mModel = model;
  }

  public void setStateShowContent() {
    mCurrentState = STATE_DEFAULT;
  }

  public void setStateShowLoading() {
    mCurrentState = STATE_SHOW_LOADING;
  }

  public void setStateHideLoading() {
    if (mCurrentState != STATE_SHOW_ERROR) { // to avoid such things use State pattern
      mCurrentState = STATE_DEFAULT;
      mError = null;
    }
  }

  public void setStateShowError(GameDataView.GameDataError error) {
    mCurrentState = STATE_SHOW_ERROR;
    mError = error;
  }

  @Override
  public void apply(GameDataView view) {
    if (mModel != null) {
      view.setData(mModel);
      view.showContent();
    }
    switch (mCurrentState) {
      case STATE_SHOW_LOADING:
        view.showLoading();
        break;
      case STATE_SHOW_ERROR:
        view.showError(mError);
        break;
      default:
        break;
    }
  }

  // PARCELABLE

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.mCurrentState);
    dest.writeInt(this.mError == null ? -1 : this.mError.ordinal());
    dest.writeParcelable(this.mModel, 0);
  }

  protected WeatherViewState(Parcel in) {
    this.mCurrentState = in.readInt();
    int tmpMError = in.readInt();
    this.mError = tmpMError == -1 ? null : WeatherView.WeatherError.values()[tmpMError];
    this.mModel = in.readParcelable(WeatherViewModel.class.getClassLoader());
  }

  public static final Creator<WeatherViewState> CREATOR = new Creator<WeatherViewState>() {
    public WeatherViewState createFromParcel(Parcel source) {
      return new WeatherViewState(source);
    }

    public WeatherViewState[] newArray(int size) {
      return new WeatherViewState[size];
    }
  };
}

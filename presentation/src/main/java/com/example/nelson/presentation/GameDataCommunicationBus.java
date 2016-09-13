package com.example.nelson.presentation;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.nelson.domain.exception.ErrorBundle;
import com.example.nelson.presentation.library.PresenterBundle;
import com.example.nelson.presentation.model.GameDataModel;
import com.example.nelson.presentation.presenter.GameDataPresenter;
import com.example.nelson.presentation.view.GameDataView;

import org.parceler.Parcels;

import javax.inject.Named;

/**
 * Created by Nelson on 04/09/2016.
 */
public class GameDataCommunicationBus implements GameDataView, GameDataPresenter {

  private GameDataView mView;

  @Named("presenter")
  private GameDataPresenter mPresenter;

  private static final String VIEW_STATE_KEY = "VIEW_STATE";
  private GameDataViewState mViewState;

  public GameDataCommunicationBus(GameDataPresenter mPresenter) {
    this.mPresenter = mPresenter;
    mViewState = createViewState();
    mView = DetachedGameDataView.getInstance();
    mPresenter.bindView(this);
  }

  @org.jetbrains.annotations.Contract(" -> !null")
  private GameDataViewState createViewState() {
    return new GameDataViewState();
  }

  @Override
  public void showLoading() {
    Log.d("NELSON", "GameDataCommunicationBus, showLoading");
    mViewState.setStateShowLoading();
    mView.showLoading();
  }

  @Override
  public void hideLoading() {
    Log.d("NELSON", "GameDataCommunicationBus, hideLoading");
    mViewState.setStateHideLoading();
    mView.hideLoading();
  }

  @Override
  public void setData(GameDataModel data) {
    Log.d("NELSON", "GameDataCommunicationBus, setData, data = " + data.toString());
    mViewState.setData(data);
    mView.setData(data);
  }

  @Override
  public void loadData() {
    Log.d("NELSON", "GameDataCommunicationBus, loadData, mView = " + mView.toString());
    mView.loadData();
  }

  @Override
  public void showContent() {
    Log.d("NELSON", "GameDataCommunicationBus, showContent");
    mViewState.setStateShowContent();
    mView.showContent();
  }

  @Override
  public void showRetry() {
    mView.showRetry();
  }

  @Override
  public void hideRetry() {
    mView.hideRetry();
  }

  @Override
  public void showError(GameDataView.GameDataError gameDataError) {
    mViewState.setStateShowError(gameDataError);
    mView.showError(gameDataError);
  }

  @Override
  public Context getContext() {
    return mView.getContext();
  }

  // PRESENTER

  @Override
  public void bindView(GameDataView view) {
    mViewState.apply(view);
    this.mView = view;
    Log.d("NELSON", "GameDataCommunicationBus, bindView, view = " + view.toString()
        + "this.mView = " + this.mView.toString());
  }

  @Override
  public void unbindView() {
    this.mView = DetachedGameDataView.getInstance();
  }

  @Override
  public void showErrorMessage(ErrorBundle defaultErrorBundle) {

  }


  @Override
  public void onCreate(@Nullable PresenterBundle bundle) {
    mPresenter.onCreate(bundle);
    if (bundle != null) {
      mViewState = (GameDataViewState) bundle.getSerializable(VIEW_STATE_KEY);
    }
  }

  @Override
    public void onSaveInstanceState(PresenterBundle bundle) {
    mPresenter.onSaveInstanceState(bundle);
    bundle.putParcelable(VIEW_STATE_KEY, Parcels.wrap(mViewState));
  }

  @Override
  public void onDestroy() {
    mPresenter.unbindView();
    mPresenter.onDestroy();
  }

  @Override
  public void callGameData() {
    mPresenter.callGameData();
  }

  @Override
  public void updateViewWithGameData(GameDataModel gameDataModel) {
    Log.d("Nelson", "GameDataCommunicationBus, updateViewWithGameData, gameDataModel = " + gameDataModel.toString());
    mPresenter.updateViewWithGameData(gameDataModel);
  }

  /**
   * Null Object Pattern
   */
  private static class DetachedGameDataView implements GameDataView {

    private static final DetachedGameDataView view = new DetachedGameDataView();

    public static DetachedGameDataView getInstance() {
      return view;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setData(GameDataModel data) {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(GameDataError gameDataError) {

    }

    @Override
    public Context getContext() {
      return null;
    }
  }
}

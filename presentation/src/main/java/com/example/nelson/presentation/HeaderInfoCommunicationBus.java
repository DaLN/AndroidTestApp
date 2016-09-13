package com.example.nelson.presentation;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.nelson.domain.exception.DefaultErrorBundle;
import com.example.nelson.domain.exception.ErrorBundle;
import com.example.nelson.presentation.library.PresenterBundle;
import com.example.nelson.presentation.model.HeaderInfoModel;
import com.example.nelson.presentation.presenter.HeaderInfoPresenter;
import com.example.nelson.presentation.view.HeaderInfoView;

import org.parceler.Parcels;

import javax.inject.Named;

/**
 * Created by Nelson on 12/09/2016.
 */
public class HeaderInfoCommunicationBus implements HeaderInfoView, HeaderInfoPresenter {

  private HeaderInfoView mView;


  private HeaderInfoPresenter mPresenter;
  private static final String VIEW_STATE_KEY = "VIEW_STATE";
  private HeaderInfoViewState mViewState;

  public HeaderInfoCommunicationBus(HeaderInfoPresenter mPresenter) {
    this.mPresenter = mPresenter;
    mViewState = createViewState();
    mView = DetachedHeaderInfoView.getInstance();
    mPresenter.bindView(this);
  }

  @org.jetbrains.annotations.Contract(" -> !null")
  private HeaderInfoViewState createViewState() {
    return new HeaderInfoViewState();
  }

  @Override
  public void callData() {
    mPresenter.callData();
  }

  @Override
  public void showLastLoginDate() {
    mView.showLastLoginDate();
  }

  @Override
  public void hideLastLoginDate() {
    mView.hideLastLoginDate();
  }

  @Override
  public void showLoading() {
    mViewState.setStateShowLoading();
    mView.showLoading();
  }

  @Override
  public void hideLoading() {
    mViewState.setStateHideLoading();
    mView.hideLoading();
  }

  @Override
  public void setData(HeaderInfoModel data) {
    mViewState.setData(data);
    mView.setData(data);
  }

  @Override
  public void loadData() {
    mView.loadData();
  }

  @Override
  public void showContent() {
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
  public void showError(HeaderInfoError headerInfoError) {
    mViewState.setStateShowError(headerInfoError);
    mView.showError(headerInfoError);
  }

  @Override
  public Context getContext() {
    return mView.getContext();
  }

  @Override
  public void onCreate(@Nullable PresenterBundle bundle) {
    mPresenter.onCreate(bundle);
    if (bundle != null) {
      mViewState = (HeaderInfoViewState) bundle.getSerializable(VIEW_STATE_KEY);
    }
  }

  @Override
  public void onSaveInstanceState(@NonNull PresenterBundle bundle) {
    mPresenter.onSaveInstanceState(bundle);
    bundle.putParcelable(VIEW_STATE_KEY, Parcels.wrap(mViewState));
  }

  @Override
  public void onDestroy() {
    mPresenter.unbindView();
    mPresenter.onDestroy();
  }

  @Override
  public void bindView(HeaderInfoView view) {
    mViewState.apply(view);
    this.mView = view;
  }

  @Override
  public void unbindView() {
    this.mView = DetachedHeaderInfoView.getInstance();
  }

  @Override
  public void showErrorMessage(ErrorBundle errorBundle) {
    showError(HeaderInfoError.GENERAL);
  }

  @Override
  public void updateViewWithHeaderInfo(HeaderInfoModel headerInfoModel) {
    mPresenter.updateViewWithHeaderInfo(headerInfoModel);
  }

  /**
   * Null Object Pattern
   */
  private static class DetachedHeaderInfoView implements HeaderInfoView {

    private static final DetachedHeaderInfoView view = new DetachedHeaderInfoView();

    public static DetachedHeaderInfoView getInstance() {
      return view;
    }

    @Override
    public void showLastLoginDate() {
    }

    @Override
    public void hideLastLoginDate() {
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setData(HeaderInfoModel data) {

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
    public void showError(HeaderInfoError gameDataError) {

    }

    @Override
    public Context getContext() {
      return null;
    }
  }
}

package com.example.nelson.presentation.presenter;

import android.view.View;

import com.example.nelson.domain.exception.ErrorBundle;
import com.example.nelson.domain.interactor.UseCase;
import com.example.nelson.presentation.DevTestApplication;
import com.example.nelson.presentation.activity.MainActivity;
import com.example.nelson.presentation.mapper.HeaderInfoModelDataMapper;
import com.example.nelson.presentation.mapper.ScoreModelDataMapper;
import com.example.nelson.presentation.model.HeaderInfoModel;
import com.example.nelson.presentation.model.ScoreModel;
import com.example.nelson.presentation.view.LoadDataView;
import com.example.nelson.presentation.view.fragment.GameDataFragment;


import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by Nelson on 13/08/2016.
 */
@Singleton
public class MainPresenter {

  private LoadDataView loadDataView;

  MainActivity mainActivity;

  private UseCase getGameDataCase;
  private UseCase getHeaderInfoCase;

  List<ScoreModel> scores;

  @Inject
  public MainPresenter(@Named("gameData") UseCase getGameDataCase,
                       @Named("headerInfo") UseCase getHeaderInfoCase) {
    this.getGameDataCase = getGameDataCase;
    this.getHeaderInfoCase = getHeaderInfoCase;
    DevTestApplication.getDevTestApplication().getTestAppComponent().inject(this);
    scores = Collections.emptyList();
  }

  public void setLoadDataView(LoadDataView loadDataView) {
    this.loadDataView = loadDataView;
  }

  public void callData() {
    hideViewRetry();
    showViewLoading();
    getHeaderInfoCase.execute(
        new HeaderInfoResponseSubscriber(this, new HeaderInfoModelDataMapper()));
    getGameDataCase.execute(
        new GameDataResponseSubscriber(this, new ScoreModelDataMapper()));
  }




  public void rxUnSubscribe() {
    if (getGameDataCase != null) {
      getGameDataCase.unsubscribe();
    }
    if (getHeaderInfoCase != null) {
      getHeaderInfoCase.unsubscribe();
    }
  }

  public void updateViewWithScores(List<ScoreModel> scores) {
    hideViewLoading();
    this.scores = scores;
    ((GameDataFragment) loadDataView).renderScoreList(scores);
  }

  public void updateActivityWithHeaderInfo(HeaderInfoModel headerInfoModel) {
    mainActivity.refreshHeaderInfo(headerInfoModel);
  }


  public void showViewLoading() {
    this.loadDataView.showLoading();
  }

  public void hideViewLoading() {
    this.loadDataView.hideLoading();
  }

  public void showViewRetry() {
    this.loadDataView.showRetry();
  }

  public void hideViewRetry() {
    this.loadDataView.hideRetry();
  }

  public void showErrorMessage(ErrorBundle errorBundle) {
    showViewRetry();
    this.loadDataView.showError(errorBundle.getErrorMessage());
  }

  public void showLastLoginDate() {
    this.mainActivity.showLastLoginDate();
  }

  public void hideLastLoginDate() {
    this.mainActivity.hideLastLoginDate();
  }

  public void setMainActivity(MainActivity mainActivity) {
    this.mainActivity = mainActivity;
  }
}

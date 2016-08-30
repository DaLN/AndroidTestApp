package com.example.nelson.presentation.presenter;

import android.util.Log;

import com.example.nelson.domain.GameData;
import com.example.nelson.domain.exception.DefaultErrorBundle;
import com.example.nelson.domain.interactor.DefaultSubscriber;
import com.example.nelson.presentation.mapper.ScoreModelDataMapper;
import com.example.nelson.presentation.model.GameDataModel;
import com.example.nelson.presentation.model.ScoreModel;

import java.util.List;

import rx.Subscriber;

/**
 * Created by Nelson on 14/08/2016.
 */
public class GameDataResponseSubscriber extends DefaultSubscriber<GameData> {


  private MainPresenter mainPresenter;
  private ScoreModelDataMapper scoreModelDataMapper;

  public GameDataResponseSubscriber(MainPresenter mainPresenter,
                                    ScoreModelDataMapper scoreModelDataMapper) {
    this.mainPresenter = mainPresenter;
    this.scoreModelDataMapper = scoreModelDataMapper;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onCompleted() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onError(Throwable e) {
    mainPresenter.showErrorMessage(new DefaultErrorBundle((Exception) e));
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void onNext(GameData gameData) {
    mainPresenter.updateViewWithScores(
        (List<ScoreModel>) scoreModelDataMapper.transform(gameData.getScoreList()));
  }
}

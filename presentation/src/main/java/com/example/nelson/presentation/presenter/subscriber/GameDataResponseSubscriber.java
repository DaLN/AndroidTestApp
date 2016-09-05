package com.example.nelson.presentation.presenter.subscriber;

import com.example.nelson.domain.GameData;
import com.example.nelson.domain.exception.DefaultErrorBundle;
import com.example.nelson.domain.interactor.DefaultSubscriber;
import com.example.nelson.presentation.mapper.ScoreModelDataMapper;
import com.example.nelson.presentation.model.ScoreModel;
import com.example.nelson.presentation.presenter.GameDataPresenterImpl;

import java.util.List;

/**
 * Created by Nelson on 14/08/2016.
 */
public class GameDataResponseSubscriber extends DefaultSubscriber<GameData> {


  private GameDataPresenterImpl mainPresenter;
  private ScoreModelDataMapper scoreModelDataMapper;

  public GameDataResponseSubscriber(GameDataPresenterImpl mainPresenter,
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

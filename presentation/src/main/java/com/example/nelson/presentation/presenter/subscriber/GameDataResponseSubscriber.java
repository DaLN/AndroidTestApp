package com.example.nelson.presentation.presenter.subscriber;

import android.util.Log;

import com.example.nelson.domain.GameData;
import com.example.nelson.domain.exception.DefaultErrorBundle;
import com.example.nelson.domain.interactor.DefaultSubscriber;
import com.example.nelson.presentation.mapper.GameDataModelDataMapper;
import com.example.nelson.presentation.mapper.ScoreModelDataMapper;
import com.example.nelson.presentation.presenter.GameDataPresenterImpl;

/**
 * Created by Nelson on 14/08/2016.
 */
public class GameDataResponseSubscriber extends DefaultSubscriber<GameData> {


  private GameDataPresenterImpl mainPresenter;
  private GameDataModelDataMapper gameDataModelDataMapper;

  public GameDataResponseSubscriber(GameDataPresenterImpl mainPresenter,
                                    GameDataModelDataMapper gameDataModelDataMapper) {
    this.mainPresenter = mainPresenter;
    this.gameDataModelDataMapper = gameDataModelDataMapper;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onCompleted() {
    Log.i("NELSON", "GameDataResponseSubscriber, onCompleted ");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onError(Throwable e) {
    Log.e("NELSON", "GameDataResponseSubscriber, onError -> " + e.toString(), e);
    mainPresenter.showErrorMessage(new DefaultErrorBundle((Exception) e));
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void onNext(GameData gameData) {
    Log.i("NELSON", "GameDataResponseSubscriber, onNext -> " + gameData.toString());
    mainPresenter.updateViewWithGameData(gameDataModelDataMapper.transform(gameData));
  }
}

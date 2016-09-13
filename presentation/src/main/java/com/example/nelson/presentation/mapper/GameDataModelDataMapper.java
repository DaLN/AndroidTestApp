package com.example.nelson.presentation.mapper;

import com.example.nelson.domain.GameData;
import com.example.nelson.domain.Score;
import com.example.nelson.presentation.model.GameDataModel;
import com.example.nelson.presentation.model.ScoreModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link Score} (in the domain layer) to {@link ScoreModel} in the
 * presentation layer.
 */
public class GameDataModelDataMapper {
  ScoreModelDataMapper scoreModelDataMapper;


  public GameDataModelDataMapper() {
    scoreModelDataMapper = new ScoreModelDataMapper();
  }

  /**
   * Transform a {@link GameData} into an {@link GameDataModel}.
   *
   * @param gameData Object to be transformed.
   * @return {@link GameDataModel}.
   */
  public GameDataModel transform(GameData gameData) {
    if (gameData == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }
    return new GameDataModel(
        (List<ScoreModel>) scoreModelDataMapper.transform(gameData.getScoreList()),
        gameData.getResponse(), gameData.getCurrency());
  }
}

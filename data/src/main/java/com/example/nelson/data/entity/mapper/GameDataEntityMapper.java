package com.example.nelson.data.entity.mapper;

import com.example.nelson.data.entity.GameDataEntity;
import com.example.nelson.data.entity.ScoreEntity;
import com.example.nelson.domain.GameData;
import com.example.nelson.domain.Score;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link GameDataEntity} (in the data layer) to {@link GameData}
 * in the domain layer.
 */
@Singleton
public class GameDataEntityMapper {

  @Inject
  public GameDataEntityMapper() {}

  /**
   * Transform a {@link GameDataEntity} into an {@link GameData}.
   *
   * @param gameDataEntity Object to be transformed.
   * @return {@link GameData } if valid {@link GameDataEntity} otherwise null.
   */
  public GameData transform(GameDataEntity gameDataEntity) {
    GameData gameData = null;
    if (gameDataEntity != null) {
      ArrayList<Score> scores = (ArrayList<Score>) transform(gameDataEntity.getScoreEntityList());
      gameData = new GameData(scores, gameDataEntity.getResponse(),
          gameDataEntity.getCurrency());
    }
    return gameData;
  }

  /**
   * Transform a List of {@link ScoreEntity} into a Collection of {@link Score}.
   *
   * @param userEntityCollection Object Collection to be transformed.
   * @return List of {@link Score} if valid {@link ScoreEntity} otherwise empty list.
   */
  public List<Score> transform(Collection<ScoreEntity> userEntityCollection) {
    List<Score> scores = new ArrayList<>(userEntityCollection.size());
    for (ScoreEntity scoreEntity : userEntityCollection) {
      scores.add(
          new Score(scoreEntity.getName(), scoreEntity.getJackpot(), scoreEntity.getDate()));
    }
    return scores;
  }
}

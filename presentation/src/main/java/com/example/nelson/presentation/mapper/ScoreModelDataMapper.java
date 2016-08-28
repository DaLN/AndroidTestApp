package com.example.nelson.presentation.mapper;

import com.example.nelson.domain.Score;
import com.example.nelson.presentation.model.ScoreModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.inject.Inject;

/**
 * Mapper class used to transform {@link Score} (in the domain layer) to {@link ScoreModel} in the
 * presentation layer.
 */
public class ScoreModelDataMapper {

  @Inject
  public ScoreModelDataMapper() {}

  /**
   * Transform a {@link Score} into an {@link ScoreModel}.
   *
   * @param score Object to be transformed.
   * @return {@link ScoreModel}.
   */
  public ScoreModel transform(Score score) {
    if (score == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }
    return new ScoreModel(score.getName(), score.getJackpot(), score.getDate());
  }

  /**
   * Transform a Collection of {@link Score} into a Collection of {@link ScoreModel}.
   *
   * @param scores Objects to be transformed.
   * @return List of {@link ScoreModel}.
   */
  public Collection<ScoreModel> transform(Collection<Score> scores) {
    Collection<ScoreModel> scoreModels;

    if (scores != null && !scores.isEmpty()) {
      scoreModels = new ArrayList<>();
      for (Score score : scores) {
        scoreModels.add(transform(score));
      }
    } else {
      scoreModels = Collections.emptyList();
    }

    return scoreModels;
  }
}

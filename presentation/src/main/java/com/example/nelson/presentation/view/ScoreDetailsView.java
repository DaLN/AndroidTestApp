package com.example.nelson.presentation.view;

import com.example.nelson.presentation.model.ScoreModel;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a score details.
 */
public interface ScoreDetailsView {

  /**
   * Render a score details.
   *
   * @param user The {@link ScoreModel} details that will be shown.
   */
  void renderScore(ScoreModel user);
}

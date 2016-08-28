package com.example.nelson.presentation.view;

import com.example.nelson.presentation.model.ScoreModel;

import java.util.List;

/**
 * Interface representing a View in a MVP pattern.
 * This view represents a list of {@link Score}.
 */
public interface ScoreListView extends LoadDataView {

  /**
   * Render a list of scores in the UI.
   *
   * @param scores The list of {@link ScoreModel} that will be shown.
   */
  void renderScoreList(List<ScoreModel> scores);

}

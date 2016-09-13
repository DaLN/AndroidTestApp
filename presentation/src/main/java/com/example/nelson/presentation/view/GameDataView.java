package com.example.nelson.presentation.view;

import com.example.nelson.presentation.model.GameDataModel;
import com.example.nelson.presentation.model.ScoreModel;

import java.util.List;

/**
 * Interface representing a View in a MVP pattern.
 * This view represents a list of {@link ScoreModel}.
 */
public interface GameDataView extends LceView<GameDataModel, GameDataView.GameDataError> {

  public enum GameDataError {
    GENERAL
  }

}

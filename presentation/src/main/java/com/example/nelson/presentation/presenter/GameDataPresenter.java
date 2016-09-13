package com.example.nelson.presentation.presenter;

import com.example.nelson.domain.exception.ErrorBundle;
import com.example.nelson.presentation.library.Presenter;
import com.example.nelson.presentation.model.GameDataModel;
import com.example.nelson.presentation.view.GameDataView;

/**
 * Created by Nelson on 03/09/2016.
 */
public interface GameDataPresenter extends Presenter<GameDataView> {

  void callGameData();
  void updateViewWithGameData(GameDataModel gameDataModel);
}

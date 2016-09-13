package com.example.nelson.presentation.presenter;

import com.example.nelson.domain.exception.ErrorBundle;
import com.example.nelson.presentation.library.Presenter;
import com.example.nelson.presentation.model.HeaderInfoModel;
import com.example.nelson.presentation.view.HeaderInfoView;

/**
 * Created by Nelson on 04/09/2016.
 */
public interface HeaderInfoPresenter extends Presenter<HeaderInfoView> {


  void callData();
  void updateViewWithHeaderInfo(HeaderInfoModel headerInfoModel);
}

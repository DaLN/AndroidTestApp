package com.example.nelson.presentation.presenter.subscriber;

import com.example.nelson.domain.HeaderInfo;
import com.example.nelson.domain.exception.DefaultErrorBundle;
import com.example.nelson.domain.interactor.DefaultSubscriber;
import com.example.nelson.presentation.mapper.HeaderInfoModelDataMapper;
import com.example.nelson.presentation.presenter.GameDataPresenterImpl;

/**
 * Created by Nelson on 14/08/2016.
 */
public class HeaderInfoResponseSubscriber extends DefaultSubscriber<HeaderInfo> {

  private GameDataPresenterImpl mainPresenter;
  private HeaderInfoModelDataMapper headerInfoModelDataMapper;

  public HeaderInfoResponseSubscriber(GameDataPresenterImpl mainPresenter,
                                      HeaderInfoModelDataMapper headerInfoModelDataMapper) {
    this.mainPresenter = mainPresenter;
    this.headerInfoModelDataMapper = headerInfoModelDataMapper;
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
  public void onNext(HeaderInfo headerInfo) {
    mainPresenter.updateActivityWithHeaderInfo(headerInfoModelDataMapper.transform(headerInfo));
  }
}

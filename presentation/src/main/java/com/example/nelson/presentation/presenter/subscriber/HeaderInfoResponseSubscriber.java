package com.example.nelson.presentation.presenter.subscriber;

import android.util.Log;

import com.example.nelson.domain.HeaderInfo;
import com.example.nelson.domain.exception.DefaultErrorBundle;
import com.example.nelson.domain.interactor.DefaultSubscriber;
import com.example.nelson.presentation.mapper.HeaderInfoModelDataMapper;
import com.example.nelson.presentation.presenter.HeaderInfoPresenter;

/**
 * Created by Nelson on 14/08/2016.
 */
public class HeaderInfoResponseSubscriber extends DefaultSubscriber<HeaderInfo> {

  private HeaderInfoPresenter headerInfoPresenter;
  private HeaderInfoModelDataMapper headerInfoModelDataMapper;

  public HeaderInfoResponseSubscriber(HeaderInfoPresenter headerInfoPresenter,
                                      HeaderInfoModelDataMapper headerInfoModelDataMapper) {
    this.headerInfoPresenter = headerInfoPresenter;
    this.headerInfoModelDataMapper = headerInfoModelDataMapper;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onCompleted() {
    Log.i("NELSON", "HeaderInfoResponseSubscriber, onCompleted ");

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onError(Throwable e) {
    Log.e("NELSON", "HeaderInfoResponseSubscriber, onError -> " + e.getLocalizedMessage(), e);
    headerInfoPresenter.showErrorMessage(new DefaultErrorBundle((Exception) e));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onNext(HeaderInfo headerInfo) {
    Log.i("NELSON", "HeaderInfoResponseSubscriber, onNext, headerInfo = " + headerInfo.toString());
    headerInfoPresenter.updateViewWithHeaderInfo(headerInfoModelDataMapper.transform(headerInfo));
  }
}

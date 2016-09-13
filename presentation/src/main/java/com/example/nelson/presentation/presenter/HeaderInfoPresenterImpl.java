package com.example.nelson.presentation.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.nelson.domain.exception.ErrorBundle;
import com.example.nelson.domain.executor.PostExecutionThread;
import com.example.nelson.domain.executor.ThreadExecutor;
import com.example.nelson.domain.interactor.UseCase;
import com.example.nelson.presentation.activity.MainActivity;
import com.example.nelson.presentation.library.PresenterBundle;
import com.example.nelson.presentation.mapper.HeaderInfoModelDataMapper;
import com.example.nelson.presentation.mapper.ScoreModelDataMapper;
import com.example.nelson.presentation.model.HeaderInfoModel;
import com.example.nelson.presentation.model.ScoreModel;
import com.example.nelson.presentation.presenter.subscriber.GameDataResponseSubscriber;
import com.example.nelson.presentation.presenter.subscriber.HeaderInfoResponseSubscriber;
import com.example.nelson.presentation.utils.RxTransformers;
import com.example.nelson.presentation.view.GameDataView;
import com.example.nelson.presentation.view.HeaderInfoView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Nelson on 13/08/2016.
 */
@Singleton
public class HeaderInfoPresenterImpl implements HeaderInfoPresenter {

  private HeaderInfoView mView;

  private UseCase getHeaderInfoCase;
  private ThreadExecutor threadExecutor;
  private PostExecutionThread postExecutionThread;
  private CompositeSubscription subscriptions;

  public HeaderInfoPresenterImpl(UseCase getHeaderInfoCase,
                                 ThreadExecutor threadExecutor,
                                 PostExecutionThread postExecutionThread) {
    this.getHeaderInfoCase = getHeaderInfoCase;
    this.threadExecutor = threadExecutor;
    this.postExecutionThread = postExecutionThread;
    subscriptions = new CompositeSubscription();
  }


  @Override
  public void bindView(HeaderInfoView view) {
    this.mView = view;
  }

  @Override
  public void unbindView() {
    this.mView = null;
  }

  @Override
  public void onCreate(PresenterBundle presenterBundle) {
    if (presenterBundle == null) {
      mView.loadData();
    }
  }

  @Override
  public void onSaveInstanceState(@NonNull PresenterBundle bundle) {

  }

  @Override
  public void onDestroy() {
    if (subscriptions.hasSubscriptions()) {
      subscriptions.unsubscribe();
    }
  }


  public void callData() {
    hideViewRetry();
    showViewLoading();
    loadHeaderInfo();
  }

  @SuppressWarnings("unchecked")
  private void loadHeaderInfo() {
    subscriptions.add(getHeaderInfoCase
        .buildUseCaseObservable()
        .subscribeOn(Schedulers.from(threadExecutor))
        .observeOn(postExecutionThread.getScheduler())
        .compose(RxTransformers.applyOpBeforeAndAfter(showViewLoading(), hideViewLoading()))
        .subscribe(new HeaderInfoResponseSubscriber(this, new HeaderInfoModelDataMapper())));
  }


  public void updateViewWithHeaderInfo(HeaderInfoModel headerInfoModel) {
    mView.setData(headerInfoModel);
    mView.showContent();
  }


  private Runnable showViewLoading() {
    return new Runnable() {
      @Override
      public void run() {
        mView.showLoading();
      }
    };
  }

  private Runnable hideViewLoading() {
    this.mView.hideLoading();
    return new Runnable() {
      @Override
      public void run() {
        mView.hideLoading();
      }
    };
  }

  private Runnable showViewRetry() {
    return new Runnable() {
      @Override
      public void run() {
        mView.showRetry();
      }
    };
  }

  private Runnable hideViewRetry() {
    return new Runnable() {
      @Override
      public void run() {
        mView.hideRetry();
      }
    };
  }

  public void showErrorMessage(ErrorBundle errorBundle) {
    showViewRetry().run();
    showViewRetry().run();
    // Error is always general.
    mView.showError(HeaderInfoView.HeaderInfoError.GENERAL);
  }

  public void showLastLoginDate() {
    this.mView.showLastLoginDate();
  }

  public void hideLastLoginDate() {
    this.mView.hideLastLoginDate();
  }
}

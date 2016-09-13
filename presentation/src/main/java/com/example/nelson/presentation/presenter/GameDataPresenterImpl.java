package com.example.nelson.presentation.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.nelson.domain.exception.ErrorBundle;
import com.example.nelson.domain.executor.PostExecutionThread;
import com.example.nelson.domain.executor.ThreadExecutor;
import com.example.nelson.domain.interactor.UseCase;
import com.example.nelson.presentation.library.PresenterBundle;
import com.example.nelson.presentation.mapper.GameDataModelDataMapper;
import com.example.nelson.presentation.mapper.ScoreModelDataMapper;
import com.example.nelson.presentation.model.GameDataModel;
import com.example.nelson.presentation.presenter.subscriber.GameDataResponseSubscriber;
import com.example.nelson.presentation.utils.RxTransformers;
import com.example.nelson.presentation.view.GameDataView;


import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Nelson on 13/08/2016.
 */
@Singleton
public class GameDataPresenterImpl implements GameDataPresenter {

  private GameDataView mView;

  private UseCase getGameDataCase;

  private ThreadExecutor threadExecutor;
  private PostExecutionThread postExecutionThread;
  private CompositeSubscription subscriptions;

  @Inject
  public GameDataPresenterImpl(@Named("gameData") UseCase getGameDataCase,
                               ThreadExecutor threadExecutor,
                               PostExecutionThread postExecutionThread) {
    this.getGameDataCase = getGameDataCase;
    this.threadExecutor = threadExecutor;
    this.postExecutionThread = postExecutionThread;
    subscriptions = new CompositeSubscription();
  }


  @Override
  public void bindView(GameDataView view) {
    this.mView = view;
  }

  @Override
  public void unbindView() {
    this.mView = null;
  }

  @Override
  public void onCreate(@Nullable PresenterBundle bundle) {
    Log.d("NELSON", "GameDataPresenterImpl, onCreate, bundle = " + (bundle != null ? bundle.toString() : null));
    if (bundle == null) {
      mView.loadData();
    }
  }

  @Override
  public void onSaveInstanceState(@NonNull PresenterBundle bundle) {

  }

  @Override
  public void onDestroy() {
    if (subscriptions != null && !subscriptions.isUnsubscribed()) {
      subscriptions.unsubscribe();
    }
  }

  public void callGameData() {
    Log.d("NELSON", "GameDataPresenterImpl, callGameData");

    hideViewRetry();
    loadGameData();
  }

  @SuppressWarnings("unchecked")
  private void loadGameData() {
    Log.d("NELSON", "GameDataPresenterImpl loadGameData, START");

    subscriptions.add(
        getGameDataCase.buildUseCaseObservable()
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())
            .compose(RxTransformers.applyOpBeforeAndAfter(showViewLoading(), hideViewLoading()))
            .subscribe(new GameDataResponseSubscriber(this, new GameDataModelDataMapper()))
    );
    Log.d("NELSON", "GameDataPresenterImpl loadGameData, subscriptions = " + subscriptions.toString());

  }

  public void updateViewWithGameData(GameDataModel gameDataModel) {
    Log.d("NELSON", "GameDataPresenterImpl updateViewWithGameData, gameDataModel = " + gameDataModel.toString());
    mView.setData(gameDataModel);
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
    // Error is always general.
    mView.showError(GameDataView.GameDataError.GENERAL);
  }

  public void showLastLoginDate() {
    //TODO tell other fragment to show login date
  }

  public void hideLastLoginDate() {
    //TODO tell other fragment to hide login date
  }

}

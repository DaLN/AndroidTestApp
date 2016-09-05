package com.example.nelson.presentation.presenter;

import com.example.nelson.domain.exception.ErrorBundle;
import com.example.nelson.domain.executor.PostExecutionThread;
import com.example.nelson.domain.executor.ThreadExecutor;
import com.example.nelson.domain.interactor.UseCase;
import com.example.nelson.presentation.activity.MainActivity;
import com.example.nelson.presentation.mapper.HeaderInfoModelDataMapper;
import com.example.nelson.presentation.mapper.ScoreModelDataMapper;
import com.example.nelson.presentation.model.HeaderInfoModel;
import com.example.nelson.presentation.model.ScoreModel;
import com.example.nelson.presentation.presenter.subscriber.GameDataResponseSubscriber;
import com.example.nelson.presentation.presenter.subscriber.HeaderInfoResponseSubscriber;
import com.example.nelson.presentation.utils.RxTransformers;
import com.example.nelson.presentation.view.LceView;
import com.example.nelson.presentation.view.fragment.GameDataFragment;


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
public class GameDataPresenterImpl implements GameDataPresenter<MainActivity> {

  private LceView LceView;

  MainActivity mainActivity;

  private UseCase getGameDataCase;
  private UseCase getHeaderInfoCase;

  List<ScoreModel> scores;

  private ThreadExecutor threadExecutor;
  private PostExecutionThread postExecutionThread;
  private CompositeSubscription subscriptions;

  @Inject
  public GameDataPresenterImpl(@Named("gameData") UseCase getGameDataCase,
                               @Named("headerInfo") UseCase getHeaderInfoCase,
                               ThreadExecutor threadExecutor,
                               PostExecutionThread postExecutionThread) {
    this.getGameDataCase = getGameDataCase;
    this.getHeaderInfoCase = getHeaderInfoCase;
    this.threadExecutor = threadExecutor;
    this.postExecutionThread = postExecutionThread;
    scores = Collections.emptyList();
  }

  public void setLceView(LceView lceView) {
    this.LceView = lceView;
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
    loadGameData();
  }

  @SuppressWarnings("unchecked")
  private void loadHeaderInfo() {
    subscriptions.add(getHeaderInfoCase.buildUseCaseObservable()
        .subscribeOn(Schedulers.from(threadExecutor))
        .observeOn(postExecutionThread.getScheduler())
        .compose(RxTransformers.applyOpBeforeAndAfter(showViewLoading(), hideViewLoading()))
        .subscribe(new HeaderInfoResponseSubscriber(this, new HeaderInfoModelDataMapper())));
  }


  @SuppressWarnings("unchecked")
  private void loadGameData() {
    subscriptions.add(getGameDataCase.buildUseCaseObservable()
        .subscribeOn(Schedulers.from(threadExecutor))
        .observeOn(postExecutionThread.getScheduler())
        .compose(RxTransformers.applyOpBeforeAndAfter(showViewLoading(), hideViewLoading()))
        .subscribe( new GameDataResponseSubscriber(this, new ScoreModelDataMapper())));
  }



  public void rxUnSubscribe() {
    if (getGameDataCase != null) {
      getGameDataCase.unsubscribe();
    }
    if (getHeaderInfoCase != null) {
      getHeaderInfoCase.unsubscribe();
    }
  }

  public void updateViewWithScores(List<ScoreModel> scores) {
    hideViewLoading();
    this.scores = scores;
    ((GameDataFragment) LceView).renderScoreList(scores);
  }

  public void updateActivityWithHeaderInfo(HeaderInfoModel headerInfoModel) {
    mainActivity.refreshHeaderInfo(headerInfoModel);
  }


  private Runnable showViewLoading() {
    return new Runnable() {
      @Override
      public void run() {
        LceView.showLoading();
      }
    };
  }

  private Runnable hideViewLoading() {
    this.LceView.hideLoading();
    return new Runnable() {
      @Override
      public void run() {
        LceView.showLoading();
      }
    };
  }

  private Runnable showViewRetry() {
    return new Runnable() {
      @Override
      public void run() {
        LceView.showRetry();
      }
    };
  }

  private Runnable hideViewRetry() {
    return new Runnable() {
      @Override
      public void run() {
        LceView.hideRetry();
      }
    };
  }

  public void showErrorMessage(ErrorBundle errorBundle) {
    showViewRetry().run();
    LceView.showError(errorBundle.getErrorMessage());
  }

  public void showLastLoginDate() {
    this.mainActivity.showLastLoginDate();
  }

  public void hideLastLoginDate() {
    this.mainActivity.hideLastLoginDate();
  }

  public void setMainActivity(MainActivity mainActivity) {
    this.mainActivity = mainActivity;
  }
}

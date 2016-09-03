package com.example.nelson.presentation;

import android.content.Context;

import com.example.nelson.data.entity.mapper.GameDataEntityMapper;
import com.example.nelson.data.entity.mapper.HeaderInfoEntityMapper;
import com.example.nelson.data.model.NetworkService;
import com.example.nelson.data.repository.datasource.GameDataStoreFactory;
import com.example.nelson.data.repository.datasource.HeaderInfoDataStoreFactory;
import com.example.nelson.domain.executor.PostExecutionThread;
import com.example.nelson.domain.executor.ThreadExecutor;
import com.example.nelson.domain.interactor.UseCase;
import com.example.nelson.domain.repository.GameDataRepository;
import com.example.nelson.domain.repository.HeaderInfoRepository;
import com.example.nelson.presentation.dagger2.component.TestAppComponent;
import com.example.nelson.presentation.dagger2.module.DataModule;
import com.example.nelson.presentation.dagger2.module.DomainModule;
import com.example.nelson.presentation.dagger2.module.PresentationModule;
import com.example.nelson.presentation.mapper.HeaderInfoModelDataMapper;
import com.example.nelson.presentation.mapper.ScoreModelDataMapper;
import com.example.nelson.presentation.presenter.GameDataResponseSubscriber;
import com.example.nelson.presentation.presenter.HeaderInfoResponseSubscriber;
import com.example.nelson.presentation.presenter.MainPresenter;
import com.example.nelson.presentation.view.fragment.GameDataFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import javax.inject.Named;

import it.cosenonjaviste.daggermock.DaggerMockRule;
import it.cosenonjaviste.daggermock.InjectFromComponent;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Nelson on 01/09/2016.
 */
public class MainPresenterTest {

  @InjectFromComponent
  MainPresenter mainPresenter;

  @Mock
  @Named("gameData")
  UseCase getGameDataCase;

  @Mock
  @Named("headerInfo")
  UseCase getHeaderInfoCase;

  @Mock
  GameDataRepository gameDataRepository;

  @Mock
  HeaderInfoRepository headerInfoRepository;

  @Mock
  PostExecutionThread postExecutionThread;

  @Mock
  ThreadExecutor threadExecutor;

  @Mock
  Context context;

  @Mock
  NetworkService NetworkService;

  @Mock
  GameDataStoreFactory gameDataStoreFactory;

  @Mock
  HeaderInfoDataStoreFactory headerInfoDataStoreFactory;

  @Mock
  GameDataEntityMapper gameDataEntityMapper;

  @Mock
  HeaderInfoEntityMapper headerInfoEntityMapper;

  GameDataFragment gameDataFragment;

  @Rule
  public DaggerMockRule<TestAppComponent> mockitoRule =
      new DaggerMockRule<>(TestAppComponent.class, new PresentationModule(), new DomainModule(),
          new DataModule());

  @Before
  public void setUp() {
    gameDataFragment = mock(GameDataFragment.class);
  }

  @Test
  public void testCallData() {
    mainPresenter.setLoadDataView(gameDataFragment);
    mainPresenter.callData();

    InOrder inOrder = inOrder(gameDataFragment);
    inOrder.verify(gameDataFragment).hideRetry();
    inOrder.verify(gameDataFragment).showLoading();
    verify(getHeaderInfoCase).execute(
        new HeaderInfoResponseSubscriber(mainPresenter, (HeaderInfoModelDataMapper) any()));
    verify(getGameDataCase).execute(
        new GameDataResponseSubscriber(mainPresenter, (ScoreModelDataMapper) any()));
  }
}

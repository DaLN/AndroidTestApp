package com.example.nelson.presentation.dagger2.module;

import android.content.Context;

import com.example.nelson.data.entity.mapper.GameDataEntityMapper;
import com.example.nelson.data.repository.GameDataRepositoryImpl;
import com.example.nelson.data.repository.datasource.GameDataStoreFactory;
import com.example.nelson.domain.executor.PostExecutionThread;
import com.example.nelson.domain.executor.ThreadExecutor;
import com.example.nelson.domain.interactor.GetGameDataCase;
import com.example.nelson.domain.interactor.UseCase;
import com.example.nelson.domain.repository.GameDataRepository;
import com.example.nelson.presentation.GameDataCommunicationBus;
import com.example.nelson.presentation.presenter.GameDataPresenter;
import com.example.nelson.presentation.presenter.GameDataPresenterImpl;
import com.example.nelson.presentation.view.PerFragment;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nelson on 05/09/2016.
 */
@Module
public class GameDataModule {

  @Provides
  @PerFragment
  GameDataPresenter provideCommunicationBus(@Named("presenter") GameDataPresenter presenter) {
    return new GameDataCommunicationBus(presenter);
  }

  @Provides
  @Named("presenter")
  @PerFragment
  GameDataPresenter provideGameDataPresenter(@Named("gameData") UseCase getGameDataCase,
                                            ThreadExecutor threadExecutor,
                                            PostExecutionThread postExecutionThread) {
    return new GameDataPresenterImpl(getGameDataCase, threadExecutor, postExecutionThread);
  }

  @Provides
  @PerFragment
  @Named("gameData")
  public UseCase provideGetGameDataCase(GameDataRepository gameDataRepository) {
    return new GetGameDataCase(gameDataRepository);
  }

  @Provides
  @PerFragment
  public GameDataRepository provideGameDataRepository(
      GameDataStoreFactory gameDataStoreFactory,
      GameDataEntityMapper gameDataEntityMapper) {
    return new GameDataRepositoryImpl(gameDataStoreFactory, gameDataEntityMapper);
  }

  @Provides
  @PerFragment
  public GameDataStoreFactory provideGameDataStoreFactory(Context context) {
    return new GameDataStoreFactory(context);
  }

  @Provides
  @PerFragment
  public GameDataEntityMapper provideGameDataEntityMapper() {
    return new GameDataEntityMapper();
  }
}

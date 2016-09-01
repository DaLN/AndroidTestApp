package com.example.nelson.presentation.dagger2.module;


import com.example.nelson.data.entity.mapper.GameDataEntityMapper;
import com.example.nelson.data.entity.mapper.HeaderInfoEntityMapper;
import com.example.nelson.data.executor.JobExecutor;
import com.example.nelson.data.repository.GameDataRepositoryImpl;
import com.example.nelson.data.repository.HeaderInfoDataRepositoryImpl;
import com.example.nelson.data.repository.datasource.GameDataStoreFactory;
import com.example.nelson.data.repository.datasource.HeaderInfoDataStoreFactory;
import com.example.nelson.domain.executor.PostExecutionThread;
import com.example.nelson.domain.executor.ThreadExecutor;
import com.example.nelson.domain.interactor.GetGameDataCase;
import com.example.nelson.domain.interactor.GetHeaderInfoCase;
import com.example.nelson.domain.interactor.UseCase;
import com.example.nelson.domain.repository.GameDataRepository;
import com.example.nelson.domain.repository.HeaderInfoRepository;
import com.example.nelson.presentation.UiThread;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nelson on 16/08/2016.
 */
@Module
public class DomainModule {

  public DomainModule() {
  }

  @Provides
  @Named("gameData")
  UseCase provideGetGameDataCase(GameDataRepository gameDataRepository,
                                  ThreadExecutor threadExecutor,
                                  PostExecutionThread postExecutionThread) {
    return new GetGameDataCase(gameDataRepository, threadExecutor, postExecutionThread);
  }

  @Provides
  @Named("headerInfo")
  UseCase provideGetHeaderInfoCase(HeaderInfoRepository headerInfoDataRepository,
                                    ThreadExecutor threadExecutor,
                                    PostExecutionThread postExecutionThread) {
    return new GetHeaderInfoCase(headerInfoDataRepository, threadExecutor, postExecutionThread);
  }

  @Provides
  GameDataRepository provideGameDataRepository(
      GameDataStoreFactory gameDataStoreFactory,
      GameDataEntityMapper gameDataEntityMapper) {
    return new GameDataRepositoryImpl(gameDataStoreFactory, gameDataEntityMapper);
  }

  @Provides
  HeaderInfoRepository provideHeaderInfoRepository(
      HeaderInfoDataStoreFactory headerInfoDataStoreFactory,
      HeaderInfoEntityMapper headerInfoEntityMapper) {
    return new HeaderInfoDataRepositoryImpl(headerInfoDataStoreFactory, headerInfoEntityMapper);
  }

  @Provides
  ThreadExecutor provideThreadExecutor() {
    return new JobExecutor();
  }

  @Provides
  PostExecutionThread providePostExecutionThread() {
    return new UiThread();
  }
}

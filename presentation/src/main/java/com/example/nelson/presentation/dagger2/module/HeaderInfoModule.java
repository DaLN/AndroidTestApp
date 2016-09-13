package com.example.nelson.presentation.dagger2.module;

import com.example.nelson.data.entity.mapper.HeaderInfoEntityMapper;
import com.example.nelson.data.repository.HeaderInfoDataRepositoryImpl;
import com.example.nelson.data.repository.datasource.HeaderInfoDataStoreFactory;
import com.example.nelson.domain.executor.PostExecutionThread;
import com.example.nelson.domain.executor.ThreadExecutor;
import com.example.nelson.domain.interactor.GetHeaderInfoCase;
import com.example.nelson.domain.interactor.UseCase;
import com.example.nelson.domain.repository.HeaderInfoRepository;
import com.example.nelson.presentation.DevTestApplication;
import com.example.nelson.presentation.GameDataCommunicationBus;
import com.example.nelson.presentation.HeaderInfoCommunicationBus;
import com.example.nelson.presentation.presenter.GameDataPresenter;
import com.example.nelson.presentation.presenter.GameDataPresenterImpl;
import com.example.nelson.presentation.presenter.HeaderInfoPresenter;
import com.example.nelson.presentation.presenter.HeaderInfoPresenterImpl;
import com.example.nelson.presentation.view.PerFragment;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nelson on 11/09/2016.
 */
@Module
public class HeaderInfoModule {

  @Provides
  @PerFragment
  HeaderInfoPresenter provideCommunicationBus(@Named("presenter") HeaderInfoPresenter presenter) {
    return new HeaderInfoCommunicationBus(presenter);
  }

  @Provides
  @Named("presenter")
  @PerFragment
  public HeaderInfoPresenter providePresenter(@Named("headerInfo") UseCase getHeaderInfoCase,
                                              ThreadExecutor threadExecutor,
                                              PostExecutionThread postExecutionThread) {
    return new HeaderInfoPresenterImpl(getHeaderInfoCase, threadExecutor, postExecutionThread);
  }

  @Provides
  @Named("headerInfo")
  public UseCase provideGetHeaderInfoCase(HeaderInfoRepository headerInfoDataRepository) {
    return new GetHeaderInfoCase(headerInfoDataRepository);
  }

  @Provides
  public HeaderInfoRepository provideHeaderInfoRepository(
      HeaderInfoDataStoreFactory headerInfoDataStoreFactory,
      HeaderInfoEntityMapper headerInfoEntityMapper) {
    return new HeaderInfoDataRepositoryImpl(headerInfoDataStoreFactory, headerInfoEntityMapper);
  }

  @Provides
  public HeaderInfoDataStoreFactory provideHeaderInfoDataStoreFactory() {
    return new HeaderInfoDataStoreFactory(DevTestApplication.getDevTestApplication());
  }

  @Provides
  public HeaderInfoEntityMapper provideHeaderInfoEntityMapper() {
    return new HeaderInfoEntityMapper();
  }
}

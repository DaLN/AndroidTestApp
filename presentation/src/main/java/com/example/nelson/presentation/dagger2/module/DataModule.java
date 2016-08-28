package com.example.nelson.presentation.dagger2.module;


import com.example.nelson.data.entity.mapper.GameDataEntityMapper;
import com.example.nelson.data.entity.mapper.HeaderInfoEntityMapper;
import com.example.nelson.data.executor.JobExecutor;
import com.example.nelson.data.model.NetworkService;
import com.example.nelson.data.repository.GameDataRepositoryImpl;
import com.example.nelson.data.repository.HeaderInfoDataRepositoryImpl;
import com.example.nelson.data.repository.datasource.GameDataStoreFactory;
import com.example.nelson.data.repository.datasource.HeaderInfoDataStoreFactory;
import com.example.nelson.domain.interactor.GetGameDataCase;
import com.example.nelson.domain.interactor.GetHeaderInfoCase;
import com.example.nelson.domain.interactor.UseCase;
import com.example.nelson.presentation.DevTestApplication;
import com.example.nelson.presentation.UiThread;
import com.example.nelson.presentation.presenter.MainPresenter;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nelson on 16/08/2016.
 */
@Module
public class DataModule {

  public DataModule() {
  }

  @Provides
  @Singleton
  NetworkService providesNetworkService() {
    return new NetworkService(DevTestApplication.getDevTestApplication());
  }
}

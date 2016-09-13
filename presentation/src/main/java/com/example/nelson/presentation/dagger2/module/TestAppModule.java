package com.example.nelson.presentation.dagger2.module;


import android.content.Context;

import com.example.nelson.data.executor.JobExecutor;
import com.example.nelson.data.model.NetworkService;
import com.example.nelson.domain.executor.PostExecutionThread;
import com.example.nelson.domain.executor.ThreadExecutor;
import com.example.nelson.presentation.DevTestApplication;
import com.example.nelson.presentation.UiThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nelson on 16/08/2016.
 */
@Module
public class TestAppModule {

  public TestAppModule() {
  }

  @Provides
  public Context provideContext() {
    return DevTestApplication.getDevTestApplication();
  }

  @Provides
  @Singleton
  public NetworkService provideNetworkService() {
    return new NetworkService(DevTestApplication.getDevTestApplication());
  }

  @Provides
  @Singleton
  public ThreadExecutor provideThreadExecutor() {
    return new JobExecutor();
  }

  @Provides
  @Singleton
  public PostExecutionThread providePostExecutionThread() {
    return new UiThread();
  }
}

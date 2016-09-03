package com.example.nelson.presentation.dagger2.module;


import android.content.Context;

import com.example.nelson.presentation.DevTestApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nelson on 16/08/2016.
 */
@Module
public class PresentationModule {

  public PresentationModule() {
  }

  @Provides
  public Context provideContext() {
    return DevTestApplication.getDevTestApplication();
  }
}

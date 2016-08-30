package com.example.nelson.presentation;

import android.app.Application;


import com.example.nelson.presentation.dagger2.component.DaggerTestAppComponent;
import com.example.nelson.presentation.dagger2.component.TestAppComponent;

import butterknife.ButterKnife;

/**
 * Created by Nelson on 16/08/2016.
 */
public class DevTestApplication extends Application {

  private static DevTestApplication devTestApplication = null;
  private TestAppComponent testAppComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    devTestApplication = this;

    testAppComponent = DaggerTestAppComponent.create();
    //ButterKnife.setDebug(BuildConfig.DEBUG);
  }

  public static DevTestApplication getDevTestApplication() {
    return devTestApplication;
  }

  public TestAppComponent getTestAppComponent() {
    return testAppComponent;
  }
}

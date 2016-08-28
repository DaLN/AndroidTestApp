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
    // If a Dagger 2 component does not have any constructor arguments for any of its modules,
    // then we can use .create() as a shortcut instead:
    //  mNetComponent = com.codepath.dagger.components.DaggerNetComponent.create();
    ButterKnife.setDebug(BuildConfig.DEBUG);
  }

  public static DevTestApplication getDevTestApplication() {
    return devTestApplication;
  }

  public TestAppComponent getTestAppComponent() {
    return testAppComponent;
  }
}

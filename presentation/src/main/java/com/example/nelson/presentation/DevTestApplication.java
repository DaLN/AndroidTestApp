package com.example.nelson.presentation;

import android.app.Application;
import android.content.Context;


import com.example.nelson.presentation.dagger2.component.DaggerTestAppComponent;
import com.example.nelson.presentation.dagger2.component.TestAppComponent;
import com.example.nelson.presentation.dagger2.module.TestAppModule;


/**
 * Created by Nelson on 16/08/2016.
 */
public class DevTestApplication extends Application {

  private TestAppComponent testAppComponent;
  private static DevTestApplication devTestApplication;

  public static DevTestApplication getDevTestApplication() {
    return devTestApplication;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    devTestApplication = this;
    //ButterKnife.setDebug(BuildConfig.DEBUG);
  }

  public static TestAppComponent getTestAppComponent(Context context) {
    DevTestApplication app = (DevTestApplication) context.getApplicationContext();
    if (app.testAppComponent == null) {
      app.testAppComponent = DaggerTestAppComponent.builder()
          .testAppModule(new TestAppModule())
          .build();
    }
    return app.testAppComponent;
  }

  public static void clearAppComponent(Context context) {
    DevTestApplication app = (DevTestApplication) context.getApplicationContext();
    app.testAppComponent = null;
  }
}

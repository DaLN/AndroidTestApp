package com.example.nelson.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.nelson.data.model.NetworkService;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link GameDataStore}.
 */
public class GameDataStoreFactory {

  private final Context context;

  public GameDataStoreFactory(@NonNull Context context) {
    this.context = context.getApplicationContext();
  }

  /**
   * Create {@link GameDataStore} to retrieve data from the Cloud.
   */
  public GameDataStore createDataStore() {
    NetworkService networkService = new NetworkService(this.context);

    return new GameDataStoreImpl(networkService);
  }
}

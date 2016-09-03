package com.example.nelson.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.nelson.data.model.NetworkService;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link GameDataStore}.
 */
@Singleton
public class HeaderInfoDataStoreFactory {

  private final Context context;

  public HeaderInfoDataStoreFactory(@NonNull Context context) {
    this.context = context.getApplicationContext();
  }

  /**
   * Create {@link GameDataStore} to retrieve data from the Cloud.
   */
  public HeaderInfoDataStore createDataStore() {
    NetworkService networkService = new NetworkService(this.context);

    return new HeaderInfoDataStoreImpl(networkService);
  }
}

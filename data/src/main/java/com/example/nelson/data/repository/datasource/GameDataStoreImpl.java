package com.example.nelson.data.repository.datasource;

import android.util.Log;

import com.example.nelson.data.entity.GameDataEntity;
import com.example.nelson.data.model.NetworkAPI;
import com.example.nelson.data.model.NetworkService;
import com.example.nelson.domain.GameData;
import rx.Observable;

/**
 * {@link GameDataStore} implementation.
 */
class GameDataStoreImpl implements GameDataStore {

  private final NetworkService networkService;


  /**
   * Construct a {@link GameDataStore}.
   *
   * @param networkService The {@link NetworkService} implementation to use.
   */
  GameDataStoreImpl(NetworkService networkService) {
    this.networkService = networkService;
  }

  @Override
  public Observable<GameDataEntity> gameDataEntity() {
    Log.d("NELSON", "GameDataStoreImpl, gameDataEntity()" );
    return (Observable<GameDataEntity>) networkService.getPreparedObservable(
        networkService.getAPI().getGameDataResponseObservable(),
        GameDataEntity.class);
  }
}

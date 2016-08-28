package com.example.nelson.data.repository.datasource;

import com.example.nelson.data.entity.GameDataEntity;
import com.example.nelson.data.entity.HeaderInfoEntity;
import com.example.nelson.data.model.NetworkService;
import com.example.nelson.domain.GameData;
import com.example.nelson.domain.HeaderInfo;

import rx.Observable;

/**
 * {@link HeaderInfoDataStore} implementation.
 */
class HeaderInfoDataStoreImpl implements HeaderInfoDataStore {

  private final NetworkService networkService;


  /**
   * Construct a {@link HeaderInfoDataStore}.
   *
   * @param networkService The {@link NetworkService} implementation to use.
   */
  HeaderInfoDataStoreImpl(NetworkService networkService) {
    this.networkService = networkService;
  }

  @Override
  public Observable<HeaderInfoEntity> headerInfoEntity() {
    return (Observable<HeaderInfoEntity>) networkService.getPreparedObservable(
        networkService.getAPI().getHeaderInfoResponseObservable(),
        HeaderInfoEntity.class);
  }
}

package com.example.nelson.data.repository.datasource;

import com.example.nelson.data.entity.HeaderInfoEntity;

import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface HeaderInfoDataStore {

  /**
   * Get an {@link rx.Observable} which will emit a {@link HeaderInfoEntity}.
   */
  Observable<HeaderInfoEntity> headerInfoEntity();
}

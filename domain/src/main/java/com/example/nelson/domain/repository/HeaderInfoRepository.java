package com.example.nelson.domain.repository;

import com.example.nelson.domain.HeaderInfo;

import rx.Observable;

/**
 * Created by Nelson on 19/08/2016.
 */
public interface HeaderInfoRepository {

  /**
   * Get an {@link rx.Observable} which will emit a List of {@link HeaderInfo}.
   */
  Observable<HeaderInfo> headerInfo();
}

package com.example.nelson.domain.repository;

import com.example.nelson.domain.GameData;

import rx.Observable;

/**
 * Created by Nelson on 19/08/2016.
 */
public interface GameDataRepository {

  /**
   * Get an {@link rx.Observable} which will emit a List of {@link GameData}.
   */
  Observable<GameData> gamedata();
}

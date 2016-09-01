package com.example.nelson.data.repository;

import com.example.nelson.data.entity.GameDataEntity;
import com.example.nelson.data.entity.mapper.GameDataEntityMapper;
import com.example.nelson.data.repository.datasource.GameDataStore;
import com.example.nelson.data.repository.datasource.GameDataStoreFactory;
import com.example.nelson.domain.GameData;
import com.example.nelson.domain.repository.GameDataRepository;

import java.text.ParseException;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Nelson on 19/08/2016.
 */
public class GameDataRepositoryImpl implements GameDataRepository {


  private GameDataStoreFactory gameDataStoreFactory;
  private GameDataEntityMapper gameDataEntityMapper;

  @Inject
  public GameDataRepositoryImpl(GameDataStoreFactory gameDataStoreFactory,
                                GameDataEntityMapper gameDataEntityMapper) {
    this.gameDataStoreFactory = gameDataStoreFactory;
    this.gameDataEntityMapper = gameDataEntityMapper;
  }

  public Observable<GameData> gamedata() {
    GameDataStore gameDataStore = this.gameDataStoreFactory.createDataStore();
    return gameDataStore.gameDataEntity().map(new Func1<GameDataEntity, GameData>() {
      @Override
      public GameData call(GameDataEntity gameDataEntity) {
        try {
          return gameDataEntityMapper.transform(gameDataEntity);
        } catch (ParseException e) {
          //RxJava will catch the error.
          throw new RuntimeException(e.getLocalizedMessage(), e);
        }
      }
    });

  }
}

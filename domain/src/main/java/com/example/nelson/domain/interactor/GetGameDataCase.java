package com.example.nelson.domain.interactor;

import android.util.Log;

import com.example.nelson.domain.GameData;
import com.example.nelson.domain.executor.PostExecutionThread;
import com.example.nelson.domain.executor.ThreadExecutor;
import com.example.nelson.domain.repository.GameDataRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link GameData}.
 */
public class GetGameDataCase extends UseCase {

  private final GameDataRepository gameDataRepository;

  public GetGameDataCase(GameDataRepository gameDataRepository) {
    super();
    this.gameDataRepository = gameDataRepository;
  }

  @Override
  public Observable<GameData> buildUseCaseObservable() {
    Log.d("NELSON", "GetGameDataCase, buildUseCaseObservable");
    return this.gameDataRepository.gamedata();
  }
}

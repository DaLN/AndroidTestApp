package com.example.nelson.domain.interactor;

import com.example.nelson.domain.GameData;
import com.example.nelson.domain.HeaderInfo;
import com.example.nelson.domain.executor.PostExecutionThread;
import com.example.nelson.domain.executor.ThreadExecutor;
import com.example.nelson.domain.repository.GameDataRepository;
import com.example.nelson.domain.repository.HeaderInfoRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link GameData}.
 */
public class GetHeaderInfoCase extends UseCase {

  private final HeaderInfoRepository headerInfoRepository;

  public GetHeaderInfoCase(HeaderInfoRepository headerInfoRepository) {
    super();
    this.headerInfoRepository = headerInfoRepository;
  }

  @Override
  public Observable<HeaderInfo> buildUseCaseObservable() {
    return this.headerInfoRepository.headerInfo();
  }
}

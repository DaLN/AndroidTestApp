package com.example.nelson.data.repository;

import com.example.nelson.data.entity.HeaderInfoEntity;
import com.example.nelson.data.entity.mapper.HeaderInfoEntityMapper;
import com.example.nelson.data.repository.datasource.HeaderInfoDataStore;
import com.example.nelson.data.repository.datasource.HeaderInfoDataStoreFactory;
import com.example.nelson.domain.HeaderInfo;
import com.example.nelson.domain.repository.HeaderInfoRepository;

import java.text.ParseException;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Nelson on 19/08/2016.
 */
public class HeaderInfoDataRepositoryImpl implements HeaderInfoRepository {


  private HeaderInfoDataStoreFactory headerInfoDataStoreFactory;
  private HeaderInfoEntityMapper headerInfoEntityMapper;

  @Inject
  public HeaderInfoDataRepositoryImpl(HeaderInfoDataStoreFactory headerInfoDataStoreFactory,
                                      HeaderInfoEntityMapper headerInfoEntityMapper) {
    this.headerInfoDataStoreFactory = headerInfoDataStoreFactory;
    this.headerInfoEntityMapper = headerInfoEntityMapper;
  }

  public Observable<HeaderInfo> headerInfo() {
    HeaderInfoDataStore headerInfoDataStore = this.headerInfoDataStoreFactory.createDataStore();
    return headerInfoDataStore.headerInfoEntity().map(new Func1<HeaderInfoEntity, HeaderInfo>() {
      @Override
      public HeaderInfo call(HeaderInfoEntity headerInfoEntity) {
        try {
          return headerInfoEntityMapper.transform(headerInfoEntity);
        } catch (ParseException e) {
          //RxJava will catch the error.
          throw new RuntimeException(e.getLocalizedMessage(), e);
        }
      }
    });

  }
}

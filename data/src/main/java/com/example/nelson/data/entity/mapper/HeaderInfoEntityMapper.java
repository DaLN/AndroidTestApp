package com.example.nelson.data.entity.mapper;

import com.example.nelson.data.entity.GameDataEntity;
import com.example.nelson.data.entity.HeaderInfoEntity;
import com.example.nelson.data.entity.ScoreEntity;
import com.example.nelson.domain.GameData;
import com.example.nelson.domain.HeaderInfo;
import com.example.nelson.domain.Score;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link GameDataEntity} (in the data layer) to {@link GameData}
 * in the domain layer.
 */
@Singleton
public class HeaderInfoEntityMapper {

  @Inject
  public HeaderInfoEntityMapper() {}

  /**
   * Transform a {@link HeaderInfoEntity} into an {@link HeaderInfo}.
   *
   * @param headerInfoEntity Object to be transformed.
   * @return {@link HeaderInfo } if valid {@link HeaderInfoEntity} otherwise null.
   */
  public HeaderInfo transform(HeaderInfoEntity headerInfoEntity) {
    HeaderInfo headerInfo = null;
    if (headerInfoEntity != null) {

      DateTime date = DateTime.parse(headerInfoEntity.getLastLogindate(),
          DateTimeFormat.forPattern("dd/MM/yyyy'T'HH:mm"));
      headerInfo = new HeaderInfo(headerInfoEntity.getPlayerName(),
          headerInfoEntity.getBalance(), headerInfoEntity.getAvatarURL(),date);
    }
    return headerInfo;
  }
}

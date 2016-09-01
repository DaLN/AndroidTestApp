package com.example.nelson.data.entity.mapper;

import com.example.nelson.data.entity.GameDataEntity;
import com.example.nelson.data.entity.HeaderInfoEntity;
import com.example.nelson.domain.GameData;
import com.example.nelson.domain.HeaderInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
  public HeaderInfo transform(HeaderInfoEntity headerInfoEntity) throws ParseException {
    HeaderInfo headerInfo = null;
    if (headerInfoEntity != null) {

      SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy'T'HH:mm", Locale.getDefault());
      Date date = format.parse(headerInfoEntity.getLastLogindate());

      headerInfo = new HeaderInfo(headerInfoEntity.getPlayerName(),
          headerInfoEntity.getBalance(), headerInfoEntity.getAvatarURL(),date);
    }
    return headerInfo;
  }
}

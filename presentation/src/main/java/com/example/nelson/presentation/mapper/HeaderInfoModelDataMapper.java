package com.example.nelson.presentation.mapper;

import com.example.nelson.domain.HeaderInfo;
import com.example.nelson.presentation.model.HeaderInfoModel;


import javax.inject.Inject;

/**
 * Mapper class used to transform {@link HeaderInfo} (in the domain layer)
 * to {@link HeaderInfoModel} in the presentation layer.
 */
public class HeaderInfoModelDataMapper {

  @Inject
  public HeaderInfoModelDataMapper() {}

  /**
   * Transform a {@link HeaderInfo} into an {@link HeaderInfoModel}.
   *
   * @param headerInfo Object to be transformed.
   * @return {@link HeaderInfoModel}.
   */
  public HeaderInfoModel transform(HeaderInfo headerInfo) {
    if (headerInfo == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }
    return new HeaderInfoModel(headerInfo.getPlayerName(), headerInfo.getBalance(),
        headerInfo.getAvatarURL(), headerInfo.getLastLogindate());
  }
}

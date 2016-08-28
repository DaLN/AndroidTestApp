package com.example.nelson.data.model;

import com.example.nelson.data.entity.GameDataEntity;
import com.example.nelson.data.entity.HeaderInfoEntity;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Nelson on 13/08/2016.
 */
public interface NetworkAPI {

    /**
     * JSON game data file location: https://dl.dropboxusercontent.com/s/1pe6mkfih0lbj72/gameData.json
     * JSON header info file location: https://dl.dropboxusercontent.com/s/6lzavrsohol21km/playerInfo.json
     */
    @GET("1pe6mkfih0lbj72/gameDataEntity.json")
    Observable<GameDataEntity> getGameDataResponseObservable();

    @GET("6lzavrsohol21km/playerInfo.json")
    Observable<HeaderInfoEntity> getHeaderInfoResponseObservable();
}

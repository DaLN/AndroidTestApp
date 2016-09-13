package com.example.nelson.data.model;

import android.content.Context;
import android.util.Log;
import android.util.LruCache;

import com.example.nelson.data.BuildConfig;
import com.example.nelson.data.entity.GameDataEntity;
import com.example.nelson.data.entity.HeaderInfoEntity;
import com.example.nelson.data.entity.TestResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.android.schedulers.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;




/**
 * Created by Nelson MELINA on 13/08/2016.
 */
@Singleton
public class NetworkService {
  private final String gamedataFilename = "gameDataEntity.json";
  private final String headerinfoFilename = "headerInfo.json";
  private NetworkAPI networkApi;
  private LruCache<Class<?>, Observable<? extends TestResponse>> apiObservables =
      new LruCache<>(10);

  private GameDataEntity gameDataEntity = null;
  private HeaderInfoEntity headerInfoEntity = null;

  private Context context;

  public NetworkService(Context context) {
    this.context = context;
    String baseUrl = "https://dl.dropboxusercontent.com/s/";

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();

    networkApi = retrofit.create(NetworkAPI.class);
  }


  public NetworkAPI getAPI() {
    return networkApi;
  }


  /**
   * To allow a networking request to live outside of the lifecycle of a view, and solve the issue
   * of rotating the device during a request, we only create an observable object when a cached
   * one is not available, or we explicitly need a new one.
   * <p/>
   * This method returns an Observable that could be cached or a new one.
   * <p/>
   * To learn more about this :
   *
   * @param unPreparedObservable
   * @param clazz
   * @return
   * @see <a href="https://www.captechconsulting.com/blogs/a-mvp-approach-to-lifecycle-safe-requests-with-retrofit-20-and-rxjava">A MVP Approach to Lifecycle Safe Requests with Retrofit 2.0 and RxJava</a>
   */
  public Observable<? extends TestResponse> getPreparedObservable(
      Observable<? extends TestResponse> unPreparedObservable, final Class<?> clazz) {
    Log.d("NELSON", "NetworkService, getPreparedObservable()" );
    Observable<? extends TestResponse> preparedObservable =
        apiObservables.get(NetworkAPI.class);

    if (preparedObservable != null) {
      return preparedObservable;
    }

    Observable<? extends TestResponse> memory = null;

    Observable<? extends TestResponse> disk = null;
    if (clazz == GameDataEntity.class) {
      memory = Observable.just(gameDataEntity);
      disk = Observable.fromCallable(new Callable<GameDataEntity>() {
        @Override
        public GameDataEntity call() throws Exception {
          return (GameDataEntity) readFromDisk(gamedataFilename);
        }
      });
    } else if (clazz == HeaderInfoEntity.class) {
      memory = Observable.just(headerInfoEntity);
      disk = Observable.fromCallable(new Callable<HeaderInfoEntity>() {
        @Override
        public HeaderInfoEntity call() throws Exception {
          return (HeaderInfoEntity) readFromDisk(headerinfoFilename);
        }
      });
    }

    disk = disk.doOnNext(new Action1<TestResponse>() {
      @Override
      public void call(TestResponse testResponse) {
          if (clazz == GameDataEntity.class) {
            gameDataEntity = (GameDataEntity) testResponse;
          } else if (clazz == HeaderInfoEntity.class) {
            headerInfoEntity = (HeaderInfoEntity) testResponse;
          }
      }
    });

    Observable<? extends TestResponse> network =
        unPreparedObservable.doOnNext(new Action1<TestResponse>() {
          @Override
          public void call(TestResponse testResponse) {
            try {
              if (clazz == GameDataEntity.class) {
                gameDataEntity = (GameDataEntity) testResponse;
                saveToDisk(testResponse, gamedataFilename);
              } else if (clazz == HeaderInfoEntity.class) {
                headerInfoEntity = (HeaderInfoEntity) testResponse;
                saveToDisk(testResponse, headerinfoFilename);
              }
            } catch (IOException ex) {
              if (BuildConfig.DEBUG) {
                Log.e(NetworkService.class.getCanonicalName(), ex.getLocalizedMessage(), ex);
              }
            }
          }
        });

    // Retrieve the first source with data that is up to date.
    Observable<? extends TestResponse> source = Observable
        .concat(memory, disk, network)
        .first(new Func1<TestResponse, Boolean>() {
          @Override
          public Boolean call(TestResponse testResponse) {
            return testResponse != null && testResponse.isUpToDate();
          }
        });

    preparedObservable = source.subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());

    preparedObservable = preparedObservable.cache();
    apiObservables.put(clazz, preparedObservable);

    return preparedObservable;
  }

  private void saveToDisk(TestResponse data, String fileName) throws IOException {
    FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
    Gson gson = new Gson();
    fos.write(gson.toJson(data).getBytes());
    fos.close();
  }

  private TestResponse readFromDisk(String fileName) throws IOException {
    if (!context.getFileStreamPath(fileName).exists()) {
      return null;
    }
    FileInputStream fis = context.openFileInput(fileName);
    if (fis != null) {
      InputStreamReader inputStreamReader = new InputStreamReader(fis);
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
      String receiveString;
      StringBuilder stringBuilder = new StringBuilder();

      while ((receiveString = bufferedReader.readLine()) != null) {
        stringBuilder.append(receiveString);
      }

      fis.close();

      Gson gson = new Gson();
      if (fileName.equalsIgnoreCase(gamedataFilename)) {
        return gson.fromJson(stringBuilder.toString(), GameDataEntity.class);
      } else if (fileName.equalsIgnoreCase(headerinfoFilename)) {
        return gson.fromJson(stringBuilder.toString(), HeaderInfoEntity.class);
      }
    }
    return null;
  }
}

package com.example.nelson.presentation.utils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Helper class for common observable transformations.
 */
public class RxTransformers {

  /**
   * Apply standard {@link Schedulers}: io for {@link Observable}, ui for {@link Subscriber}
   */
  public static <T> Observable.Transformer<T, T> applySchedulers() {
    return new Observable.Transformer<T, T>() {
      @Override
      public Observable<T> call(Observable<T> tObservable) {
        return tObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
      }
    };
  }

  /**
   * Apply standard {@link Schedulers}: io for {@link Observable}, io for {@link Subscriber}
   */
  public static <T> Observable.Transformer<T, T> applyIoSchedulers() {
    return new Observable.Transformer<T, T>() {
      @Override
      public Observable<T> call(Observable<T> tObservable) {
        return tObservable.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io());
      }
    };
  }

  /**
   * Run code before running observable {@link Observable} and after its termination
   *
   * @param before code that will run onSubscribe
   * @param after  code that will run afterTerminate
   * @param <T>    {@link Object}
   * @return {@link Observable}
   */
  public static <T> Observable.Transformer<T, T> applyOpBeforeAndAfter(final Runnable before, final Runnable after) {
    return new Observable.Transformer<T, T>() {
      @Override
      public Observable<T> call(Observable<T> tObservable) {
        return tObservable
            .doAfterTerminate(new Action0() {
              @Override
              public void call() {
                after.run();
              }
            }).doOnSubscribe(new Action0() {
              @Override
              public void call() {
                before.run();
              }
            });
      }
    };
  }
}

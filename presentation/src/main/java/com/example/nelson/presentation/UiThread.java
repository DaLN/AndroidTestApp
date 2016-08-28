package com.example.nelson.presentation;

import com.example.nelson.domain.executor.PostExecutionThread;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Nelson on 28/08/2016.
 */
public class UiThread implements PostExecutionThread {
  @Override
  public Scheduler getScheduler() {
    return AndroidSchedulers.mainThread();
  }
}

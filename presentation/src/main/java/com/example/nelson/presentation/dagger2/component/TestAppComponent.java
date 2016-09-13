package com.example.nelson.presentation.dagger2.component;

import android.content.Context;

import com.example.nelson.data.model.NetworkService;
import com.example.nelson.domain.executor.PostExecutionThread;
import com.example.nelson.domain.executor.ThreadExecutor;
import com.example.nelson.presentation.activity.MainActivity;
import com.example.nelson.presentation.dagger2.module.GameDataModule;
import com.example.nelson.presentation.dagger2.module.HeaderInfoModule;
import com.example.nelson.presentation.dagger2.module.TestAppModule;
import com.example.nelson.presentation.view.fragment.DetailFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Nelson on 16/08/2016.
 */
@Singleton
@Component(modules = {TestAppModule.class})
public interface TestAppComponent {
  Context context();
  NetworkService networkService();
  ThreadExecutor threadExecutor();
  PostExecutionThread postExecutionThread();
}

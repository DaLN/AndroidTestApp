package com.example.nelson.presentation.dagger2.component;

import com.example.nelson.data.model.NetworkService;
import com.example.nelson.presentation.activity.MainActivity;
import com.example.nelson.presentation.dagger2.module.DataModule;
import com.example.nelson.presentation.dagger2.module.DomainModule;
import com.example.nelson.presentation.dagger2.module.PresentationModule;
import com.example.nelson.presentation.presenter.GameDataPresenterImpl;
import com.example.nelson.presentation.presenter.subscriber.GameDataResponseSubscriber;
import com.example.nelson.presentation.presenter.subscriber.HeaderInfoResponseSubscriber;
import com.example.nelson.presentation.view.fragment.DetailFragment;
import com.example.nelson.presentation.view.fragment.GameDataFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Nelson on 16/08/2016.
 */
@Singleton
@Component(modules = {PresentationModule.class, DomainModule.class, DataModule.class})
public interface TestAppComponent {

  GameDataPresenterImpl mainPresenter();
  void inject(MainActivity mainActivity);
  void inject(GameDataPresenterImpl mainActivity);
  void inject(GameDataFragment gameDataFragment);
  void inject(DetailFragment detailFragment);
  void inject(NetworkService networkService);
  void inject(GameDataResponseSubscriber gameDataResponseSubscriber);
  void inject(HeaderInfoResponseSubscriber headerInfoResponseSubscriber);
}

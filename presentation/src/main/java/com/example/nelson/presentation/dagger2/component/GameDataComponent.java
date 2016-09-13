package com.example.nelson.presentation.dagger2.component;

import com.example.nelson.presentation.activity.MainActivity;
import com.example.nelson.presentation.dagger2.module.GameDataModule;
import com.example.nelson.presentation.library.HasPresenter;
import com.example.nelson.presentation.presenter.GameDataPresenter;
import com.example.nelson.presentation.presenter.GameDataPresenterImpl;
import com.example.nelson.presentation.presenter.subscriber.GameDataResponseSubscriber;
import com.example.nelson.presentation.view.PerFragment;
import com.example.nelson.presentation.view.fragment.DetailFragment;
import com.example.nelson.presentation.view.fragment.GameDataFragment;
import com.example.nelson.presentation.view.fragment.GameDataLayout;

import dagger.Component;

/**
 * Created by Nelson on 04/09/2016.
 */
@Component(
    dependencies = TestAppComponent.class,
    modules = {GameDataModule.class}
)
@PerFragment
public interface GameDataComponent extends HasPresenter<GameDataPresenter> {
  GameDataPresenter gameDataPresenter();
  void inject(GameDataResponseSubscriber gameDataResponseSubscriber);
  void inject(GameDataPresenterImpl gameDataPresenter);
  void inject(GameDataFragment gameDataFragment);
  void inject(DetailFragment detailFragment);

  void inject(MainActivity mainActivity);

  GameDataLayout inject(GameDataLayout gameDataLayout);
}

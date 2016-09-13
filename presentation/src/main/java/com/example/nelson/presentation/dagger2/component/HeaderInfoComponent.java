package com.example.nelson.presentation.dagger2.component;

import android.view.View;

import com.example.nelson.presentation.dagger2.module.HeaderInfoModule;
import com.example.nelson.presentation.library.HasPresenter;
import com.example.nelson.presentation.presenter.HeaderInfoPresenter;
import com.example.nelson.presentation.presenter.subscriber.HeaderInfoResponseSubscriber;
import com.example.nelson.presentation.view.PerFragment;
import com.example.nelson.presentation.view.fragment.HeaderInfoFragment;
import com.example.nelson.presentation.view.fragment.HeaderInfoLayout;

import dagger.Component;

/**
 * Created by Nelson on 04/09/2016.
 */
@Component(
    dependencies = TestAppComponent.class,
    modules = {HeaderInfoModule.class}
)
@PerFragment
public interface HeaderInfoComponent extends HasPresenter<HeaderInfoPresenter> {
  void inject(HeaderInfoResponseSubscriber headerInfoResponseSubscriber);
  void inject(HeaderInfoFragment headerInfoFragment);
  HeaderInfoLayout inject(HeaderInfoLayout headerInfoLayout);
}

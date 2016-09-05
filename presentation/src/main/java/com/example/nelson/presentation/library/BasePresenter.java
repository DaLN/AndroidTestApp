package com.example.nelson.presentation.library;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.nelson.presentation.presenter.Presenter;
import com.example.nelson.presentation.view.View;

/**
 * This is a default implementation for the Presenter interface which provides (mostly) empty
 * methods so you can override only what is needed when writing your own presenters.
 *
 * From [here] (http://blog.bradcampbell.nz/mvp-presenters-that-survive-configuration-changes-part-2/)
 */
public abstract class BasePresenter<V extends View> implements Presenter<V> {
  protected V view;

  @Override
  public void bindView(V view) {
    this.view = view;
  }

  @Override
  public void unbindView() {
    this.view = null;
  }

  @Override
  public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {}

  @Override
  public void onDestroy() {}

  @Override
  public void onSaveInstanceState(@NonNull Bundle bundle) {}
}

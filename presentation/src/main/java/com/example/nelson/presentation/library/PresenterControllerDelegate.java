package com.example.nelson.presentation.library;

import android.os.Bundle;

import com.example.nelson.presentation.presenter.Presenter;
import com.example.nelson.presentation.view.View;

/**
 * This is the delegate class for Fragments/Activities to manage the lifecycle events of it's
 * presenter instance. Like the other delegate classes, this should only be used in client code
 * when using the support library fragment isn't quite what you want.
 *
 * From [here] (http://blog.bradcampbell.nz/mvp-presenters-that-survive-configuration-changes-part-2/)
 */
public class PresenterControllerDelegate<P extends Presenter> {

  private boolean isDestroyedBySystem;
  private P presenter;

  public void onCreate(P presenter, Bundle savedInstanceState) {
    this.presenter = presenter;
    presenter.onCreate(savedInstanceState);
  }

  @SuppressWarnings("unchecked")
  public void onViewCreated(View view) {
    try {
      presenter.bindView(view);
    } catch (ClassCastException e) {
      throw new RuntimeException("The view provided does not"
          + " implement the view interface expected by "
          + presenter.getClass().getSimpleName(), e);
    }
  }

  public void onResume() {
    isDestroyedBySystem = false;
  }

  public void onSaveInstanceState(Bundle outState) {
    isDestroyedBySystem = true;
    PresenterBundle bundle = new PresenterBundle();
    presenter.onSaveInstanceState(bundle);
    setPresenterBundle(outState, bundle);
  }

  public void onDestroyView() {
    presenter.unbindView();
  }

  public void onDestroy() {
    if (!isDestroyedBySystem) {
      presenter.onDestroy();
    }
  }
}

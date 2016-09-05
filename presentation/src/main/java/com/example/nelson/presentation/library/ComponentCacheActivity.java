package com.example.nelson.presentation.library;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * This is a convenience class that extends from AppCompatActivity and implements ComponentCache.
 * It delegates all of the hard work to ComponentCacheDelegate.
 *
 * @see ComponentCache
 *
 * From [here] (http://blog.bradcampbell.nz/mvp-presenters-that-survive-configuration-changes-part-2/)
 */
public class ComponentCacheActivity extends AppCompatActivity implements ComponentCache {

  private ComponentCacheDelegate delegate =
      new ComponentCacheDelegate();

  @Override public void onCreate(Bundle savedInstanceState) {
    delegate.onCreate(savedInstanceState,
        getLastCustomNonConfigurationInstance());
    super.onCreate(savedInstanceState);
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    delegate.onSaveInstanceState(outState);
  }

  @Override
  public Object onRetainCustomNonConfigurationInstance() {
    return delegate.onRetainCustomNonConfigurationInstance();
  }

  @Override
  public long generateId() {
    return delegate.generateId();
  }

  @Override
  public final <C> C getComponent(long index) {
    return delegate.getComponent(index);
  }

  @Override
  public void setComponent(long index, Object component) {
    delegate.setComponent(index, component);
  }
}

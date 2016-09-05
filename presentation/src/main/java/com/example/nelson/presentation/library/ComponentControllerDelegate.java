package com.example.nelson.presentation.library;

import android.os.Bundle;

/**
 * This is a delegate class for Fragments/Activities to use that manages the scoping of its
 * non-configuration component instance. Like the other delegate classes, this typically won't be
 * used by client code. The idea is that you could use this in other Fragment types
 * (e.g. ListFragment, PreferenceFragment, etc) or Activities.
 *
 * From [here] (http://blog.bradcampbell.nz/mvp-presenters-that-survive-configuration-changes-part-2/)
 */
public class ComponentControllerDelegate<C> {

  private static final String PRESENTER_INDEX_KEY
      = "presenter-index";

  private C component;
  private ComponentCache cache;
  private long componentId;
  private boolean isDestroyedBySystem;

  public void onCreate(ComponentCache cache,
                       Bundle savedInstanceState,
                       ComponentFactory<C> componentFactory) {
    this.cache = cache;
    if (savedInstanceState == null) {
      componentId = cache.generateId();
    } else {
      componentId = savedInstanceState
          .getLong(PRESENTER_INDEX_KEY);
    }
    component = cache.getComponent(componentId);
    if (component == null) {
      component = componentFactory.createComponent();
      cache.setComponent(componentId, component);
    }
  }

  public void onResume() {
    isDestroyedBySystem = false;
  }

  public void onSaveInstanceState(Bundle outState) {
    isDestroyedBySystem = true;
    outState.putLong(PRESENTER_INDEX_KEY, componentId);
  }

  public void onDestroy() {
    if (!isDestroyedBySystem) {
      // User is exiting this view, remove component
      // from the cache
      cache.setComponent(componentId, null);
    }
  }

  public C getComponent() {
    return component;
  }
}

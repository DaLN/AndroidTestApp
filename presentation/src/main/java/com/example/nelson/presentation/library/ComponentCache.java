package com.example.nelson.presentation.library;

/**
 * This is an interface for the Activity that will act as the cache for all of the
 * non-configuration components.
 *
 * From [here] (http://blog.bradcampbell.nz/mvp-presenters-that-survive-configuration-changes-part-2/)
 */
public interface ComponentCache {

  long generateId();

  <C> C getComponent(long index);

  void setComponent(long index, Object component);
}

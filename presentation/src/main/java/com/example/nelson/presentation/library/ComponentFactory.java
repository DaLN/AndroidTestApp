package com.example.nelson.presentation.library;

/**
 * An interface that defines a createComponent() method. This is used with
 * ComponentControllerDelegate.
 *
 * From [here] (http://blog.bradcampbell.nz/mvp-presenters-that-survive-configuration-changes-part-2/)
 */
public interface ComponentFactory<C> {

  public C createComponent();
}

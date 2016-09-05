package com.example.nelson.presentation.library;

import com.example.nelson.presentation.presenter.Presenter;

/**
 * All components used with PresenterControllerFragment must extends from this interface so that
 * the library has a way of retrieving the presenter instance from the component and calling the
 * presenter lifecycle methods automatically for you.
 *
 * From [here] (http://blog.bradcampbell.nz/mvp-presenters-that-survive-configuration-changes-part-2/)
 */
public interface HasPresenter<P> {

  Presenter getPresenter();

  boolean hasPresenter();
}

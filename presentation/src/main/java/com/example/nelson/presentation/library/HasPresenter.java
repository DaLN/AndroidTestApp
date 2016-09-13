package com.example.nelson.presentation.library;

public interface HasPresenter<P extends Presenter> {
    P getPresenter();
}

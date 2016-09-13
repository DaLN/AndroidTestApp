package com.example.nelson.presentation.library;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.nelson.domain.exception.DefaultErrorBundle;
import com.example.nelson.domain.exception.ErrorBundle;
import com.example.nelson.presentation.model.HeaderInfoModel;

public interface Presenter<T> {
    void onCreate(@Nullable PresenterBundle bundle);
    void onSaveInstanceState(@NonNull PresenterBundle bundle);
    void onDestroy();
    void bindView(T view);
    void unbindView();

    void showErrorMessage(ErrorBundle defaultErrorBundle);
}

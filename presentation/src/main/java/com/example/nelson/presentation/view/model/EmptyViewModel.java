package com.example.nelson.presentation.view.model;

/**
 * Interface to determine empty state for view model
 * From [here] (https://github.com/nbarishok/RxMvpAndroid/blob/master/app/src/main/java/com/onemanparty/rxmvpandroid/core/view/view_model/EmptyViewModel.java)
 */
public abstract class EmptyViewModel {
  boolean isEmpty() {
    return false;
  }
}

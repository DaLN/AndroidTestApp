package com.example.nelson.presentation.view.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.nelson.presentation.DevTestApplication;
import com.example.nelson.presentation.model.ScoreModel;
import com.example.nelson.presentation.presenter.MainPresenter;
import com.example.nelson.presentation.view.LoadDataView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Nelson on 15/08/2016.
 */
public class DetailFragment extends Fragment implements LoadDataView {

  @Inject
  MainPresenter mainPresenter;

  public DetailFragment() {
    //DevTestApplication.getDevTestApplication().getTestAppComponent().inject(this);
    mainPresenter.setLoadDataView(this);
  }

  public void refreshScores(List<ScoreModel> scores) {

  }

  /**
   * Shows a {@link android.widget.Toast} message.
   *
   * @param message message to be shown.
   */
  protected void showToastMessage(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }

  /**
   * Indicate that the view is loading data.
   */
  @Override
  public void showLoading() {

  }

  /**
   * Remove the indication that the view is loading data.
   */
  @Override
  public void hideLoading() {

  }

  /**
   * Indicate that the view is retrying to load the data after an error.
   */
  @Override
  public void showRetry() {

  }

  /**
   * Remove the indication that the view is retrying to load the data.
   */
  @Override
  public void hideRetry() {

  }

  /**
   * Show an error message
   *
   * @param message Error message.
   */
  @Override
  public void showError(String message) {

  }

  /**
   * Get a {@link Context}.
   */
  @Override
  public Context context() {
    return null;
  }
}

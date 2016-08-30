package com.example.nelson.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.nelson.presentation.DevTestApplication;
import com.example.nelson.presentation.R;
import com.example.nelson.presentation.model.ScoreModel;
import com.example.nelson.presentation.navigator.NavigationManager;
import com.example.nelson.presentation.presenter.MainPresenter;
import com.example.nelson.presentation.view.ScoreListView;
import com.example.nelson.presentation.view.adapter.ScoreListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nelson on 15/08/2016.
 */
public class GameDataFragment extends ListFragment implements ScoreListView {

  @Inject
  MainPresenter mainPresenter;

  private ArrayAdapter<ScoreModel> adapter;

  @BindView(R.id.view_progress)
  RelativeLayout viewProgress;

  @BindView(R.id.view_retry)
  RelativeLayout viewRetry;

  @Inject
  public GameDataFragment() {
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.fragment_gamedata, container, false);
    if (getActivity() != null && this.isAdded()) {
      setListAdapter(adapter = new ScoreListAdapter(getActivity(), R.layout.fragment_item));
    }
    DevTestApplication.getDevTestApplication().getTestAppComponent().inject(this);
    ButterKnife.bind(this, view);
    return view;
  }

  /**
   * Attach to list view once the view hierarchy has been created.
   *
   * @param view
   * @param savedInstanceState
   */
  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mainPresenter.setLoadDataView(this);
    mainPresenter.callData();
  }


  @Override
  public void onListItemClick(ListView listView, View view, int position, long id) {
    DetailFragment detailFragment = new DetailFragment();

    ScoreModel score = adapter.getItem(position);

    NavigationManager.navigateToScoreDetails(getActivity(), score);
  }

  /**
   * Shows a {@link android.widget.Toast} message.
   *
   * @param message message to be shown.
   */
  protected void showToastMessage(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void showLoading() {
    Log.d("NELSON", "showLoading, viewProgress = " + viewProgress);
    this.viewProgress.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideLoading() {
    Log.d("NELSON", "hideLoading, viewProgress = " + viewProgress);
    this.viewProgress.setVisibility(View.GONE);
  }

  @Override
  public void showRetry() {
    this.viewRetry.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideRetry() {
    this.viewRetry.setVisibility(View.GONE);
  }

  @Override
  public void showError(String message) {
    this.showToastMessage(message);
  }


  /**
   * Get a {@link Context}.
   */
  @Override
  public Context getContext() {
    return null;
  }

  /**
   * Render a list of scores in the UI.
   *
   * @param scores The list of {@link ScoreModel} that will be shown.
   */
  @Override
  public void renderScoreList(List<ScoreModel> scores) {
    adapter.clear();
    adapter.addAll(scores);
  }
}

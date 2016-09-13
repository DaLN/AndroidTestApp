package com.example.nelson.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nelson.presentation.R;
import com.example.nelson.presentation.dagger2.component.DaggerGameDataComponent;
import com.example.nelson.presentation.dagger2.component.GameDataComponent;
import com.example.nelson.presentation.library.PresenterControllerListFragment;
import com.example.nelson.presentation.model.ScoreModel;
import com.example.nelson.presentation.navigator.NavigationManager;
import com.example.nelson.presentation.presenter.GameDataPresenter;
import com.example.nelson.presentation.view.adapter.ScoreListAdapter;


import butterknife.ButterKnife;

import static com.example.nelson.presentation.DevTestApplication.getTestAppComponent;

/**
 * Created by Nelson on 15/08/2016.
 */
public class GameDataFragment
    extends PresenterControllerListFragment<GameDataComponent, GameDataPresenter> {

  private ArrayAdapter<ScoreModel> adapter;

  protected GameDataComponent onCreateNonConfigurationComponent() {
    return DaggerGameDataComponent.builder()
        .testAppComponent(getTestAppComponent(getActivity()))
        .build();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }


  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    GameDataLayout view = (GameDataLayout) inflater.inflate(R.layout.fragment_gamedata, container, false);
    if (getActivity() != null && this.isAdded()) {
      setListAdapter(adapter = new ScoreListAdapter(getActivity(), R.layout.fragment_item));
    }
    view.setAdapter(adapter);
    ButterKnife.bind(this, view);
    return getComponent().inject((GameDataLayout) view);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override
  public void onListItemClick(ListView listView, View view, int position, long id) {
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
}

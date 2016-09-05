package com.example.nelson.presentation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.example.nelson.presentation.dagger2.component.GameDataComponent;
import com.example.nelson.presentation.library.ComponentCache;
import com.example.nelson.presentation.library.ComponentControllerDelegate;
import com.example.nelson.presentation.library.ComponentFactory;
import com.example.nelson.presentation.model.GameDataModel;
import com.example.nelson.presentation.model.ScoreModel;
import com.example.nelson.presentation.navigator.NavigationManager;
import com.example.nelson.presentation.presenter.GameDataPresenterImpl;
import com.example.nelson.presentation.view.GameDataView;
import com.example.nelson.presentation.view.LceView;
import com.example.nelson.presentation.view.adapter.ScoreListAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nelson on 15/08/2016.
 */
public class GameDataFragment extends ListFragment {

  @Inject
  GameDataPresenterImpl mainPresenter;

  private GameDataModel gameDataModel;

  private ArrayAdapter<ScoreModel> adapter;

  private ComponentCache componentCache;
  private ComponentControllerDelegate<GameDataComponent> componentDelegate =
      new ComponentControllerDelegate<>();

  private ComponentFactory<GameDataComponent> componentFactory =
      new ComponentFactory<GameDataComponent>() {
        @NonNull
        @Override
        public GameDataComponent createComponent() {
          return onCreateNonConfigurationComponent();
        }
      };

  private GameDataComponent onCreateNonConfigurationComponent() {
    return GameDataComponent.builder()
        .appComponent(getAppComponent(getActivity()))
        .build();
  }

  @Inject
  public GameDataFragment() {
  }


  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof ComponentCache) {
      componentCache = (ComponentCache)context;
    } else {
      throw new RuntimeException(getClass().getSimpleName()
          + " must be attached to "
          + "an Activity that implements "
          + ComponentCache.class.getSimpleName());
    }
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    componentDelegate.onCreate(componentCache, savedInstanceState, componentFactory);
  }

  @Override
  public void onResume() {
    super.onResume();
    componentDelegate.onResume();
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    componentDelegate.onSaveInstanceState(outState);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    componentDelegate.onDestroy();
  }

  public GameDataComponent getComponent() {
    return componentDelegate.getComponent();
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

    mainPresenter.setLceView((LceView) view);
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
}

package com.example.nelson.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nelson.presentation.DevTestApplication;
import com.example.nelson.presentation.R;
import com.example.nelson.presentation.navigator.NavigationManager;
import com.example.nelson.presentation.presenter.GameDataPresenterImpl;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nelson on 15/08/2016.
 */
public class DetailFragment extends Fragment {

  @Inject
  GameDataPresenterImpl mainPresenter;

  @BindView(R.id.scoreNameText)
  TextView scoreNameText;

  @BindView(R.id.scoreJackpotText)
  TextView scoreJackpotText;

  @BindView(R.id.scoreDateText)
  TextView scoreDateText;

  public DetailFragment() {

  }

  /**
   * {@inheritDoc}
   */
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    RelativeLayout view =
        (RelativeLayout) inflater.inflate(R.layout.fragment_score_details, container, false);

    return view;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    DevTestApplication.getDevTestApplication().getTestAppComponent().inject(this);
    ButterKnife.bind(this, getActivity());
    //mainPresenter.setLceView(this);
    mainPresenter.hideLastLoginDate();
    Bundle bundle = getArguments();
    scoreNameText.setText(bundle.getString(NavigationManager.NAME_FRAGMENT_ARGUMENT));
    scoreDateText.setText(bundle.getString(NavigationManager.DATE_FRAGMENT_ARGUMENT));
    scoreJackpotText.setText(bundle.getString(NavigationManager.JACKPOT_FRAGMENT_ARGUMENT));
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
   * Get a {@link Context}.
   */
  @Override
  public Context getContext() {
    return null;
  }

  /**
   * Called when the fragment is no longer attached to its activity.  This
   * is called after {@link #onDestroy()}.
   */
  @Override
  public void onDetach() {
    super.onDetach();
    mainPresenter.showLastLoginDate();
  }
}

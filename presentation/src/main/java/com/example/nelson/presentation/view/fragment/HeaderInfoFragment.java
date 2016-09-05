package com.example.nelson.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nelson.presentation.R;
import com.example.nelson.presentation.dagger2.component.HeaderInfoComponent;
import com.example.nelson.presentation.library.PresenterControllerFragment;
import com.example.nelson.presentation.model.HeaderInfoModel;
import com.example.nelson.presentation.presenter.HeaderInfoPresenter;
import com.example.nelson.presentation.view.HeaderView;
import com.example.nelson.presentation.view.LceView;

import butterknife.BindView;

/**
 * Created by Nelson on 04/09/2016.
 */
public class HeaderInfoFragment
    extends PresenterControllerFragment<HeaderInfoComponent, HeaderInfoPresenter>
    implements HeaderView {

  @BindView(R.id.userAvatar)
  ImageView userAvatarView;

  @BindView(R.id.playerNameText)
  TextView playerNameView;

  @BindView(R.id.balancePlayerText)
  TextView balancePlayerView;

  @BindView(R.id.lastLoginDateText)
  TextView lastLoginDateView;

  /**
   * Called to have the fragment instantiate its user interface view.
   * This is optional, and non-graphical fragments can return null (which
   * is the default implementation).  This will be called between
   * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
   * <p/>
   * <p>If you return a View from here, you will later be called in
   * {@link #onDestroyView} when the view is being released.
   *
   * @param inflater           The LayoutInflater object that can be used to inflate
   *                           any views in the fragment,
   * @param container          If non-null, this is the parent view that the fragment's
   *                           UI should be attached to.  The fragment should not add the view itself,
   *                           but this can be used to generate the LayoutParams of the view.
   * @param savedInstanceState If non-null, this fragment is being re-constructed
   *                           from a previous saved state as given here.
   * @return Return the View for the fragment's UI, or null.
   */
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return getComponent()
        .inject(
            inflater.inflate(R.layout.fragment_header,
                container, false));  }

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
   * @param data
   */
  @Override
  public void setData(HeaderInfoModel data) {

  }

  @Override
  public void loadData() {

  }

  /**
   *
   */
  @Override
  public void showContent() {

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

  @Override
  protected Object onCreateNonConfigurationComponent() {
    return DaggerHello1Component.builder()
        .appComponent(getAppComponent(getActivity()))
        .build();
  }
}

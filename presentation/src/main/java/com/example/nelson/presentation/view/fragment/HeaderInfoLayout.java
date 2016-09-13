package com.example.nelson.presentation.view.fragment;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nelson.presentation.R;
import com.example.nelson.presentation.model.HeaderInfoModel;
import com.example.nelson.presentation.presenter.HeaderInfoPresenter;
import com.example.nelson.presentation.view.HeaderInfoView;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nelson on 12/09/2016.
 */
public class HeaderInfoLayout extends RelativeLayout implements HeaderInfoView {

  @Inject
  HeaderInfoPresenter presenter;

  private HeaderInfoModel model;

  @BindView(R.id.userAvatar)
  ImageView userAvatarView;

  @BindView(R.id.playerNameText)
  TextView playerNameView;

  @BindView(R.id.balancePlayerText)
  TextView balancePlayerView;

  @BindView(R.id.lastLoginDateText)
  TextView lastLoginDateView;

  @BindView(R.id.view_progress_header)
  RelativeLayout viewProgress;

  @BindView(R.id.view_retry_header)
  RelativeLayout viewRetry;

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.bind(this);
  }

  /**
   * Indicate that the view is loading data.
   */
  @Override
  public void showLoading() {
    Log.d("NELSON", "HeaderInfoLayout, showLoading, viewProgress = " + viewProgress);
    this.viewProgress.setVisibility(VISIBLE);
  }

  /**
   * Remove the indication that the view is loading data.
   */
  @Override
  public void hideLoading() {
    Log.d("NELSON", "HeaderInfoLayout, hideLoading, viewProgress = " + viewProgress);
    this.viewProgress.setVisibility(GONE);
  }

  /**
   * @param data
   */
  @Override
  public void setData(HeaderInfoModel data) {
    model = data;
  }

  @Override
  public void loadData() {
    presenter.callData();
  }

  /**
   *
   */
  @Override
  public void showContent() {
    refreshHeaderInfo(model);
  }

  /**
   * Indicate that the view is retrying to load the data after an error.
   */
  @Override
  public void showRetry() {
    this.viewRetry.setVisibility(VISIBLE);
  }

  /**
   * Remove the indication that the view is retrying to load the data.
   */
  @Override
  public void hideRetry() {
    this.viewRetry.setVisibility(GONE);
  }

  @Override
  public void showError(HeaderInfoError gameDataError) {

  }

  public void refreshHeaderInfo(HeaderInfoModel headerInfoModel) {
    Picasso
        .with(getContext())
        .load(String.valueOf(headerInfoModel.getAvatarURL()))
        .into(userAvatarView);
    playerNameView.setText(headerInfoModel.getPlayerName());

    NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
    balancePlayerView.setText(numberFormat.format(headerInfoModel.getBalance()));

    DateFormat dateFormat =
        SimpleDateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM,
            Locale.getDefault());
    lastLoginDateView.setText(dateFormat.format(headerInfoModel.getLastLogindate()));
  }

  @Override
  public void showLastLoginDate() {
    this.lastLoginDateView.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideLastLoginDate() {
    this.lastLoginDateView.setVisibility(View.GONE);
  }


  public HeaderInfoLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }
}

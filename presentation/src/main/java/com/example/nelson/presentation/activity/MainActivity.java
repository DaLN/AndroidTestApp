package com.example.nelson.presentation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.nelson.presentation.DevTestApplication;
import com.example.nelson.presentation.R;
import com.example.nelson.presentation.library.ComponentCacheActivity;
import com.example.nelson.presentation.model.HeaderInfoModel;
import com.example.nelson.presentation.presenter.GameDataPresenter;
import com.example.nelson.presentation.presenter.GameDataPresenterImpl;
import com.example.nelson.presentation.view.fragment.GameDataFragment;
import com.example.nelson.presentation.view.fragment.HeaderInfoFragment;
import com.squareup.picasso.Picasso;


import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import butterknife.BindView;
import butterknife.ButterKnife;

@Singleton
public class MainActivity extends ComponentCacheActivity {

  @Inject
  GameDataPresenter mainPresenter;

  @Inject
  public MainActivity() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    DevTestApplication.getDevTestApplication().getTestAppComponent().inject(this);
    ButterKnife.bind(this);

    mainPresenter.setMainActivity(this);

    if (findViewById(R.id.activity_contentContainer) != null) {
      if (savedInstanceState != null) {
        return;
      }

      HeaderInfoFragment headerInfoFragment = new HeaderInfoFragment();
      headerInfoFragment.setArguments(getIntent().getExtras());
      headerInfoFragment.setArguments(getIntent().getExtras());
      getSupportFragmentManager().beginTransaction()
          .add(R.id.activity_headerContainer, headerInfoFragment).commit();

      GameDataFragment gameDataFragment = new GameDataFragment();
      gameDataFragment.setArguments(getIntent().getExtras());
      getSupportFragmentManager().beginTransaction()
          .add(R.id.activity_contentContainer, gameDataFragment).commit();
    }

  }


  /**
   * {@inheritDoc}
   */
  @Override
  protected void onDestroy() {
    mainPresenter.rxUnSubscribe();
    super.onDestroy();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void onPause() {
    mainPresenter.rxUnSubscribe();
    super.onPause();
  }

  public void refreshHeaderInfo(HeaderInfoModel headerInfoModel) {
    Picasso
        .with(this)
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

  public void showLastLoginDate() {
    this.lastLoginDateView.setVisibility(View.VISIBLE);
  }

  public void hideLastLoginDate() {
    this.lastLoginDateView.setVisibility(View.GONE);
  }
}

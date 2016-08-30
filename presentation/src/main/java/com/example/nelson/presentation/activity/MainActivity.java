package com.example.nelson.presentation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.nelson.presentation.DevTestApplication;
import com.example.nelson.presentation.R;
import com.example.nelson.presentation.model.HeaderInfoModel;
import com.example.nelson.presentation.navigator.NavigationManager;
import com.example.nelson.presentation.presenter.MainPresenter;
import com.example.nelson.presentation.view.fragment.GameDataFragment;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;

import javax.inject.Inject;
import javax.inject.Singleton;

import butterknife.BindView;
import butterknife.ButterKnife;

@Singleton
public class MainActivity extends AppCompatActivity {

  @Inject
  MainPresenter mainPresenter;

  @BindView(R.id.userAvatar)
  ImageView userAvatarView;

  @BindView(R.id.playerNameText)
  TextView playerNameView;

  @BindView(R.id.balancePlayerText)
  TextView balancePlayerView;

  @BindView(R.id.lastLoginDateText)
  TextView lastLoginDateView;

  @Inject
  public MainActivity() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    DevTestApplication.getDevTestApplication().getTestAppComponent().inject(this);
    ButterKnife.bind(this);

    mainPresenter.setMainActivity(this);

    if (findViewById(R.id.fragmentContainer) != null) {
      if (savedInstanceState != null) {
        return;
      }

      GameDataFragment gameDataFragment = new GameDataFragment();
      gameDataFragment.setArguments(getIntent().getExtras());
      getSupportFragmentManager().beginTransaction()
          .add(R.id.fragmentContainer, gameDataFragment).commit();
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

    lastLoginDateView.setText(
        headerInfoModel.getLastLogindate().toString(NavigationManager.DATE_PATTERN));
  }
}

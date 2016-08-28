package com.example.nelson.presentation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.nelson.presentation.DevTestApplication;
import com.example.nelson.presentation.R;
import com.example.nelson.presentation.model.HeaderInfoModel;
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
    DevTestApplication.getDevTestApplication().getTestAppComponent().inject(this);
    ButterKnife.bind(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Check that the activity is using the layout version with
    // the fragment_container FrameLayout
    if (findViewById(R.id.fragmentContainer) != null) {

      // However, if we're being restored from a previous state,
      // then we don't need to do anything and should return or else
      // we could end up with overlapping fragments.
      if (savedInstanceState != null) {
        return;
      }

      // Create a new Fragment to be placed in the activity layout
      GameDataFragment gameDataFragment = new GameDataFragment();

      // In case this activity was started with special instructions from an
      // Intent, pass the Intent's extras to the fragment as arguments
      gameDataFragment.setArguments(getIntent().getExtras());

      // Add the fragment to the 'fragment_container' FrameLayout
      getSupportFragmentManager().beginTransaction()
          .add(R.id.fragmentContainer, gameDataFragment).commit();
      mainPresenter.callData();
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

    lastLoginDateView.setText(headerInfoModel.getLastLogindate().toString());
  }
}

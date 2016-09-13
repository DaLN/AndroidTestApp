package com.example.nelson.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nelson.presentation.R;
import com.example.nelson.presentation.dagger2.component.DaggerHeaderInfoComponent;
import com.example.nelson.presentation.dagger2.component.HeaderInfoComponent;
import com.example.nelson.presentation.library.PresenterControllerFragment;
import com.example.nelson.presentation.presenter.HeaderInfoPresenter;
import com.example.nelson.presentation.view.HeaderInfoView;

import butterknife.ButterKnife;

import static com.example.nelson.presentation.DevTestApplication.getTestAppComponent;

/**
 * Created by Nelson on 04/09/2016.
 */
public class HeaderInfoFragment
    extends PresenterControllerFragment<HeaderInfoComponent, HeaderInfoPresenter> {

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getComponent().inject(this);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    HeaderInfoLayout view =
        (HeaderInfoLayout) inflater.inflate(R.layout.fragment_header, container, false);
    return getComponent().inject(view);
  }

  @Override
  protected HeaderInfoComponent onCreateNonConfigurationComponent() {
    return DaggerHeaderInfoComponent.builder()
        .testAppComponent(getTestAppComponent(getActivity()))
        .build();
  }
}

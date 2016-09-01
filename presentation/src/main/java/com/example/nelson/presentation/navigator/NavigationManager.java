package com.example.nelson.presentation.navigator;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.nelson.presentation.R;
import com.example.nelson.presentation.model.ScoreModel;
import com.example.nelson.presentation.view.fragment.DetailFragment;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.NumberFormat;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Nelson on 19/08/2016.
 */
@Singleton
public class NavigationManager {

  public static final String NAME_FRAGMENT_ARGUMENT = "name";
  public static final String JACKPOT_FRAGMENT_ARGUMENT = "jackpot";
  public static final String DATE_FRAGMENT_ARGUMENT = "date";

  @Inject
  public NavigationManager() {
  }

  /**
   * Goes to the user details screen.
   *
   * @param context An Activity Context needed to change the fragment shown.
   */
  public static void navigateToScoreDetails(Context context, ScoreModel score) {
    if (context != null) {
      DetailFragment detailFragment = new DetailFragment();

      Bundle args = new Bundle();
      args.putString(NAME_FRAGMENT_ARGUMENT, score.getName());
      Locale locale = Locale.getDefault();
      NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
      args.putString(JACKPOT_FRAGMENT_ARGUMENT,
          numberFormat.format(Integer.parseInt(score.getJackpot())));

      DateTimeFormatter fmt =  DateTimeFormat.mediumDateTime();
      fmt.withLocale(locale);
      args.putString(DATE_FRAGMENT_ARGUMENT, fmt.print(score.getDate()));
      detailFragment.setArguments(args);



      FragmentTransaction transaction = ((FragmentActivity) context).getSupportFragmentManager()
          .beginTransaction();

      // Replace whatever is in the fragment_container view with this fragment,
      // and add the transaction to the back stack
      transaction.replace(R.id.fragmentContainer, detailFragment);
      transaction.addToBackStack(null);

      // Commit the transaction
      transaction.commit();
    }
  }
}

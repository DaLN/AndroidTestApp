package com.example.nelson.presentation.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nelson.presentation.R;
import com.example.nelson.presentation.model.ScoreModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nelson on 15/08/2016.
 */
public class ScoreListAdapter extends ArrayAdapter<ScoreModel> {

  private Activity activity;

  public ScoreListAdapter(Activity activity, int resource) {
    super(activity, resource);
    this.activity = activity;
    ButterKnife.bind(this, activity);
  }

  @Override
  public View getView(int position, View view, ViewGroup parent) {
    ScoreModel score = getItem(position);

    ViewHolder holder;
    LayoutInflater inflater = LayoutInflater.from(activity);
    if (view != null) {
      holder = (ViewHolder) view.getTag();
    } else {
      view = inflater.inflate(R.layout.fragment_item, parent, false);
      holder = new ViewHolder(view);
      view.setTag(holder);
    }
    holder.textView.setText(score.getName());
    return view;
  }


  static final class ViewHolder {
    @BindView(R.id.gameDataListTextItem)
    TextView textView;

    ViewHolder(View view) {
      ButterKnife.bind(this, view);
    }
  }
}

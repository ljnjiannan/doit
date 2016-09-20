package com.nannan.doit.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Johan on 16/9/16.
 */

public abstract class BaseFragment extends Fragment {

  protected String TAG="BaseFragmentLog";


  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(setCOntentViewId(), container, false);
    ButterKnife.bind(this, view);
    initView(view);
    initData();
    return view;
  }

  protected abstract int setCOntentViewId();

  protected abstract void initView(View view);

  protected abstract void initData();

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

}

package com.nannan.doit.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.nannan.doit.R;

import butterknife.ButterKnife;

/**
 * @author ljnjiannan
 * @since 16/9/11.
 */

public abstract class BaseActivity extends AppCompatActivity{

  protected String TAG="BaseActivityLog";

  private Toolbar mToolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if(setCOntentViewId()!=0) {
      setContentView(setCOntentViewId());
    }
    initToolBar();
    initView();
    initData();
  }

  @Override
  public void setContentView(int layoutResID) {
    super.setContentView(layoutResID);
    ButterKnife.bind(this);
  }


  private void initToolBar(){
    mToolbar=ButterKnife.findById(this,R.id.common_toolbar);
    if (mToolbar!=null){
      setSupportActionBar(mToolbar);
      if(setActivityTitle()!=null) {
        getSupportActionBar().setTitle(setActivityTitle());
      }
    }
  }

  protected abstract String setActivityTitle();

  protected abstract int setCOntentViewId();

  protected abstract void initView();

  protected abstract void initData();
}

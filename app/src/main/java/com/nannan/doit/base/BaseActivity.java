package com.nannan.doit.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.nannan.doit.R;
import com.nannan.doit.rx.RxUtils;

import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

import static com.nannan.doit.data.DIConstants.IntentKey.EXTRA_FROM_TAG;

/**
 * @author ljnjiannan
 * @since 16/9/11.
 */

public abstract class BaseActivity extends AppCompatActivity{

  protected String TAG="BaseActivityLog";

  protected Toolbar mToolbar;
  protected CompositeSubscription rxSubscription = new CompositeSubscription();
  protected String mFromTag = null;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle bundle=getIntent().getExtras();
    getExtra(bundle);


    if(setContentViewId()!=0) {
      setContentView(setContentViewId());
    }
    //是否开启rxbus订阅支持
    if (isBindRxBus()) {
      rxSubscription = RxUtils.getNewCompositeSubIfUnsubscribed(rxSubscription);
      initRxEvent();
    }

    if (getIntent().hasExtra(EXTRA_FROM_TAG)) {
      mFromTag = getIntent().getStringExtra(EXTRA_FROM_TAG);
    }

    mToolbar = ButterKnife.findById(this, R.id.common_toolbar);


    if(mToolbar!=null){
      setSupportActionBar(mToolbar);
      initToolBar();
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);

      mToolbar.setNavigationOnClickListener(view -> finish());
    }
    initRxEvent();
    initView();
    initData();
  }

  protected abstract void getExtra(Bundle extra);

  protected abstract void initRxEvent();

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

  protected void setViewTitle(String title){
    if(!title.isEmpty()){
      getSupportActionBar().setTitle(title);
    }
  }


  @Override
  protected void onDestroy() {
    super.onDestroy();
    if(rxSubscription!=null && !rxSubscription.isUnsubscribed()) {
      rxSubscription.unsubscribe();
    }
  }

  protected abstract String setActivityTitle();

  protected abstract int setContentViewId();

  protected abstract void initView();

  protected abstract void initData();

  protected abstract boolean isBindRxBus();
}

package com.nannan.doit.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;

import com.nannan.doit.R;
import com.nannan.doit.base.BaseActivity;
import com.nannan.doit.data.DIConstants;
import com.nannan.doit.rx.RxBus;
import com.nannan.doit.rx.event.MissionCateSelectedEvent;
import com.nannan.doit.utils.IntentUtil;
import com.nannan.doit.vp.ipresenter.IHomePresenter;
import com.nannan.doit.vp.iview.IHomeView;
import com.nannan.doit.vp.presenter.HomePresenter;

import butterknife.Bind;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity
        implements IHomeView {

  @Bind(R.id.fab_home_add_mission)
  FloatingActionButton fabHomeAddMission;
  @Bind(R.id.draw_layout)
  DrawerLayout drawerLayout;

  private long currentCateId = 0;
  private int currentType=0;


  @Override
  protected void initData() {
    IHomePresenter presenter = new HomePresenter(this);
    presenter.initApplicationData(this);
  }

  @Override
  protected boolean isBindRxBus() {
    return true;
  }

  @Override
  protected void getExtra(Bundle extra) {

  }

  @Override
  protected void initRxEvent() {
    rxSubscription.add(RxBus.getDefault().toObservable(MissionCateSelectedEvent.class)
            .subscribe(event -> {
              currentCateId=event.getModel().getId();
              currentType=event.getModel().getType();
              setTitle(event.getModel().getTitle());
              setListView(currentType,currentCateId);
              drawerLayout.closeDrawers();
            }));

  }

  @Override
  protected String setActivityTitle() {
    return null;
  }

  @Override
  protected int setCOntentViewId() {
    return R.layout.activity_main;
  }

  @Override
  protected void initView() {
    setListView(DIConstants.MissionListType.MTYPE_DEFAULT, currentCateId);
    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
  }

  private void setListView(int viewType, long id) {
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction transaction = fm.beginTransaction();
    Fragment fragment = null;
    switch (viewType) {
      case DIConstants.MissionListType.MTYPE_NORMAL:
        fragment = HomeMissionListNormalFragment.getInstance(id);
        break;
      case DIConstants.MissionListType.MTYPE_DAY:
        fragment = HomeMissionListNormalFragment.getInstance(id);
        break;
      case DIConstants.MissionListType.MTYPE_REPEAT:
        fragment = HomeMissionListNormalFragment.getInstance(id);
        break;
      default:
        break;
    }
    if (fragment == null) {
      return;
    }
    transaction.replace(R.id.home_fragment, fragment);
    transaction.commit();
  }


  @OnClick(R.id.fab_home_add_mission)
  public void onAddFabClick() {
    IntentUtil.openMissionEditActivity(this,currentCateId);
  }


}

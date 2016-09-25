package com.nannan.doit.vp.presenter;

import android.app.Activity;

import com.nannan.doit.data.database.DBFactory;
import com.nannan.doit.vp.ipresenter.IDrawerPresenter;
import com.nannan.doit.vp.iview.IDrawer;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Johan on 16/9/16.
 */

public class DrawerPresenter implements IDrawerPresenter {

  private IDrawer view;

  public DrawerPresenter(IDrawer view) {
    this.view = view;
  }

  @Override
  public void loadData(Activity activitys) {
    DBFactory.getMissionCateModelDao(activitys).queryBuilder().rx()
            .list()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(list -> {
              view.onDataLoad(list);
            },throwable -> {
            });
  }
}

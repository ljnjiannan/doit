package com.nannan.doit.vp.presenter;

import android.app.Activity;

import com.nannan.doit.data.database.DBFactory;
import com.nannan.doit.model.MissionModel;
import com.nannan.doit.utils.DateUtil;
import com.nannan.doit.vp.ipresenter.IHomePresenter;
import com.nannan.doit.vp.iview.IHomeView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author ljnjiannan
 * @since 16/9/12.
 */

public class HomePresenter implements IHomePresenter {

  private IHomeView view;

  public HomePresenter(IHomeView view) {
    this.view = view;
  }

  @Override
  public void loadData(Activity activity) {
    DBFactory.getMissionModelQuery(activity)
        .rx().list()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(missions -> {
          if(missions!=null){
            view.onDataLoad(missions);
          }
        }, throwable -> {

        });
  }

  @Override
  public void deleteMission(Activity activity, MissionModel model) {
    //从数据库删除
    DBFactory.getMissionModelDao(activity).rx()
        .delete(model)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(c -> {
          view.onDeleteMissionSuccess(model);
            },
            throwable -> {
            });
  }

  @Override
  public void setMissionState(Activity activity, MissionModel model) {
    model.setModifyTime(DateUtil.getCurrentTime());
    model.setDone(!model.isDone());
    //更新数据库
    DBFactory.getMissionModelDao(activity).rx()
        .update(model)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe((mission) -> {
          view.setViewStateSuccess();
        }, (throwable) -> {
        });
  }
}

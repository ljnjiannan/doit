package com.nannan.doit.vp.presenter;

import android.app.Activity;

import com.nannan.doit.data.DIConstants;
import com.nannan.doit.data.database.DBFactory;
import com.nannan.doit.model.MissionCateModel;
import com.nannan.doit.vp.ipresenter.IHomePresenter;
import com.nannan.doit.vp.iview.IHomeView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
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
  public void initApplicationData(Activity activity) {
    DBFactory.getMissionCateModelDao(activity).queryBuilder().rx()
            .list()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(list -> {
              if (list.size() <= getInitMissionCateList().size()) {
                initCateList(activity);
              }
            }, throwable -> {
            });
  }


  private void initCateList(Activity activity) {
    Observable.from(getInitMissionCateList())
            .flatMap((Func1<MissionCateModel, Observable<?>>) missionCateModel
                    -> DBFactory.getMissionCateModelDao(activity).rx().
                    insert(missionCateModel))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(O -> {

            }, throwable -> {

            });
  }

  private List<MissionCateModel> getInitMissionCateList() {
    List<MissionCateModel> list = new ArrayList<>();
    MissionCateModel allMission = new MissionCateModel(Long.parseLong("0")
            , "任务榜", DIConstants.MissionListType.MTYPE_NORMAL);
    MissionCateModel todayMission = new MissionCateModel(Long.parseLong("1")
            , "今日任务", DIConstants.MissionListType.MTYPE_DAY);
    MissionCateModel tomorowMission = new MissionCateModel(Long.parseLong("2")
            , "明日任务", DIConstants.MissionListType.MTYPE_DAY);
    MissionCateModel repeatMission = new MissionCateModel(Long.parseLong("3")
            , "每日任务", DIConstants.MissionListType.MTYPE_REPEAT);
    list.add(allMission);
    list.add(todayMission);
    list.add(tomorowMission);
    list.add(repeatMission);
    return list;
  }
}

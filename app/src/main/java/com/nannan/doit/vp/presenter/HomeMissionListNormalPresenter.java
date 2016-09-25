package com.nannan.doit.vp.presenter;

import android.app.Activity;

import com.nannan.doit.data.database.DBFactory;
import com.nannan.doit.model.MissionCateModel;
import com.nannan.doit.model.MissionModel;
import com.nannan.doit.model.MissionModelDao;
import com.nannan.doit.utils.DateUtil;
import com.nannan.doit.utils.LogUtil;
import com.nannan.doit.vp.ipresenter.IHomeMissionListNormalPresenter;
import com.nannan.doit.vp.iview.IHomeMissionListNormal;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Johan on 16/9/16.
 */

public class HomeMissionListNormalPresenter implements IHomeMissionListNormalPresenter {

  public static final String TAG = "HomeMissionListNormalPresenterLog";
  private IHomeMissionListNormal view;

  public HomeMissionListNormalPresenter(IHomeMissionListNormal view) {
    this.view = view;
  }

  @Override
  public void loadData(Activity activity, long cateId) {
    DBFactory.getMissionModelDao(activity).queryBuilder()
        .where(MissionModelDao.Properties.CateId.eq(cateId))
        .rx().list()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(missions -> {
          if (missions != null) {
            view.onDataLoad(missions);
          }
        }, throwable -> {
          LogUtil.error(TAG, throwable.getMessage());
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
              LogUtil.error(TAG, throwable.getMessage());
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
          LogUtil.error(TAG, throwable.getMessage());
        });
  }

  @Override
  public void getMissionMoveList(Activity activity,MissionModel model) {
    DBFactory.getMissionCateModelDao(activity).queryBuilder().rx().list()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(list -> {
          view.showMissionMoveDialog(list,model);
        }, throwable -> {
          LogUtil.error(TAG, throwable.getMessage());
        });
  }

  @Override
  public void moveMission(Activity activity, MissionModel model, MissionCateModel cateModel) {
    model.setCateId(cateModel.getId());
    //更新数据库
    DBFactory.getMissionModelDao(activity).rx()
        .update(model)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe((mission) -> {
          view.setViewStateSuccess();
        }, (throwable) -> {
          LogUtil.error(TAG, throwable.getMessage());
        });
  }

}

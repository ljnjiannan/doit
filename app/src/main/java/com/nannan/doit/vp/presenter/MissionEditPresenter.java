package com.nannan.doit.vp.presenter;

import android.app.Activity;

import com.nannan.doit.data.database.DBFactory;
import com.nannan.doit.model.MissionModel;
import com.nannan.doit.model.MissionModelDao;
import com.nannan.doit.utils.DateUtil;
import com.nannan.doit.utils.LogUtil;
import com.nannan.doit.vp.ipresenter.IMissionEditPresenter;
import com.nannan.doit.vp.iview.IMissionEditView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Johan
 * @since 16/9/23.
 */

public class MissionEditPresenter implements IMissionEditPresenter {

  private static final String TAG = "MissionEditPresenter";
  private IMissionEditView view;

  public MissionEditPresenter(IMissionEditView view) {
    this.view = view;
  }

  @Override
  public void loadData(Activity activity, long id) {
    DBFactory.getMissionModelDao(activity)
        .queryBuilder()
        .where(MissionModelDao.Properties.Id.eq(id)).rx()
        .list()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(missionModels -> {
          if (missionModels.size() != 0) {
            view.onLoadDataSuccess(missionModels.get(0));
          }
        }, throwable -> {
          LogUtil.error(TAG, throwable.getMessage());
        });
  }

  @Override
  public void saveData(Activity activity, String missionName, long type) {
    MissionModel model = new MissionModel();
    model.setTitle(missionName);
    model.setCateId(type);
    model.setAddTime(DateUtil.getCurrentTime());
    DBFactory.getMissionModelDao(activity).rx()
        .insert(model)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(missionModel -> {
          view.onSaveSuccess();
        }, throwable -> {
        });
  }

}

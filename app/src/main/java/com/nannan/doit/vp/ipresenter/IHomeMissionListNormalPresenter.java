package com.nannan.doit.vp.ipresenter;

import android.app.Activity;

import com.nannan.doit.model.MissionModel;

/**
 * Created by Johan on 16/9/16.
 */

public interface IHomeMissionListNormalPresenter {

  void loadData(Activity activity,long cateId);

  void deleteMission(Activity activity,MissionModel model);

  void setMissionState(Activity activity,MissionModel model);
}

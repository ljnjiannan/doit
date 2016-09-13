package com.nannan.doit.vp.ipresenter;

import android.app.Activity;

import com.nannan.doit.model.MissionModel;

/**
 * @author ljnjiannan
 * @since 16/9/12.
 */

public interface IHomePresenter {

  void loadData(Activity activity);

  void deleteMission(Activity activity,MissionModel model);

  void setMissionState(Activity activity,MissionModel model);

}

package com.nannan.doit.vp.iview;

import com.nannan.doit.model.MissionModel;

import java.util.List;

/**
 * Created by Johan on 16/9/16.
 */

public interface IHomeMissionListNormal {


  void onDataLoad(List<MissionModel> list);

  void onDeleteMissionSuccess(MissionModel model);

  void setViewStateSuccess();


}

package com.nannan.doit.vp.iview;

import com.nannan.doit.base.iview.IView;
import com.nannan.doit.model.MissionModel;

import java.util.List;

/**
 * @author ljnjiannan
 * @since 16/9/12.
 */

public interface IHomeView extends IView{

  void onDataLoad(List<MissionModel> list);

  void onDeleteMissionSuccess(MissionModel model);

  void setViewStateSuccess();

}

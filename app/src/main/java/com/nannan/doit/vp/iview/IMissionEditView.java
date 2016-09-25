package com.nannan.doit.vp.iview;

import com.nannan.doit.model.MissionModel;

/**
 * @author Johan
 * @since 16/9/23.
 */

public interface IMissionEditView {

  void onLoadDataSuccess(MissionModel model);

  void onSaveSuccess();

}

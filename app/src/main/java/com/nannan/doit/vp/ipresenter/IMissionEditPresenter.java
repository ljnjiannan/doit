package com.nannan.doit.vp.ipresenter;

import android.app.Activity;

/**
 * @author Johan
 * @since 16/9/23.
 */

public interface IMissionEditPresenter {

  void loadData(Activity activity,long id);

  void saveData(Activity activity,String missionName,long type);

}

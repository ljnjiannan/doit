package com.nannan.doit.data.database;

import android.app.Activity;

import com.nannan.doit.DIApplication;
import com.nannan.doit.model.DaoSession;
import com.nannan.doit.model.MissionCateModelDao;
import com.nannan.doit.model.MissionModelDao;

/**
 * @author ljnjiannan
 * @since 16/9/12.
 */

public class DBFactory {

  private static final Object LOCK = new Object();
  private static MissionModelDao missionModelDao;
  private static MissionCateModelDao missionCateModelDao;

  private static DaoSession getDaoSession(Activity activity) {
    synchronized (LOCK) {
      return ((DIApplication)activity.getApplication()).getDaoSession();
    }
  }

  public static MissionModelDao getMissionModelDao(Activity activity) {
    synchronized (LOCK) {
      if (missionModelDao == null) {
        missionModelDao = getDaoSession(activity).getMissionModelDao();
      }
    }
    return missionModelDao;
  }


  public static MissionCateModelDao getMissionCateModelDao(Activity activity) {
    synchronized (LOCK) {
      if (missionCateModelDao == null) {
        missionCateModelDao = getDaoSession(activity).getMissionCateModelDao();
      }
      return missionCateModelDao;
    }
  }

}

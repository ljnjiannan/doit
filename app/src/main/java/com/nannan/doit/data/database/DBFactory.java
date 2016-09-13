package com.nannan.doit.data.database;

import android.app.Activity;

import com.nannan.doit.DIApplication;
import com.nannan.doit.model.DaoSession;
import com.nannan.doit.model.MissionModel;
import com.nannan.doit.model.MissionModelDao;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.rx.RxDao;
import org.greenrobot.greendao.rx.RxQuery;

/**
 * @author ljnjiannan
 * @since 16/9/12.
 */

public class DBFactory {

  private static final Object LOCK = new Object();
  private static MissionModelDao missionModelDao;
  private static QueryBuilder<MissionModel> missionModelQuery;
  private static RxQuery<MissionModel> missionModelRxQuery;
  private static RxDao<MissionModel, Long> missionModelLongRxDao;

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
      return missionModelDao;
    }
  }

  public static QueryBuilder<MissionModel> getMissionModelQuery(Activity activity){
    synchronized (LOCK){
      if(missionModelQuery==null){
        missionModelQuery=getMissionModelDao(activity).queryBuilder();
      }
    }
    return missionModelQuery;
  }

}

package com.nannan.doit;

import android.app.Application;
import android.content.Context;

import com.nannan.doit.model.DaoMaster;
import com.nannan.doit.model.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * @author ljnjiannan
 * @since 16/9/12.
 */

public class DIApplication extends Application {

  public static final boolean ENCRYPTED = false;

  public static final String DB_NAME="db_doit";
  public static final String DB_NAME_ENCRYPTED="db_doit_encryted";

  private DaoSession daoSession;

  private static DIApplication sInstance;

  public static DIApplication getInstance() {
    return sInstance;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    sInstance=this;

    DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,ENCRYPTED ? DB_NAME_ENCRYPTED : DB_NAME);
    Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
    daoSession = new DaoMaster(db).newSession();
  }

  public static Context getContext() {
    return sInstance.getApplicationContext();
  }


  public DaoSession getDaoSession() {
    return daoSession;
  }

}

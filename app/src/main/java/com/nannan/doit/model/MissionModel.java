package com.nannan.doit.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * @author ljnjiannan
 * @since 16/9/11.
 */

@Entity()
public class MissionModel {

  @Transient
  public static final int MISSION_STATE_DOING = 0;

  @Transient
  public static final int MISSION_STATE_DONE = 1;

  @Id
  private Long id;

  private String title;

  private int state;

  private long cateId;

  private long order;

  private long addTime;

  private long modifyTime;

  @ToOne(joinProperty = "cateId")
  private MissionCateModel cateModel;

  @Generated(hash = 2032296467)
  private transient Long cateModel__resolvedKey;

  /** Used for active entity operations. */
  @Generated(hash = 1661983549)
  private transient MissionModelDao myDao;

  /** Used to resolve relations */
  @Generated(hash = 2040040024)
  private transient DaoSession daoSession;

  public long getModifyTime() {
    return this.modifyTime;
  }

  public void setModifyTime(long modifyTime) {
    this.modifyTime = modifyTime;
  }

  public long getAddTime() {
    return this.addTime;
  }

  public void setAddTime(long addTime) {
    this.addTime = addTime;
  }

  public long getOrder() {
    return this.order;
  }

  public void setOrder(long order) {
    this.order = order;
  }

  public long getCateId() {
    return this.cateId;
  }

  public void setCateId(long cateId) {
    this.cateId = cateId;
  }

  public int getState() {
    return this.state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Generated(hash = 1257918852)
  public MissionModel(Long id, String title, int state, long cateId, long order,
      long addTime, long modifyTime) {
    this.id = id;
    this.title = title;
    this.state = state;
    this.cateId = cateId;
    this.order = order;
    this.addTime = addTime;
    this.modifyTime = modifyTime;
  }

  @Generated(hash = 712115961)
  public MissionModel() {
  }

  public boolean isDone(){
    return state==MISSION_STATE_DONE;
  }

  public void setDone(boolean isDone){
    if(isDone){
      state=MISSION_STATE_DONE;
    }else {
      state=MISSION_STATE_DOING;
    }
  }

  /**
   * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
   * Entity must attached to an entity context.
   */
  @Generated(hash = 1942392019)
  public void refresh() {
    if (myDao == null) {
      throw new DaoException("Entity is detached from DAO context");
    }
    myDao.refresh(this);
  }

  /**
   * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
   * Entity must attached to an entity context.
   */
  @Generated(hash = 713229351)
  public void update() {
    if (myDao == null) {
      throw new DaoException("Entity is detached from DAO context");
    }
    myDao.update(this);
  }

  /**
   * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
   * Entity must attached to an entity context.
   */
  @Generated(hash = 128553479)
  public void delete() {
    if (myDao == null) {
      throw new DaoException("Entity is detached from DAO context");
    }
    myDao.delete(this);
  }

  /** called by internal mechanisms, do not call yourself. */
  @Generated(hash = 604948743)
  public void setCateModel(@NotNull MissionCateModel cateModel) {
    if (cateModel == null) {
      throw new DaoException(
          "To-one property 'cateId' has not-null constraint; cannot set to-one to null");
    }
    synchronized (this) {
      this.cateModel = cateModel;
      cateId = cateModel.getId();
      cateModel__resolvedKey = cateId;
    }
  }

  /** To-one relationship, resolved on first access. */
  @Generated(hash = 148733651)
  public MissionCateModel getCateModel() {
    long __key = this.cateId;
    if (cateModel__resolvedKey == null || !cateModel__resolvedKey.equals(__key)) {
      final DaoSession daoSession = this.daoSession;
      if (daoSession == null) {
        throw new DaoException("Entity is detached from DAO context");
      }
      MissionCateModelDao targetDao = daoSession.getMissionCateModelDao();
      MissionCateModel cateModelNew = targetDao.load(__key);
      synchronized (this) {
        cateModel = cateModelNew;
        cateModel__resolvedKey = __key;
      }
    }
    return cateModel;
  }

  /** called by internal mechanisms, do not call yourself. */
  @Generated(hash = 458464021)
  public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getMissionModelDao() : null;
  }

}

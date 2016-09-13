package com.nannan.doit.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

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

}

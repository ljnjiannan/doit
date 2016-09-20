package com.nannan.doit.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Johan
 * @since 16/9/16.
 */

@Entity
public class MissionCateModel {

  @Id
  private Long id;

  private String title;

  private int type;

  @Transient
  private boolean selected;

  @Generated(hash = 604728112)
  public MissionCateModel(Long id, String title, int type) {
    this.id = id;
    this.title = title;
    this.type = type;
  }

  @Generated(hash = 1131637116)
  public MissionCateModel() {
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

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }


}

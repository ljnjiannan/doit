package com.nannan.doit.rx.event;

import com.nannan.doit.model.MissionCateModel;

/**
 * @author Johan
 * @since 16/9/16.
 */

public class MissionCateSelectedEvent {

  private MissionCateModel model;

  public MissionCateSelectedEvent(MissionCateModel model) {
    this.model=model;
  }

  public MissionCateModel getModel() {
    return model;
  }
}

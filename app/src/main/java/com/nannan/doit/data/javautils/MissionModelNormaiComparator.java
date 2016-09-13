package com.nannan.doit.data.javautils;

import com.nannan.doit.model.MissionModel;

import java.util.Comparator;

/**
 * @author ljnjiannan
 * @since 16/9/12.
 */

public class MissionModelNormaiComparator implements Comparator<MissionModel> {

  @Override
  public int compare(MissionModel t1, MissionModel t2) {
    int state1 = t1.getState();
    int state2 = t2.getState();
    if (state1 < state2) {
      return -1;
    } else if (t1.getState() == t2.getState()) {
      long time1 = t1.getModifyTime() == 0 ? t1.getAddTime() : t1.getModifyTime();
      long time2 = t2.getModifyTime() == 0 ? t2.getAddTime() : t2.getModifyTime();
      if (state1 == 0 && time1 > time2) {
        return -1;
      } else if (state1 == 1 && time1 < time2) {
        return -1;
      }
    }
    return 0;
  }

}

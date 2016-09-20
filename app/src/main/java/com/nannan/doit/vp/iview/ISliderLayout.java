package com.nannan.doit.vp.iview;

import com.nannan.doit.base.iview.IView;
import com.nannan.doit.model.MissionCateModel;

import java.util.List;

/**
 * Created by Johan on 16/9/16.
 */

public interface ISliderLayout extends IView {

  void onDataLoad(List<MissionCateModel> list);
}

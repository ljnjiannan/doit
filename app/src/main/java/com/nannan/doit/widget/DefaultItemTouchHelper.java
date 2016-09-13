package com.nannan.doit.widget;

import android.support.v7.widget.helper.SwipeItemTouchHelper;

/**
 * @author ljnjiannan
 * @since 16/9/12.
 */

public class DefaultItemTouchHelper extends SwipeItemTouchHelper {

  private DefaultItemTouchHelpCallback itemTouchHelpCallback;

  public DefaultItemTouchHelper(DefaultItemTouchHelpCallback.OnItemTouchCallbackListener onItemTouchCallbackListener) {
    super(new DefaultItemTouchHelpCallback(onItemTouchCallbackListener));
    itemTouchHelpCallback = (DefaultItemTouchHelpCallback) getCallback();
  }

  /**
   * 设置是否可以被拖拽
   *
   * @param canDrag 是true，否false
   */
  public void setDragEnable(boolean canDrag) {
    itemTouchHelpCallback.setDragEnable(canDrag);
  }

  /**
   * 设置是否可以被滑动
   *
   * @param canSwipe 是true，否false
   */
  public void setSwipeEnable(boolean canSwipe) {
    itemTouchHelpCallback.setSwipeEnable(canSwipe);
  }

}

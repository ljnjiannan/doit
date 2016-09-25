package com.nannan.doit.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.nannan.doit.R;
import com.nannan.doit.base.BaseRefreshFragment;
import com.nannan.doit.utils.DensityUtil;
import com.nannan.doit.utils.ResUtil;
import com.nannan.doit.widget.DefaultItemTouchHelpCallback;
import com.nannan.doit.widget.DefaultItemTouchHelper;

/**
 * @author Johan
 * @since 16/9/23.
 */

public abstract class HomeBaseMissionListFragment<T> extends BaseRefreshFragment<T>
    implements DefaultItemTouchHelpCallback.OnItemTouchCallbackListener {

  protected long cateId;

  public void setCateId(long id) {
    cateId = id;
  }

  @Override
  protected void initView(View view) {
    super.initView(view);
    DefaultItemTouchHelper itemTouchHelper = new DefaultItemTouchHelper(this);
    itemTouchHelper.attachToRecyclerView(mRecyclerView);
    itemTouchHelper.setDragEnable(true);
    itemTouchHelper.setSwipeEnable(true);
  }

  @Override
  public boolean onMove(int srcPosition, int targetPosition) {
    return false;
  }

  @Override
  public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
      float dX, float dY, int actionState, boolean isCurrentlyActive) {
    if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
      View itemView = viewHolder.itemView;
      Bitmap icon;
      Paint p = new Paint();
      if (dX < 0) {
        icon = BitmapFactory.decodeResource(getResources(),
            R.drawable.ic_mission_move);
        // Set color for right swipe
        p.setColor(ResUtil.getColor(R.color.amber_500));
        // Draw Rect with varying right side, equal to displacement dX
        c.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(),
            itemView.getRight(),
            (float) itemView.getBottom(), p);
        // Set the image icon for right swipe
        c.drawBitmap(icon, (float) itemView.getRight() - DensityUtil.dpToPx(40),
            (float) itemView.getTop() +
                ((float) itemView.getBottom() - (float) itemView.getTop() - icon.getHeight()) / 2,
            p);
        icon.recycle();
      } else {
        icon = BitmapFactory.decodeResource(getResources(),
            R.drawable.ic_mission_done);
        p.setColor(ResUtil.getColor(R.color.light_blue_500));
        c.drawRect((float) itemView.getLeft() + DensityUtil.dpToPx(0), (float) itemView.getTop(),
            dX + DensityUtil.dpToPx(0),
            (float) itemView.getBottom(), p);
        c.drawBitmap(icon, dX-icon.getWidth()-DensityUtil.dpToPx(10),
            (float) itemView.getTop() +
                ((float) itemView.getBottom() - (float) itemView.getTop() - icon.getHeight()) / 2,
            p);
        icon.recycle();
      }
    }
  }
}

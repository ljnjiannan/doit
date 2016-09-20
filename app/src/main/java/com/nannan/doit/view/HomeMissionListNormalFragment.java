package com.nannan.doit.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.nannan.doit.R;
import com.nannan.doit.base.BasePullComplexRecyclerAdapter;
import com.nannan.doit.base.BaseRefreshFragment;
import com.nannan.doit.data.javautils.MissionModelNormaiComparator;
import com.nannan.doit.model.MissionModel;
import com.nannan.doit.recycler.adapter.HomeRecyclerViewAdapter;
import com.nannan.doit.utils.DensityUtil;
import com.nannan.doit.vp.ipresenter.IHomeMissionListNormalPresenter;
import com.nannan.doit.vp.iview.IHomeMissionListNormal;
import com.nannan.doit.vp.presenter.HomeMissionListNormalPresenter;
import com.nannan.doit.widget.DefaultItemTouchHelpCallback;
import com.nannan.doit.widget.DefaultItemTouchHelper;

import java.util.Collections;
import java.util.List;

/**
 * @author  Johan
 * @since 16/9/16.
 */

public class HomeMissionListNormalFragment extends BaseRefreshFragment<MissionModel>
        implements IHomeMissionListNormal
        ,DefaultItemTouchHelpCallback.OnItemTouchCallbackListener{

  private long cateId;

  public static Fragment getInstance(long cateId){
    HomeMissionListNormalFragment fragment=new HomeMissionListNormalFragment();
    fragment.setCateId(cateId);
    return fragment;
  }

  public HomeMissionListNormalFragment(){
  }

  public void setCateId(long id){
    cateId=id;
  }

  private IHomeMissionListNormalPresenter presenter;

  @Override
  public BasePullComplexRecyclerAdapter<MissionModel> getListAdapter() {
    return new HomeRecyclerViewAdapter(mRecyclerView, getActivity());
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
  protected void initData() {
    presenter=new HomeMissionListNormalPresenter(this);
    presenter.loadData(getActivity(),cateId);
  }

  @Override
  public void onDataLoad(List<MissionModel> list) {
    mData.clear();
    Collections.sort(list, new MissionModelNormaiComparator());
    mData.addAll(list);
    mAdapter.refresh(mData);
  }

  @Override
  public void onDeleteMissionSuccess(MissionModel model) {
    if (mData.contains(model)) {
      mData.remove(model);
      mAdapter.refresh(mData);
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    presenter.loadData(getActivity(),cateId);
  }

  @Override
  public void setViewStateSuccess() {
    presenter.loadData(getActivity(),cateId);
  }


  @Override
  public void onSwiped(int adapterPosition, int direction) {
    if (direction == ItemTouchHelper.RIGHT) {
      presenter.setMissionState(getActivity(), mData.get(adapterPosition));
    } else {
      presenter.deleteMission(getActivity(), mData.get(adapterPosition));
    }
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
                R.mipmap.ic_item_delete);
        // Set color for right swipe
        p.setColor(Color.RED);
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
        p.setColor(Color.BLUE);
        c.drawRect((float) itemView.getLeft() + DensityUtil.dpToPx(0), (float) itemView.getTop(),
                dX + DensityUtil.dpToPx(0),
                (float) itemView.getBottom(), p);
      }
    }
  }
}

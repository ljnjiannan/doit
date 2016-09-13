package com.nannan.doit.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.nannan.doit.R;
import com.nannan.doit.base.BasePullComplexRecyclerAdapter;
import com.nannan.doit.base.BaseRefreshActivity;
import com.nannan.doit.data.javautils.MissionModelNormaiComparator;
import com.nannan.doit.model.MissionModel;
import com.nannan.doit.recycler.adapter.HomeRecyclerViewAdapter;
import com.nannan.doit.utils.DensityUtil;
import com.nannan.doit.vp.ipresenter.IHomePresenter;
import com.nannan.doit.vp.iview.IHomeView;
import com.nannan.doit.vp.presenter.HomePresenter;
import com.nannan.doit.widget.DefaultItemTouchHelpCallback;
import com.nannan.doit.widget.DefaultItemTouchHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class HomeActivity extends BaseRefreshActivity
    implements IHomeView, DefaultItemTouchHelpCallback.OnItemTouchCallbackListener {

  @Bind(R.id.fab_home_add_mission) FloatingActionButton fabHomeAddMission;
  private List<MissionModel> mData = new ArrayList<>();
  private IHomePresenter presenter;

  @Override
  protected void initView() {
    super.initView();
    DefaultItemTouchHelper itemTouchHelper = new DefaultItemTouchHelper(this);
    itemTouchHelper.attachToRecyclerView(mRecyclerView);
    itemTouchHelper.setDragEnable(true);
    itemTouchHelper.setSwipeEnable(true);
  }

  @Override
  protected void initData() {
    presenter = new HomePresenter(this);
    presenter.loadData(this);
  }

  @Override
  protected int setCOntentViewId() {
    return R.layout.activity_main;
  }

  @Override
  public BasePullComplexRecyclerAdapter getListAdapter() {
    return new HomeRecyclerViewAdapter(mRecyclerView, this);
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
  public void setViewStateSuccess() {
    presenter.loadData(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    presenter.loadData(this);
  }

  @OnClick(R.id.fab_home_add_mission)
  public void onAddFabClick() {
    startActivity(new Intent(HomeActivity.this, MissionEditActivity.class));
  }

  @Override
  public void onSwiped(int adapterPosition, int direction) {
    if (direction == ItemTouchHelper.RIGHT) {
      presenter.setMissionState(HomeActivity.this,mData.get(adapterPosition));
    } else {
      presenter.deleteMission(HomeActivity.this, mData.get(adapterPosition));
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

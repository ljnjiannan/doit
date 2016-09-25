package com.nannan.doit.view;

import android.view.View;

import com.nannan.doit.base.BasePullComplexRecyclerAdapter;
import com.nannan.doit.base.BaseRefreshFragment;
import com.nannan.doit.model.MissionCateModel;
import com.nannan.doit.recycler.adapter.DrawerRecyclerAdapter;
import com.nannan.doit.rx.RxBus;
import com.nannan.doit.rx.event.MissionCateSelectedEvent;
import com.nannan.doit.vp.ipresenter.IDrawerPresenter;
import com.nannan.doit.vp.iview.IDrawer;
import com.nannan.doit.vp.presenter.DrawerPresenter;

import java.util.List;

/**
 * @author Johan
 * @since 16/9/16.
 */

public class DrawerFragment extends BaseRefreshFragment<MissionCateModel>
        implements IDrawer {

  private IDrawerPresenter presenter;
  private long cateId =0;


  @Override
  protected BasePullComplexRecyclerAdapter<MissionCateModel> getListAdapter() {
    return new DrawerRecyclerAdapter(mRecyclerView, getActivity());
  }

  @Override
  protected void initView(View view) {
    super.initView(view);
    mAdapter.setOnItemClickListener((view1, item, position) -> {
      cateId =item.getId();
      setCateSelected(mData);
      mAdapter.notifyDataSetChanged();
      RxBus.getDefault().post(new MissionCateSelectedEvent(item));
    });
  }

  protected void initData() {
    presenter=new DrawerPresenter(this);
    presenter.loadData(getActivity());
  }


  @Override
  public void onDataLoad(List<MissionCateModel> list) {
    mData.clear();
    mData.addAll(setCateSelected(list));
    mAdapter.refresh(mData);
  }

  private List<MissionCateModel> setCateSelected(List<MissionCateModel> list){
    for (MissionCateModel model : list) {
      model.setSelected(model.getId()== cateId);
    }
    return list;
  }

}

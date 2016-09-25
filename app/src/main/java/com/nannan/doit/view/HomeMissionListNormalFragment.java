package com.nannan.doit.view;

import android.support.v4.app.Fragment;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.nannan.doit.R;
import com.nannan.doit.base.BasePullComplexRecyclerAdapter;
import com.nannan.doit.data.javautils.MissionModelNormaiComparator;
import com.nannan.doit.model.MissionCateModel;
import com.nannan.doit.model.MissionModel;
import com.nannan.doit.recycler.adapter.DialogMissonCateAdapter;
import com.nannan.doit.recycler.adapter.HomeRecyclerViewAdapter;
import com.nannan.doit.utils.IntentUtil;
import com.nannan.doit.vp.ipresenter.IHomeMissionListNormalPresenter;
import com.nannan.doit.vp.iview.IHomeMissionListNormal;
import com.nannan.doit.vp.presenter.HomeMissionListNormalPresenter;
import com.nannan.widget.linearlistview.LinearListView;
import com.nannan.widget.material.MaterialDialog;

import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Johan
 * @since 16/9/16.
 */

public class HomeMissionListNormalFragment extends HomeBaseMissionListFragment<MissionModel>
    implements IHomeMissionListNormal {


  public static Fragment getInstance(long cateId) {
    HomeMissionListNormalFragment fragment = new HomeMissionListNormalFragment();
    fragment.setCateId(cateId);
    return fragment;
  }

  private IHomeMissionListNormalPresenter presenter;

  @Override
  public BasePullComplexRecyclerAdapter<MissionModel> getListAdapter() {
    return new HomeRecyclerViewAdapter(mRecyclerView, getActivity());
  }

  @Override
  protected void initView(View view) {
    super.initView(view);
    mAdapter.setOnItemClickListener((view1, item, position) -> {
      IntentUtil.openMissionEditActivity(getActivity(),cateId,item.getId());
    });
    mAdapter.setOnItemLongClickListener(
        (view1, item, position) -> {
          final MaterialDialog deleteDialog = new MaterialDialog(getActivity());
          deleteDialog.setPositiveButton(getString(R.string.dialog_button_confirm),
              v -> {
                presenter.deleteMission(getActivity(), mData.get(position));
                deleteDialog.dismiss();
              })
              .setNegativeButton(getString(R.string.dialog_button_cancel),
                  v -> deleteDialog.dismiss())
              .setMessage(getString(R.string.dialog_confirm_delete))
              .show();
        });
  }

  @Override
  protected int setLayoutViewId() {
    return R.layout.fragment_home_normal_list;
  }

  @Override
  protected void initData() {
    presenter = new HomeMissionListNormalPresenter(this);
    presenter.loadData(getActivity(), cateId);
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
    presenter.loadData(getActivity(), cateId);
  }

  @Override
  public void setViewStateSuccess() {
    presenter.loadData(getActivity(), cateId);
  }

  @Override
  public void showMissionMoveDialog(List<MissionCateModel> list, MissionModel model) {
    MaterialDialog dialog = new MaterialDialog(getActivity());
    View view = getActivity().getLayoutInflater().inflate(R.layout.view_dialog_move_mission, null);
    dialog.setTitle("移动至");
    dialog.setContentView(view);
    dialog.setCanceledOnTouchOutside(true);
    LinearListView listView = ButterKnife.findById(view, R.id.rv_dialog_mission_cate);
    DialogMissonCateAdapter dialogAdapter = new DialogMissonCateAdapter(getActivity(), list);
    listView.setAdapter(dialogAdapter);
    listView.setOnItemClickListener((item, view1, position) -> {
      presenter.moveMission(getActivity(), model, (MissionCateModel) item);
      dialog.dismiss();
    });
    dialog.setOnDismissListener(dialog1 -> {
      mAdapter.notifyDataSetChanged();
    });
    dialog.show();
  }

  @Override
  public void onSwiped(int adapterPosition, int direction) {
    if (direction == ItemTouchHelper.RIGHT) {
      presenter.setMissionState(getActivity(), mData.get(adapterPosition));
    } else {
      presenter.getMissionMoveList(getActivity(), mData.get(adapterPosition));
    }
  }

  @OnClick(R.id.fab_home_add_mission)
  public void onAddFabClick() {
    IntentUtil.openMissionEditActivity(getActivity(), cateId,-1);
  }


}

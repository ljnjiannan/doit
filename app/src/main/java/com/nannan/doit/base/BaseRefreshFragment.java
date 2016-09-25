package com.nannan.doit.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nannan.doit.R;
import com.nannan.doit.base.iview.IRefreshAndMoreView;
import com.nannan.doit.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * @author Johan
 * @since 16/9/16.
 */

public abstract class BaseRefreshFragment<T> extends BaseFragment
        implements IRefreshAndMoreView, BasePullComplexRecyclerAdapter.onLoadMoreListener {

  protected RecyclerView mRecyclerView;
  protected BasePullComplexRecyclerAdapter<T> mAdapter;
  protected List<T> mData=new ArrayList<T>();

  @Override
  protected void initView(View view) {
    mRecyclerView= ButterKnife.findById(view,R.id.base_activity_recyclerview);
    if (mRecyclerView != null) {
      mRecyclerView.setHasFixedSize(true);
      mRecyclerView.setLayoutManager(getLayoutManager());
      if (getListAdapter() != null) {
        mAdapter = getListAdapter();
        mAdapter.setOnLoadMoreListener(this);
        mRecyclerView.setAdapter(mAdapter);
      } else {
        LogUtil.error(TAG, "getListAdapter can not be null");
      }
    }
  }

  @Override
  protected int setLayoutViewId() {
    return R.layout.base_fragment_refresh;
  }


  protected RecyclerView.LayoutManager getLayoutManager() {
    return new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
  }

  protected abstract BasePullComplexRecyclerAdapter<T> getListAdapter();

  @Override
  public void onLoadmore() {

  }

  @Override
  public void showLoadMore() {

  }

  @Override
  public void showNoMore() {

  }

  @Override
  public void showLoadMoreError(String msg) {

  }

  @Override
  public void hideLoadMore() {

  }

  @Override
  public void loadCompleted() {

  }

  @Override
  public void showLoading() {

  }

  @Override
  public void showLoading(String message) {

  }

  @Override
  public void hideLoading() {

  }

  @Override
  public void showError(String message) {

  }

  @Override
  public void showNetError() {

  }

  @Override
  public void onReloadData() {

  }

  @Override
  public void showLoadingDialog() {

  }

  @Override
  public void hideLoadingDialog() {

  }

}

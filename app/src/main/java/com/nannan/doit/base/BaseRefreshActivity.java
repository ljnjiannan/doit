package com.nannan.doit.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nannan.doit.R;
import com.nannan.doit.base.iview.IRefreshAndMoreView;
import com.nannan.doit.utils.LogUtil;

import butterknife.ButterKnife;

/**
 * @author ljnjiannan
 * @since 16/9/11.
 */

public abstract class BaseRefreshActivity<T> extends BaseActivity
    implements IRefreshAndMoreView, BasePullComplexRecyclerAdapter.onLoadMoreListener {

  protected BasePullComplexRecyclerAdapter mAdapter;

  public RecyclerView mRecyclerView;

  @Override
  protected String setActivityTitle() {
    return null;
  }

  @Override
  protected int setContentViewId() {
    return R.layout.base_activity_refresh;
  }

  @Override
  protected void initView() {
    mRecyclerView= ButterKnife.findById(this,R.id.base_activity_recyclerview);
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

  protected RecyclerView.LayoutManager getLayoutManager() {
    return new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
  }

  public abstract BasePullComplexRecyclerAdapter<T> getListAdapter();

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

package com.nannan.doit.base.iview;

/**
 * @author ljnjiannan
 * @since 16/9/11.
 */

public interface IRefreshAndMoreView extends IRefreshView{

  void showLoadMore();

  void showNoMore();

  void showLoadMoreError(String msg);

  void hideLoadMore();
}

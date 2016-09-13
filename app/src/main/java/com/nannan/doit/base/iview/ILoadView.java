package com.nannan.doit.base.iview;

/**
 * @author ljnjiannan
 * @since 16/9/11.
 */

public interface ILoadView extends IView{


  void showLoading();


  void showLoading(String message);


  void hideLoading();


  void showError(String message);


  void showNetError();


  void onReloadData();


  void showLoadingDialog();


  void hideLoadingDialog();
}

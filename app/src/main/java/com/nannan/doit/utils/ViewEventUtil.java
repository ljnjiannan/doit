package com.nannan.doit.utils;

import android.view.View;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 * @author ljnjiannan
 * @since 16/9/11.
 */

public class ViewEventUtil {

  public static final int DURATION = 1;

  /**
   * @param view 控件
   * @param action rx action实例
   */
  public static void click(View view, Action1 action) {
    click(view, DURATION, action);
  }

  /**
   * @param view 控件
   * @param duration 时长
   * @param action rx action
   */
  public static void click(View view, int duration, Action1 action) {
    RxView.clicks(view)
        .throttleFirst(duration, TimeUnit.SECONDS)
        .subscribe(action, new Action1<Throwable>() {
          @Override
          public void call(Throwable throwable) {

          }
        });
  }

  /**
   * 事件点击
   */
  public static void clickWithMilliSec(View view, int duration, Action1 action) {
    RxView.clicks(view)
        .throttleFirst(duration, TimeUnit.MILLISECONDS)
        .subscribe(action, new Action1<Throwable>() {
          @Override
          public void call(Throwable throwable) {

          }
        });
  }

}

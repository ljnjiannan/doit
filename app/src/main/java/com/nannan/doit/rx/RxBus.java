package com.nannan.doit.rx;

import com.nannan.doit.utils.LogUtil;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * @author Johan
 * @since 16/9/16.
 */

public class RxBus {

  private static final String TAG = "RxBus";

  private final Subject<Object, Object> subject = new SerializedSubject<>(PublishSubject.create());

  public static RxBus getDefault() {
    return HolderClass.INSTANCE;
  }

  private final static class HolderClass {
    private final static RxBus INSTANCE = new RxBus();
  }

  public void post(Object event) {
    subject.onNext(event);
  }

  public  <T> Observable<T> toObservable(final Class<T> type) {
    return subject.ofType(type);
  }

  public <T> Subscription toSubscription(final Class<T> type, Observer<T> observer) {
    return toObservable(type).subscribe(observer);
  }

  public <T> Subscription toSubscription(final Class<T> type, Action1<T> action1) {
    return toObservable(type).subscribe(action1, new Action1<Throwable>() {
      @Override
      public void call(Throwable throwable) {
        LogUtil.error(TAG,"type="+type+",error="+throwable,throwable);
      }
    });
  }

  public boolean hasObservers() {
    return subject.hasObservers();
  }

}

package com.nannan.doit.rx;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author Johan
 * @since 16/9/18.
 */

public class RxUtils {

  /**
   * 取消subscription订阅
   */
  public static void unsubscribeIfNotNull(Subscription... subscriptions) {
    for (Subscription subscription : subscriptions) {
      if (subscription != null) {
        subscription.unsubscribe();
      }
    }

  }

  public static CompositeSubscription getNewCompositeSubIfUnsubscribed(
          CompositeSubscription subscription) {
    if (subscription == null || subscription.isUnsubscribed()) {
      return new CompositeSubscription();
    }

    return subscription;
  }
}

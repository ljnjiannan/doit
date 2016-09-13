package com.nannan.doit.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.nannan.doit.utils.LogUtil;
import com.nannan.doit.utils.ViewEventUtil;

import rx.functions.Action1;

/**
 * @author ljnjiannan
 * @since 16/9/11.
 */

public abstract class BaseItemDelegate<T> extends BaseAdapterDelegate<T> {

  private static final String TAG = "BaseItemDelegate";
  /**
   * item点击事件
   */
  protected BaseSimpleRecyclerAdapter.OnMultiItemClickListener<T> mOnMultiItemClickListener = null;

  public BaseItemDelegate(Context context, int viewType) {
    super(context, viewType);
  }

  public void setOnMultiItemClickListener(BaseSimpleRecyclerAdapter.OnMultiItemClickListener<T> listener) {
    this.mOnMultiItemClickListener = listener;
  }

  @Override
  public void onBindViewHolder(@NonNull T item, int position,
      @NonNull BaseRecyclerViewHolder holder) {

    setClickListener(holder.itemView, item, position);
  }

  protected void setClickListener(final View itemView, final T item, final int position) {

    if (mOnMultiItemClickListener != null) {
      ViewEventUtil.click(itemView, new Action1() {
        @Override
        public void call(Object o) {
          mOnMultiItemClickListener.onMultiItemClick(itemView, item, position, viewType);
        }
      });
    } else {
      LogUtil.error(TAG, "adapter must extends BaseRecyclerAdapter");
    }
  }

}

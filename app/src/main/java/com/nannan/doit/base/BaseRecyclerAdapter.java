package com.nannan.doit.base;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;

import com.nannan.doit.lib.recyclerview.AdapterDelegate;

import java.util.List;

/**
 * @author ljnjiannan
 * @since 16/9/11.
 */

public class BaseRecyclerAdapter<T> extends BasePullComplexRecyclerAdapter<T> {

  public BaseRecyclerAdapter(RecyclerView recyclerView, Context context, List<T> data) {
    super(recyclerView, context, data);
  }

  @Override
  protected ItemView setFootView() {
    return null;
  }

  @Override
  public void setOnMultiItemClickListener(OnMultiItemClickListener<T> listener) {
    super.setOnMultiItemClickListener(listener);
    //delegate item click
    SparseArrayCompat<AdapterDelegate<T>> delegates = delegatesManager.getDelegates();
    int size = delegates.size();
    for (int i = 0; i < size; i++) {
      BaseItemDelegate<T> delegate = (BaseItemDelegate<T>) delegates.valueAt(i);
      delegate.mOnMultiItemClickListener = listener;
    }
  }
}

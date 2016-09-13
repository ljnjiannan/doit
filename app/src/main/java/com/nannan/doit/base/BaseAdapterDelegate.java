package com.nannan.doit.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * @author ljnjiannan
 * @since 16/9/11.
 */

public abstract class BaseAdapterDelegate<T> extends AbsAdapterDelegate<T> {

  protected Context mContext;
  protected LayoutInflater mLayoutInflater;

  public BaseAdapterDelegate(Context context, int viewType) {
    super(viewType);
    mContext = context;
    this.mLayoutInflater = LayoutInflater.from(context);

  }

  @NonNull
  @Override
  public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent) {
    return new BaseRecyclerViewHolder(mLayoutInflater.inflate(getItemViewLayout(), parent, false));
  }

  public abstract int getItemViewLayout();
}
